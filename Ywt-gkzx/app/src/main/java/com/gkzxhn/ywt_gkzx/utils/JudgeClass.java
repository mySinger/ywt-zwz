package com.gkzxhn.ywt_gkzx.utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by ZengWenZhi on 2016/8/25 0025.
 * JudgeClass 判断类，用于判断各种合法性
 */

public class JudgeClass {
    public Context context;
    private int color;

    public JudgeClass(Context context) {
        this.context = context;
    }

    //对电话号码进行合法性判断
    public void JudgePhoneNumber(EditText phone) {
        String strPhone = phone.getText().toString();
        if (strPhone.equals("")) {
            phone.setHint("亲，不能为空哟");
            //设置提示语字体颜色
            color = context.getResources().getColor(R.color.tv_red);
            phone.setHintTextColor(color);
        }
    }

    //对身份证号码进行合法性判断
    public void JudgeIdCard(EditText idCard) {
        String strIdCard = idCard.getText().toString();
        if (strIdCard.equals("")) {
            idCard.setHint("亲，不能为空哟！");
            idCard.setHintTextColor(color);
        }
    }

    //对验证码进行合法性判断
    public void JudgeVerificationCode(EditText VerificationCode) {
        String strVerificationCode = VerificationCode.getText().toString();
        if (strVerificationCode.equals("")) {
            VerificationCode.setHint("亲，不能为空呦！");
            VerificationCode.setHintTextColor(color);
        }
    }
}
