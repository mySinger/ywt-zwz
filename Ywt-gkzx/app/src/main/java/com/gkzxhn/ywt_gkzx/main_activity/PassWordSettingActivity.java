package com.gkzxhn.ywt_gkzx.main_activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.utils.BaseActivity;

import me.foji.widget.PwdEditText;

/**
 * Created by ZengWenZhi on 2016/9/3 0003.
 * 设置密码模块
 */

public class PassWordSettingActivity extends BaseActivity{

    private PwdEditText pwdEditText;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将状态栏设为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_setting_password);
        initView();
    }

    public void initView(){
        tv = (TextView) findViewById(R.id.tv);

        //拿到密码输入框对象
        pwdEditText = (PwdEditText) findViewById(R.id.tv_pwd);
        pwdEditText.setOnInputListener(new PwdEditText.OnInputListener() {
            @Override
            public void onInput(CharSequence text) {
                Toast.makeText(PassWordSettingActivity.this,text,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInputFinish(CharSequence text) {

                tv.setText("请再次输入密码");
                Toast.makeText(PassWordSettingActivity.this,"输入完毕！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
