package com.gkzxhn.ywt_gkzx.main_activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.utils.BaseActivity;
import com.gkzxhn.ywt_gkzx.utils.CustomDialog;
import com.zcw.togglebutton.ToggleButton;

/**
 * Created by ZengWenZhi on 2016/8/23 0023.
 */

public class SettingActivity extends BaseActivity {

    private CustomDialog customDialog;
    private ToggleButton toggleButtonPassWard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_setting);
        super.onCreate(savedInstanceState);
        initView();
    }

    /**
     * 在写switch语句的时候，一定要注意case穿透，在适当的
     * 地方不要忘记使用break；来结束语句。
     */

    public void initView() {
        customDialog = new CustomDialog(this);
        final ToggleButton toggleBtn =  (ToggleButton) findViewById(R.id.clock_switch);
        toggleBtn.setOnToggleChanged(new ToggleButton.OnToggleChanged(){
            @Override
            public void onToggle(boolean on) {
                String onStr = on + "";
                Log.e("=======================",on+"");
                switch (onStr){
                    case "true":
                        customDialog.createDialog("确认", "取消", "温馨提示", "闹铃提醒已开启，如您有即将会见的档期，系统将会在会见开始前半" +
                                "个小时以闹铃的形式提醒您，请注意手机状态。", new CustomDialog.CallBack() {
                            @Override
                            public void isConfirm(boolean flag) {
                                if(flag == true){
                                    toastShort("闹醒提醒已开启！");
                                }else{
                                    //关闭闹铃提醒
                                    toggleBtn.setToggleOff();
                                    toastLong("闹铃提醒已关闭！");
                                }
                            }
                        });
                        break;
                    case "false":
                        customDialog.createDialog("确认", "取消", "温馨提示", "您确定要关闭闹铃提醒服务吗？如果您关闭该服务，当您有即将会见的档期，系统将不会在会见开始前半" +
                                "个小时以闹铃的形式给您供提醒服务！。", new CustomDialog.CallBack() {
                            @Override
                            public void isConfirm(boolean flag) {
                                if(flag == true){
                                    toastShort("闹醒提醒成功关闭！");
                                }else{
                                    //开启闹铃提醒
                                    toggleBtn.setToggleOn();
                                    toastLong("闹铃提醒已开启！");
                                }
                            }
                        });
                }


            }
        });
        toggleButtonPassWard = (ToggleButton) findViewById(R.id.password_setting);
        toggleButtonPassWard.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if(on == true){
                    Intent intent = new Intent(SettingActivity.this,PassWordSettingActivity.class);
                    startActivityForResult(intent,0);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
