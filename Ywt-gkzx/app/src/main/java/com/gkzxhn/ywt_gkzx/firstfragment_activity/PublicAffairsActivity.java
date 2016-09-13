package com.gkzxhn.ywt_gkzx.firstfragment_activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by ZengWenZhi on 2016/9/7 0007.
 * 狱务公开
 */

public class PublicAffairsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏透明化
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_affairs_public);
    }
}
