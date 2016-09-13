package com.gkzxhn.ywt_gkzx.main_activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by ZengWenZhi on 2016/8/23 0023.
 */

public class ShoppingRecordActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //将系统状态栏设置为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_record_shopping);
        super.onCreate(savedInstanceState);
    }
}
