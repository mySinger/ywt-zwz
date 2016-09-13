package com.gkzxhn.ywt_gkzx.fragment;


import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.activity.RechargeActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ZengWenZhi on 2016/8/22 0022.
 * 探监的远程探监模块
 */

public class VisitRemoteFragment extends Fragment implements View.OnClickListener, OnDateSetListener {

    private View view;
    private DatePickerDialog dlg;
    private TextView txt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_remote_visit, container, false);

        //充值按钮
        Button recharge = (Button) view.findViewById(R.id.visit_recharge);
        recharge.setOnClickListener(this);

        Button bt_visit_apply = (Button) view.findViewById(R.id.bt_visit_apply);
        bt_visit_apply.setOnClickListener(this);
        //设置远程探监的申请时间按钮
        Button setApplyTime_btn = (Button) view.findViewById(R.id.set_apply_time_btn);
        setApplyTime_btn.setOnClickListener(this);
        txt = (TextView) view.findViewById(R.id.time_visit_apply);
        initApplyTime();

        return view;
    }

    @Override
    public void onClick(View v) {
        int checkedId = v.getId();
        switch (checkedId) {
            case R.id.bt_visit_apply:
                //未完待续
                break;
            case R.id.set_apply_time_btn:
                //创建一个日历引用calendar，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
                Calendar calendar = Calendar.getInstance(Locale.CHINA);
                //创建一个Date实例
                Date myDate = new Date();
                //设置日历的时间，把一个新建Date实例myDate传入
                calendar.setTime(myDate);
                //获得日历中的 year month day
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
       /* 新建一个DatePickerDialog 构造方法中
        （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）*/
                dlg = new DatePickerDialog(getActivity(), VisitRemoteFragment.this, year, month, day);
                //让DatePickerDialog显示出来
                dlg.show();
                break;
            case R.id.visit_recharge:
                Intent intent = new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent);
                break;

        }

    }

    //初始化申请会见时间，进入界面立即刷新
    public void initApplyTime() {
        Date dt = new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式,时间格式化
        String nowTime = "";
        //用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
        nowTime = df.format(dt);
        Log.e("time", nowTime);
        TextView applyTime = (TextView) view.findViewById(R.id.time_visit_apply);
        applyTime.setText(nowTime);
    }

    //onDateChangedListener的onDateSet是用于都在时间设置对话框的确定按钮被按下的监听
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Log.e("R.id.time_visit_apply", R.id.time_visit_apply + "");
        String yearString = String.valueOf(year);
        Log.e("year", yearString);
        String monthOfYearString = String.valueOf(monthOfYear + 1);
        Log.e("month", monthOfYearString);
        String dayOfMonthString = String.valueOf(dayOfMonth);
        Log.e("day", dayOfMonthString);
        txt.setText(yearString + "-" + monthOfYearString + "-" + dayOfMonthString);
    }
}
