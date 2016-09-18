package com.gkzxhn.ywt_gkzx.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.main.MainActivity;
import com.gkzxhn.ywt_gkzx.utils.CustomDialog;

import static android.R.attr.data;

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

    /**
     *返回键点击事件监听
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                final CustomDialog customDialog = new CustomDialog(this);
                customDialog.createDialog("Yes", "No", "放弃结算", "如果你确认要取消结算，你所选商品会全部清零，请你慎重选择！", new CustomDialog.CallBack() {
                    @Override
                    public void isConfirm(boolean flag) {
                        if(flag == true){
                            Intent data = new Intent();
                            data.putExtra("Intent","PayMoney");
                            PayActivity.this.setResult(1,data);
                            finish();
                        }else{
                            customDialog.createToasts("请继续结算,祝您购物愉快！",getLayoutInflater());
                        }
                    }
                });
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_back:
                final CustomDialog customDialog = new CustomDialog(this);
                customDialog.createDialog("Yes", "No", "放弃结算", "如果你确认要取消结算，你所选商品会全部清零，请你慎重选择！", new CustomDialog.CallBack() {
                    @Override
                    public void isConfirm(boolean flag) {
                        if(flag == true){
                            Intent data = new Intent();
                            data.putExtra("Intent","PayMoney");
                            PayActivity.this.setResult(1,data);
                            finish();
                        }else{
                            customDialog.createToasts("请继续结算,祝您购物愉快！",getLayoutInflater());
                        }
                    }
                });
                break;
        }
    }
}
