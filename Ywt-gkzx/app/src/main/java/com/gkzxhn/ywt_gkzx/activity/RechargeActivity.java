package com.gkzxhn.ywt_gkzx.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.utils.BaseActivity;

import static android.R.attr.button;

/**
 * Created by ZengWenZhi on 2016/8/31 0031.
 * 远程探监充值模块
 */

public class RechargeActivity extends Activity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private RadioButton moneyOne;
    private RadioButton moneyTwo;
    private RadioButton moneyThree;
    private RadioButton moneyfour;
    private Button btn_recharge;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //将系统状态栏设为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_recharge);
        super.onCreate(savedInstanceState);
        intent = new Intent(this,PayActivity.class);
        /**
         * 值得注意的是对数据的初始化initData（），对view的初始化initView（）都要在填充布局文件之后进行！
         */
        initView();


    }

    public void initView(){


        //充值按钮
        btn_recharge = (Button) findViewById(R.id.btn_recharge);
        btn_recharge.setOnClickListener(this);

        moneyOne = (RadioButton) findViewById(R.id.money_one);
        moneyOne.setOnCheckedChangeListener(this);
        moneyOne.performClick();
        moneyTwo = (RadioButton) findViewById(R.id.money_two);
        moneyTwo.setOnCheckedChangeListener(this);
        moneyThree = (RadioButton) findViewById(R.id.money_three);
        moneyThree.setOnCheckedChangeListener(this);
        moneyfour = (RadioButton) findViewById(R.id.money_four);
        moneyfour.setOnCheckedChangeListener(this);
        //充值界面的返回按钮
        ImageView rechargeBack = (ImageView) findViewById(R.id.recharge_back);
        rechargeBack.setOnClickListener(this);
    }

    /**
     *CompoundButton buttonView:选中的按钮, boolean isChecked:是否被选中
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();

        //加入if判断防止每次点击switch结构先执行当前点击选项，再执行先前点击选项，执行两次。
        if(isChecked){
        switch (id){
            case R.id.money_one:
                Toast.makeText(this,"5",Toast.LENGTH_SHORT).show();
                intent.putExtra("money","5元");
                intent.putExtra("TAG","充值页");
                break;
            case R.id.money_two:
                Toast.makeText(this,"20",Toast.LENGTH_SHORT).show();
                intent.putExtra("money","20元");
                intent.putExtra("TAG","充值页");
                break;
            case R.id.money_three:
                Toast.makeText(this,"50",Toast.LENGTH_SHORT).show();
                intent.putExtra("money","50元");
                intent.putExtra("TAG","充值页");
                break;
            case R.id.money_four:
                Toast.makeText(this,"100",Toast.LENGTH_SHORT).show();
                intent.putExtra("money","100元");
                intent.putExtra("TAG","充值页");
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_recharge:
                //跳转到支付页
                startActivity(intent);
                break;
            case R.id.recharge_back:
                finish();
        }
    }
}
