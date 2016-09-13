package com.gkzxhn.ywt_gkzx.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by ZengWenZhi on 2016/8/26 0026.
 * 自定义对话框工具类
 */

public class CustomDialog {
    private AlertDialog.Builder builder;
    private Context context;

    public CustomDialog(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public void createDialog(String positiveText, String negativeText, String title, String message,
                             final CallBack callBack) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        //屏蔽返回键
        builder.setCancelable(false);

        //设置对话框左侧图标
        builder.setIcon(R.drawable.gkzx_ywt);
        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                callBack.isConfirm(true);
            }
        });

        if(!negativeText.equals("null")) {
            builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callBack.isConfirm(false);
                }
            });
        }

        builder.create().show();
    }

    /**
     *创建的自定义对话框对象在调用方法实例化对话框时，通过创建接口的匿名内部类
     *的方式重写isConfirm()方法来确定点击相应选项的执行内容
     */

    public interface CallBack {
        void isConfirm(boolean flag);
    }

    /**
     * 自定义Toast
     * @param message
     * @param layoutInflater
     */
    public void createToasts(String message, LayoutInflater layoutInflater) {
        View view = layoutInflater.inflate(R.layout.toast, null);
        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
