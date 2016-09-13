package com.gkzxhn.ywt_gkzx.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by ZengWenZhi on 2016/8/15 0015.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  void toastLong(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void toastShort(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


}
