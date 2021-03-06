package com.gkzxhn.ywt_gkzx.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.utils.CustomDialog;

import static com.gkzxhn.ywt_gkzx.R.layout.toast;

/**
 * Created by ZengWenZhi on 2016/8/31 0031.
 * 远程探监充值的支付模块
 */

public class PayActivity extends Activity implements View.OnClickListener {

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

    public void initView() {
        payBack = (ImageView) findViewById(R.id.pay_back);
        payBack.setOnClickListener(this);
        pay = (TextView) findViewById(R.id.pay_money);
        pay.setText(payMoney);
    }

    /**
     * 返回键点击事件监听
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                //当从购物车商品详情页跳转到支付页面时，不弹出选择对话框
                if (getIntent().getExtras().getCharSequence("TAG").equals("购物车详情页")) {
                    Toast.makeText(PayActivity.this, "欢迎您的再次光临!", Toast.LENGTH_SHORT).show();
                } else if (getIntent().getExtras().getCharSequence("TAG").equals("充值页")) {
                    Toast.makeText(PayActivity.this, "您已取消支付！", Toast.LENGTH_SHORT).show();
                } else {
                    final CustomDialog customDialog = new CustomDialog(this);
                    customDialog.createDialog("Yes", "No", "放弃结算", "如果你确认要取消结算，你所选商品会全部清零，请你慎重选择！", new CustomDialog.CallBack() {
                        @Override
                        public void isConfirm(boolean flag) {
                            if (flag == true) {
                                Intent data = new Intent();
                                data.putExtra("Intent", "PayMoney");
                                PayActivity.this.setResult(1, data);
                                finish();
                            } else {
                                customDialog.createToasts("请继续结算,祝您购物愉快！", getLayoutInflater());
                            }
                        }
                    });
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_back:
                if (getIntent().getExtras().getCharSequence("TAG").equals("电子商城")) {
                    final CustomDialog customDialog = new CustomDialog(this);
                    customDialog.createDialog("Yes", "No", "放弃结算", "如果你确认要取消结算，你所选商品会全部清零，请你慎重选择！", new CustomDialog.CallBack() {
                        @Override
                        public void isConfirm(boolean flag) {
                            if (flag == true) {
                                Intent data = new Intent();
                                data.putExtra("Intent", "PayMoney");
                                PayActivity.this.setResult(1, data);
                                finish();
                            } else {
                                customDialog.createToasts("请继续结算,祝您购物愉快！", getLayoutInflater());
                            }
                        }
                    });
                } else {
                    Toast.makeText(PayActivity.this, "您已取消支付!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
