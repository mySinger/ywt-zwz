package com.gkzxhn.ywt_gkzx.login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by Administrator on 2016/8/12 0012.
 * 登录的监狱用户模块
 */

public class LoginFragmentPrison extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment_prison,container,false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
