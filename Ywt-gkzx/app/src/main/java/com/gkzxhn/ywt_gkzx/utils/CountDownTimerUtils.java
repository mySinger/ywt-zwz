package com.gkzxhn.ywt_gkzx.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.gkzxhn.ywt_gkzx.R;

/**
 * Created by ZengWenZhi on 2016/9/23 0023.
 * 验证码倒计时工具类
 */

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;

    /**
     * @param textView
     * @param millisInFuture    millisInFuture  从开始调用start()到倒计时完成并onFinish()方法被调用的毫秒数。
     *                          （译者注：倒计时时间，单位毫秒）
     * @param countDownInterval   接收onTick(long)回调的间隔时间。（译者注：单位毫秒）
     *
     */
    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //设置不可点击
        mTextView.setClickable(false);
        //设置倒计时时间
        mTextView.setText(millisUntilFinished / 1000 + "秒后可重新发送");
        //设置按钮为灰色，这时是不能点击的
        mTextView.setBackgroundResource(R.drawable.bg_identify_code_press);

        /**
         * TextView通常用来显示普通文本，但是有时候需要对其中某些文本进行样式、事件方面的设置。
         * Android系统通过SpannableString类来对指定文本进行相关处理，具体有以下功能：
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */

        //获取按钮上的文字
        SpannableString spannableString = new SpannableString(mTextView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         *
         *SPAN_INCLUSIVE_EXCLUSIVE：可以扩大的跨度，非0，包括文字插入他们的出发点，
         * 而不是在他们的终点，如果为0的时候就像一个点
         */
        //将倒计时的时间设置为红色
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText("重新获取");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.bg_identify_code_normal);  //还原背景色
    }
}
