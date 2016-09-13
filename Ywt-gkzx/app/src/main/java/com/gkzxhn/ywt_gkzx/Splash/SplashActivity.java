package com.gkzxhn.ywt_gkzx.Splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.login.LoginActivity;
import com.gkzxhn.ywt_gkzx.utils.SplashAD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZengWenZhi on 2016/9/12 0012.
 * 欢迎页，应用首次下载、版本更新时向用户展示
 */

public class SplashActivity extends Activity {
    private ViewPager viewPager;

    private List<SplashAD> list = new ArrayList<>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    createSharedPreference();

                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
            }
        }
    };
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //将系统状态栏设为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        super.onCreate(savedInstanceState);
        //首次进入时，sp.getBoolean("SplashIsFirst", true)为true
        sp = getSharedPreferences("APPData", MODE_PRIVATE);
        if (sp.getBoolean("SplashIsFirst", true) == false) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        } else {
            initView();
            initListener();
            initData();
        }
    }


    private void initView() {
        setContentView(R.layout.activity_splash);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.e("position", position + "");
                if (position == 2) {
                    handler.sendEmptyMessageDelayed(0, 3000);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initData() {
        list.add(new SplashAD(R.drawable.welcome01));
        list.add(new SplashAD(R.drawable.welcome02));
        list.add(new SplashAD(R.drawable.welcome03));

        viewPager.setAdapter(new MyPagerAdapter());


    }

    class MyPagerAdapter extends PagerAdapter {

        /**
         * 返回多少page
         */
        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * true: 表示不去创建，使用缓存  false:去重新创建
         * view： 当前滑动的view
         * object：将要进入的新创建的view，由instantiateItem方法创建
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 类似于BaseAdapger的getView方法
         * 用了将数据设置给view
         * 由于它最多就3个界面，不需要viewHolder
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(SplashActivity.this, R.layout.adapter_splash, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

            SplashAD splashAD = list.get(position);
            imageView.setImageResource(splashAD.getImageView());

            container.addView(view);//一定不能少，将view加入到viewPager中

            return view;
        }

        /**
         * 销毁page
         * position： 当前需要消耗第几个page
         * object:当前需要消耗的page
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 以SharedPreference方式存储应用数据
     * 写入数据后，使用commit（）提交
     * 由于欢迎页面只在更新之后和第一次使用时出现，所以展现完后，将其设为false，防止日常使用中
     * 再次出现
     */
    public void createSharedPreference() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("SplashIsFirst", false);
        //goodsIsFirst设为true,电子商务模块从本地数据库中加载商品信息
        editor.putBoolean("goodsIsFirst",true);
        editor.commit();
    }
}
