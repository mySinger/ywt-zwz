package com.gkzxhn.ywt_gkzx.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.utils.BaseActivity;

public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将系统状态栏设为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_login);
        Spinner sp = (Spinner) findViewById(R.id.login_spinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.languages);

                if ("个人用户".equals(languages[position])) {
                    Toast.makeText(LoginActivity.this, "个人", Toast.LENGTH_LONG).show();
                    LoginFragmentPerson lgo = new LoginFragmentPerson();
                    getFragmentManager().beginTransaction().replace(R.id.frame_layout, lgo).commit();

                } else if ("监狱用户".equals(languages[position])) {
                    LoginFragmentPrison lgt = new LoginFragmentPrison();
                    getFragmentManager().beginTransaction().replace(R.id.frame_layout, lgt).commit();
                    Toast.makeText(LoginActivity.this, "监狱", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }


}

