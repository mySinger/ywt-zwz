package com.gkzxhn.ywt_gkzx.main_activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by ZengWenZhi on 2016/8/23 0023.
 */

public class AccountInformationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //将状态栏设置为透明的,实现标题栏与系统状态栏颜色保持一致
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_information_account);
        super.onCreate(savedInstanceState);
    }
}
