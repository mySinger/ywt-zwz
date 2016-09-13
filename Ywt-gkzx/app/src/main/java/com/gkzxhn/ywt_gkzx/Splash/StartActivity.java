package com.gkzxhn.ywt_gkzx.Splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.login.LoginActivity;


/**
 * Created by ZengWenZhi on 2016/9/12 0012.
 * 启动页
 */

public class StartActivity extends Activity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将系统状态栏设为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_start);
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
