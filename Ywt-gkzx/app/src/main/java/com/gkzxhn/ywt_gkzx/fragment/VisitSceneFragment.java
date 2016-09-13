package com.gkzxhn.ywt_gkzx.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.gkzxhn.ywt_gkzx.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by ZengWenZhi on 2016/8/22 0022.
 * 探监模块的实地探监模块
 */

public class VisitSceneFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private View view;
    private TextView visitTimeContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_scene_visit, container, false);
        initView();
        initVisitTime();
        return view;
    }

    //初始化view
    public void initView() {
        visitTimeContent = (TextView) view.findViewById(R.id.visit_time_content);
        Button visitTimeBtn = (Button) view.findViewById(R.id.visit_time_btn);
        visitTimeBtn.setOnClickListener(this);
    }

    //初始化实地探监的申请时间
    public void initVisitTime() {
        //拿到当前时间
        Date date = new Date();
        //创建时间格式化工厂
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = "";
        nowTime = dateFormat.format(date);
        visitTimeContent.setText(nowTime);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.visit_time_btn:
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
                DatePickerDialog dlg = new DatePickerDialog(getActivity(), VisitSceneFragment.this, year, month, day);
                //让DatePickerDialog显示出来
                dlg.show();
        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        Log.e("R.id.time_visit_apply", R.id.time_visit_apply + "");
        String yearString = String.valueOf(year);
        Log.e("year", yearString);
        String monthOfYearString = String.valueOf(monthOfYear + 1);
        Log.e("month", monthOfYearString);
        String dayOfMonthString = String.valueOf(dayOfMonth);
        Log.e("day", dayOfMonthString);
        visitTimeContent.setText(yearString + "-" + monthOfYearString + "-" + dayOfMonthString);

    }
}
