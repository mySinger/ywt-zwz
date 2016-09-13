package com.gkzxhn.ywt_gkzx.main;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.activity.RechargeActivity;
import com.gkzxhn.ywt_gkzx.fragment.VisitRemoteFragment;
import com.gkzxhn.ywt_gkzx.fragment.VisitSceneFragment;

import static com.gkzxhn.ywt_gkzx.R.drawable.remote_selector;
import static com.gkzxhn.ywt_gkzx.R.drawable.visit_selector;

/**
 * Created by ZengWenZhi on 2016/8/19 0019.
 * 探监模块
 */

public class VisitFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private View view;

    public VisitFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_secend, container, false);
        changeSize();
        initView();


        return view;
    }

    //===================动态调整顶部RadioButton中图片大小===================
    public void changeSize() {
        RadioButton rb1 = (RadioButton) view.findViewById(R.id.visit_rb1);
        Drawable drawableFirst = getResources().getDrawable(remote_selector);
        drawableFirst.setBounds(0, 0, 100, 100);//第一0是距左右边距离，第二0是距上下边距离，第三100长度,第四宽度
        rb1.setCompoundDrawables(drawableFirst, null, null, null);//只放上面

        RadioButton rb2 = (RadioButton) view.findViewById(R.id.visit_rb2);
        Drawable drawableSecond = getResources().getDrawable(visit_selector);
        drawableSecond.setBounds(0, 0, 100, 100);//第一0是距左右边距离，第二0是距上下边距离，第三100长度,第四宽度
        rb2.setCompoundDrawables(drawableSecond, null, null, null);//只放上面
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            int checkedWidgetId = buttonView.getId();
            Log.e("id", checkedWidgetId + "");
            switch (checkedWidgetId) {
                case R.id.visit_rb1:
                    Log.e("visit_rb1", "远程探监");
                    VisitRemoteFragment visitRemoteFragment = new VisitRemoteFragment();
                    getFragmentManager().beginTransaction().replace(R.id.visit_fl, visitRemoteFragment).commit();
                    break;
                case R.id.visit_rb2:
                    Log.e("visit_rb2", "实地探监");
                    VisitSceneFragment visitSceneFragment = new VisitSceneFragment();
                    getFragmentManager().beginTransaction().replace(R.id.visit_fl, visitSceneFragment).commit();
                    break;
            }
        }
    }

    public void initView() {
        RadioButton visit_rb1 = (RadioButton) view.findViewById(R.id.visit_rb1);
        visit_rb1.setOnCheckedChangeListener(this);
        RadioButton visit_rb2 = (RadioButton) view.findViewById(R.id.visit_rb2);
        visit_rb2.setOnCheckedChangeListener(this);
        //默认选中远程探监模块
        visit_rb1.performClick();

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){

        }
    }
}
