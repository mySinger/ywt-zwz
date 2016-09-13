package com.gkzxhn.ywt_gkzx.main;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.activity.PayActivity;
import com.gkzxhn.ywt_gkzx.activity.RechargeActivity;
import com.gkzxhn.ywt_gkzx.utils.CustomDialog;
import com.gkzxhn.ywt_gkzx.utils.DatabaseHelper;
import com.gkzxhn.ywt_gkzx.utils.Goods;
import com.gkzxhn.ywt_gkzx.utils.MyAdapter;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;

import static android.R.id.content;
import static android.R.id.list;
import static android.content.Context.MODE_PRIVATE;
import static android.os.Build.VERSION_CODES.M;
import static com.gkzxhn.ywt_gkzx.R.drawable.d;
import static com.gkzxhn.ywt_gkzx.R.layout.toast;

/**
 * Created by ZengWenZhi on 2016/8/19 0019.
 * 电子商务模块
 * 在电子商务模块，我们引入了一个内部模块依赖nicespinner
 */

public class CanteenFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Goods> myList;
    private View view;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sp;

    public CanteenFragment() {
    }


    @Nullable
    @Override
    //分类复选框
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third, container, false);
        /**
         * 初始化下拉列表
         */
        initSpinner();
        /**
         * 初始化数据库数据
         */
        initDatabaseData();
        /**
         * 初始化数据
         */
        initData();
        /**
         * 初始化View
         */
        initView();

        return view;
    }


    public void initView() {
    }

    /**
     * 初始化数据库数据
     */
    public void initDatabaseData() {
        myList = new ArrayList();

        myList.add(new Goods("万事如意杯", "门面到期，清仓大耍买，十元买不了吃亏、买不了上当，" +
                "走过路过不要错过，各位顾客请慢走！", "6", R.drawable.beizi1, 0));
        myList.add(new Goods("寿比南山杯", "门面到期，清仓大耍买，十元买不了吃亏、买不了上当，" +
                "走过路过不要错过，各位顾客请慢走！", "66", R.drawable.beizi2, 0));
        myList.add(new Goods("斯坦科维奇杯", "门面到期，清仓大耍买，十元买不了吃亏、买不了上当，" +
                "走过路过不要错过，各位顾客请慢走！", "666", R.drawable.beizi3, 0));
        myList.add(new Goods("世界杯", "门面到期，清仓大耍买，十元买不了吃亏、买不了上当，" +
                "走过路过不要错过，各位顾客请慢走！", "6666", R.drawable.beizi4, 0));
        myList.add(new Goods("万事如意杯", "门面到期，清仓大耍买，十元买不了吃亏、买不了上当，" +
                "走过路过不要错过，各位顾客请慢走！", "6", R.drawable.beizi1, 89));
        myList.add(new Goods("寿比南山杯", "门面到期，清仓大耍买，十元买不了吃亏、买不了上当，" +
                "走过路过不要错过，各位顾客请慢走！", "66", R.drawable.beizi2, 89));
        myList.add(new Goods("斯坦科维奇杯", "门面到期，清仓大耍买，十元买不了吃亏、买不了上当，" +
                "走过路过不要错过，各位顾客请慢走！", "666", R.drawable.beizi3, 89));
        myList.add(new Goods("世界杯", "门面到期，清仓大耍买，十元买不了吃亏、买不了上当，" +
                "走过路过不要错过，各位顾客请慢走！", "6666", R.drawable.beizi4, 89));

        databaseHelper = new DatabaseHelper(getActivity());
        sp = getActivity().getSharedPreferences("APPData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //当goodsIsFirst为true代表数据库有更新，将更新的数据插入数据库中。
        if(sp.getBoolean("goodsIsFirst",false) == true) {
            editor.putBoolean("goodsIsFirst",false);
            editor.commit();
            //将myList中的数据依次录入数据库中
            for (int i = 0; i < myList.size(); i++) {
                databaseHelper.insertAGoods(myList.get(i));
            }
            Toast.makeText(getActivity(),sp.getBoolean("goodsIsFirst",false)+"",Toast.LENGTH_LONG).show();
        }

        ListView canteenLv = (ListView) view.findViewById(R.id.canteen_fragment_lv);
        MyAdapter myAdapter = new MyAdapter(getActivity(), R.layout.item_shopping_content, databaseHelper.readAllGoods());
        canteenLv.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    public void initData() {

    }

    public void initSpinner() {
        NiceSpinner NSClassification = (NiceSpinner) view.findViewById(R.id.classification);
        final List<String> datasetClassification = new LinkedList<>(Arrays.asList("全部", "洗涤日化", "食品", "服饰鞋帽"));
        NSClassification.attachDataSource(datasetClassification);

        //分类复选框选择监听
        NSClassification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), datasetClassification.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //销量复选框
        NiceSpinner NSSales = (NiceSpinner) view.findViewById(R.id.sales);
        List<String> datasetSales = new LinkedList<>(Arrays.asList("一万以上", "一万~五千", "五千~一千", "一千一下"));
        NSSales.attachDataSource(datasetSales);
        //销量复选框选择监听
        NSSales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
