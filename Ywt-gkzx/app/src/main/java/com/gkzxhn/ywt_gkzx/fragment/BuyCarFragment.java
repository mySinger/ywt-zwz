package com.gkzxhn.ywt_gkzx.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

public class BuyCarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_car, container, false);


        List<Goods> list = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        //从数据库中获取所选商品信息，将其呈现在购物车详情页
        databaseHelper.obtainClickGoodsMessage(list);
        BuyCarAdapter buyCarAdapter = new BuyCarAdapter(getActivity(), R.layout.item_buy_car, list);
        ListView buyCarLV = (ListView) view.findViewById(R.id.lv_buyCar);
        buyCarLV.setAdapter(buyCarAdapter);
        return view;
    }
}
