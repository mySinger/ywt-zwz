package com.gkzxhn.ywt_gkzx.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.gkzxhn.ywt_gkzx.R;

/**
 * 焦点关注的第二条新闻信息
 * Created by ZengWenZhi on 2016/9/13 0013.
 */

public class NewsTwoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置系统状态栏为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_two_news);
    }
}
