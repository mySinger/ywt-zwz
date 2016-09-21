package com.gkzxhn.ywt_gkzx.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.utils.BuyCarAdapter;
import com.gkzxhn.ywt_gkzx.utils.DatabaseHelper;
import com.gkzxhn.ywt_gkzx.utils.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车详情页中的帧布局中所填充的fragment，用以显示购物车内所选商品信息列表
 * Created by ZengWenZhi on 2016/9/19 0019.
 */

public class BuyCarFragment extends Fragment implements View.OnClickListener {
    private List<Goods> list;
    private BuyCarAdapter buyCarAdapter;
    private DatabaseHelper databaseHelper;
    private TextView number;
    private TextView money;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_buy_car, container, false);


        list = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getActivity());
        //从数据库中获取所选商品信息，将其呈现在购物车详情页
        databaseHelper.obtainClickGoodsMessage(list);

        initView();
        return view;
    }

    //初始化View
    public void initView() {
        //拿到碎片所在activity中的清空购物车控件对象
        TextView clearBuyCar = (TextView) getActivity().findViewById(R.id.tv_clear);
        clearBuyCar.setOnClickListener(this);

        number = (TextView) getActivity().findViewById(R.id.goods_number);
        money = (TextView) getActivity().findViewById(R.id.money);

        buyCarAdapter = new BuyCarAdapter(getActivity(), R.layout.item_buy_car, list, number, money);
        ListView buyCarLV = (ListView) view.findViewById(R.id.lv_buyCar);
        buyCarLV.setAdapter(buyCarAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //清空购物车
            case R.id.tv_clear:
                number.setText("0");
                money.setText("0.00");
                for (Goods goods : list) {
                    databaseHelper.clearNumber(goods);
                }
                list.clear();
                buyCarAdapter.notifyDataSetChanged();
                break;
        }
    }
}
