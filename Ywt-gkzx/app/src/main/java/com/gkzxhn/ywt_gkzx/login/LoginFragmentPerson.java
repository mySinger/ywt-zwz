package com.gkzxhn.ywt_gkzx.login;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.main.MainActivity;
import com.gkzxhn.ywt_gkzx.regist.RegisterActivity;
import com.gkzxhn.ywt_gkzx.utils.BaseFragment;
import com.gkzxhn.ywt_gkzx.utils.JudgeClass;

/**
 * Created by Administrator on 2016/8/12 0012.
 * 登录的个人用户模块
 */

public class LoginFragmentPerson extends BaseFragment implements View.OnClickListener {
    View view;
    private EditText phoneNumber;
    private EditText idCard;
    private EditText verificationCode;
    private JudgeClass jc;
    private Button noAccountLogin;

    //初始化View
    @Override
    public void initView() {
        //无账号快捷登录
        noAccountLogin = (Button) view.findViewById(R.id.no_account_login);
        noAccountLogin.setOnClickListener(this);
        //注册按钮
        Button bt_register = (Button) view.findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
        /*动态添加背景
        bt_register.setBackground(getResources().getDrawable(R.drawable.bt_shape));*/
        //个人登录按钮
        Button person_dl = (Button) view.findViewById(R.id.bt_person_dl);
        person_dl.setOnClickListener(this);
        //电话号码
        phoneNumber = (EditText) view.findViewById(R.id.et_phone_number);
        //身份证号码
        idCard = (EditText) view.findViewById(R.id.et_id_card);
        //验证码
        verificationCode = (EditText) view.findViewById(R.id.et_verification_code);
        verificationCode.setBackgroundColor(getResources().getColor(R.color.white));
    }

    //初始化数据
    @Override
    public void initData() {
        //获取合法性判断类
        jc = new JudgeClass(getActivity());

    }

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment_person, container, false);
        initView();
        initData();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_person_dl:

                String phone = phoneNumber.getText().toString();

                String card = idCard.getText().toString();

                String Code = verificationCode.getText().toString();


                if (phone.equals("") || card.equals("") || Code.equals("")) {

                    jc.JudgePhoneNumber(phoneNumber);
                    jc.JudgeIdCard(idCard);
                    jc.JudgeVerificationCode(verificationCode);

                } else {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("Tag","欢迎使用!");
                    startActivity(intent);
                }
                break;
            case R.id.bt_register:
                Intent intentReg = new Intent(context, RegisterActivity.class);
                startActivity(intentReg);
                break;
            case R.id.no_account_login:
                //无账号快捷登录主界面
                Intent intent = new Intent(context,MainActivity.class);
                //无账号快捷登录时给主界面发送标签“NoAccountLogin”
                intent.putExtra("Tag", "无账号快捷登录");
                startActivity(intent);

        }
    }
}
