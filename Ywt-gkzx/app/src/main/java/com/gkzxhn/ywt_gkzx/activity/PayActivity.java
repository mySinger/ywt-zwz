package com.gkzxhn.ywt_gkzx.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by ZengWenZhi on 2016/8/31 0031.
 * 远程探监充值的支付模块
 *
 */

public class PayActivity extends Activity implements View.OnClickListener{

    private TextView pay;
    private String payMoney;
    private ImageView payBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //将系统的状态栏设为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_pay);

        Bundle num = getIntent().getExtras();
        //拿到充值页面传过来的充值金额
        payMoney = (String) num.getCharSequence("money");
        initView();
        super.onCreate(savedInstanceState);
    }

    public void initView(){
        payBack = (ImageView) findViewById(R.id.pay_back);
        payBack.setOnClickListener(this);
        pay = (TextView) findViewById(R.id.pay_money);
        pay.setText(payMoney);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_back:
                finish();
        }
    }
}
