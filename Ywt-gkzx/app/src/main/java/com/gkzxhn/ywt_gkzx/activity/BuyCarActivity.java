package com.gkzxhn.ywt_gkzx.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.utils.BuyCarAdapter;
import com.gkzxhn.ywt_gkzx.utils.Goods;

import java.util.ArrayList;
import java.util.List;

import static com.gkzxhn.ywt_gkzx.R.drawable.clearbuycar;

/**
 * Created by ZengWenZhi on 2016/9/18 0018.
 * 购物车内商品内容详情及处理页面
 */

public class BuyCarActivity extends Activity {

    private List<Goods> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //系统状态栏透明化
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_buy_car);

        initData();
        initView();
    }

    private void initView() {
        //指定清理购物车的图片的大小
        TextView tvClear = (TextView) findViewById(R.id.tv_clear);
        Drawable drawableClear = getResources().getDrawable(clearbuycar);
        drawableClear.setBounds(0, 0, 50, 50);
        tvClear.setCompoundDrawables(drawableClear, null, null, null);
        BuyCarAdapter buyCarAdapter = new BuyCarAdapter(this,R.layout.item_buy_car,list);
        ListView buyCarLV = (ListView) findViewById(R.id.lv_buyCar);
        buyCarLV.setAdapter(buyCarAdapter);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Goods("zwz","8898",0));
    }


}
