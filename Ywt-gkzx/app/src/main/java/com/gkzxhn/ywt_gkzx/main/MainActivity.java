package com.gkzxhn.ywt_gkzx.main;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.activity.BuyCarActivity;
import com.gkzxhn.ywt_gkzx.activity.PayActivity;
import com.gkzxhn.ywt_gkzx.login.LoginActivity;
import com.gkzxhn.ywt_gkzx.main_activity.AccountInformationActivity;
import com.gkzxhn.ywt_gkzx.main_activity.RemittanceRecordActivity;
import com.gkzxhn.ywt_gkzx.main_activity.SettingActivity;
import com.gkzxhn.ywt_gkzx.main_activity.ShoppingRecordActivity;
import com.gkzxhn.ywt_gkzx.utils.BaseActivity;
import com.gkzxhn.ywt_gkzx.utils.CustomDialog;
import com.gkzxhn.ywt_gkzx.utils.DatabaseHelper;
import com.gkzxhn.ywt_gkzx.utils.Goods;
import com.gkzxhn.ywt_gkzx.utils.MyAdapter;

import java.util.List;

import static android.R.attr.tag;
import static com.gkzxhn.ywt_gkzx.R.drawable.menu_setting;
import static com.gkzxhn.ywt_gkzx.R.drawable.remittance_record;
import static com.gkzxhn.ywt_gkzx.R.drawable.shopping_record;
import static com.gkzxhn.ywt_gkzx.R.drawable.tab_first_bankground;
import static com.gkzxhn.ywt_gkzx.R.drawable.tab_secend_bankground;
import static com.gkzxhn.ywt_gkzx.R.drawable.tab_thrid_bankground;
import static com.gkzxhn.ywt_gkzx.R.drawable.user_info;
import static com.gkzxhn.ywt_gkzx.R.id.buyCar_num;
import static com.gkzxhn.ywt_gkzx.R.id.main;
import static com.gkzxhn.ywt_gkzx.R.id.rb_first;
import static com.gkzxhn.ywt_gkzx.R.id.settlement;
import static com.gkzxhn.ywt_gkzx.R.id.tv_agreement;

/**
 * Created by ZengWenZhi on 2016/8/16 0016.
 * 主界面
 */

public class MainActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView btn_back;
    private SlideMenu slideMenu;
    private RadioButton mRBtnFrist;
    private RadioButton mRBtnSecond;
    private RadioButton mRBtnThrid;
    private ViewGroup mainLayout;
    private TextView headPager;
    private TextView accountInformation;
    private TextView remittanceRecord;
    private TextView shoppingRecord;
    private TextView setting;
    private TextView logOut;
    private String intentTag;
    private CustomDialog customDialog;
    private LinearLayout settlementLayout;

    private String TAG;
    private TextView settlement;
    private ImageView buyCar;
    private TextView buyCar_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏为透明色，实现与app标题栏的颜色一致
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_main);

        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
        settlementLayout = (LinearLayout) findViewById(R.id.layout_settlement);
        initData();
        initView();

    }


    public void initData() {

        //获取自定义对话框对象
        customDialog = new CustomDialog(this);

        mainLayout = (ViewGroup) findViewById(main);

        mRBtnFrist = (RadioButton) mainLayout.findViewById(rb_first);

        mRBtnSecond = (RadioButton) mainLayout.findViewById(R.id.rb_second);
        mRBtnThrid = (RadioButton) mainLayout.findViewById(R.id.rb_thrid);
        //设置点击改变监听
        mRBtnFrist.setOnCheckedChangeListener(this);
        mRBtnSecond.setOnCheckedChangeListener(this);
        mRBtnThrid.setOnCheckedChangeListener(this);
        //perfromClick()方法用于设置默认开启页，此方法的调用位置只能在设置点击监听之后
        mRBtnFrist.performClick();

        //拿到账户、无账号快捷登录传递过来的数据intenttag,进入主界面时，作为toast弹出
        Intent intent = getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle = intent.getExtras();//.getExtras()得到intent所附带的额外数据
        intentTag = bundle.getString("Tag");
        customDialog.createToasts(intentTag, getLayoutInflater());

        //当用户通过无账号快捷登录进入时，将“探监”、“电子商务”两大模块屏蔽
        if (intentTag.equals("NoAccountLogin")) {
            //false使RadioButton 无法获取焦点，不能被点击。
            mRBtnSecond.setClickable(false);
            mRBtnThrid.setClickable(false);
        }


    }

    public void onClick(View v) {
        switch (v.getId()) {
            //如果跳转的下一个活动，如果有 intent.getExtras()接受上一个活动的数据，那么上一个活动必须有intent.putExtra()方法。
            case R.id.settlement:
                Intent intent = new Intent(this, PayActivity.class);
                TextView totalMoney = (TextView) findViewById(R.id.total_money);
                intent.putExtra("money", totalMoney.getText() + "元");
                //在跳转到结算界面的同时，将所选商品清零
                DatabaseHelper databaseHelper = new DatabaseHelper(this);
                List<Goods> list = databaseHelper.readAllGoods();
                databaseHelper.clearNum(list);

                //要求activity有返回值
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_back:
                slideMenu.switchMenu();
                break;
            //跳转到账户信息界面
            case R.id.menu_account_information:
                Intent accountIntent = new Intent(this, AccountInformationActivity.class);
                startActivity(accountIntent);
                break;
            //跳转到汇款记录界面
            case R.id.menu_remittance_record:
                Intent remittanceIntent = new Intent(this, RemittanceRecordActivity.class);
                startActivity(remittanceIntent);
                break;
            //跳转到购物记录界面
            case R.id.menu_Shopping_record:
                Intent shoppingIntent = new Intent(this, ShoppingRecordActivity.class);
                startActivity(shoppingIntent);
                break;
            //跳转到设置界面
            case R.id.setting:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);

                break;
            //注销登录
            case R.id.log_out:
                //注销登录对话框
                customDialog.createDialog("确认", "取消", "您确定要退出当前账号吗？", "请您再次确认？", new CustomDialog.CallBack() {
                    @Override
                    public void isConfirm(boolean flag) {
                        if (flag == true) {
                            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        } else if (flag == false) {
                            customDialog.createToasts("您已取消了用户注销操作！", getLayoutInflater());
                        }

                    }
                });
                break;
            case R.id.buycar:
                //跳转至购物车内容详情页面
                Intent intent_buyCar = new Intent(MainActivity.this, BuyCarActivity.class);
                startActivity(intent_buyCar);

                break;
        }
    }

    /**
     * 从电子商务模块进入结算模块，使用startActivityForResult(),要求activity有返回值。
     * 当resultCode为1时，从结算界面返回。
     * 当点击结算，放弃支付时，重新返回购物页面，此时需将所选商品和合计价格清零。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                TextView buyCar_num = (TextView) findViewById(R.id.buyCar_num);
                buyCar_num.setText("0");
                TextView totalMoney = (TextView) findViewById(R.id.total_money);
                totalMoney.setText("0.00");

                DatabaseHelper databaseHelper = new DatabaseHelper(this);
                ListView canteenLv = (ListView) findViewById(R.id.canteen_fragment_lv);
                MyAdapter myAdapter = new MyAdapter(this, R.layout.item_shopping_content,
                        databaseHelper.readAllGoods(), totalMoney, buyCar_num);
                canteenLv.setAdapter(myAdapter);

                break;

        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //获取被点击的控件的id
        int checkedWidgetId = buttonView.getId();

        if (isChecked) {

            //设置头标签随fragment的转换而转换，保持同步
            if (checkedWidgetId == rb_first) {
                headPager = (TextView) findViewById(R.id.headpager);
                headPager.setText("首页");
                Log.e("666666666666666", "首页");
                settlementLayout.setVisibility(View.GONE);

            } else if (checkedWidgetId == R.id.rb_second) {
                headPager.setText("探监");
                settlementLayout.setVisibility(View.GONE);
            } else if (checkedWidgetId == R.id.rb_thrid) {
                headPager.setText("电子商务");
                TAG = "gone";
                settlementLayout.setVisibility(View.VISIBLE);
            }
            //将被点击的按钮设为点击状态
            mRBtnFrist.setChecked(checkedWidgetId == rb_first);

            mRBtnSecond.setChecked(checkedWidgetId == R.id.rb_second);

            mRBtnThrid.setChecked(checkedWidgetId == R.id.rb_thrid);
            //展示与按钮相对应的fragment
            showFragment(checkedWidgetId);
        } else {
            //此处记录了用户上次浏览的选项卡
            String unCheckFragmentTag = getTagById(buttonView.getId());
            Fragment unCheckFragment = getFragmentManager().findFragmentByTag(unCheckFragmentTag);
            if (unCheckFragment != null) {
                //隐藏上次显示到fragment,确保fragment不会重叠
                getFragmentManager()
                        .beginTransaction()
                        .hide(unCheckFragment)
                        .commit();
            }
        }
    }

    public void showFragment(int checkedWidgetId) {
        String tag = getTagById(checkedWidgetId);
        Fragment mainFragment = getFragmentManager().findFragmentByTag(tag);
        if (mainFragment == null) {
            if (tag.equals("first")) {
                Fragment firstFragment = new HomePageFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.fl_contain, firstFragment, "first").commit();
            } else if (tag.equals("second")) {
                Fragment secondFragment = new VisitFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.fl_contain, secondFragment, "second").commit();
                Log.e("===", "666");
            } else if (tag.equals("thrid")) {
                Fragment thridFragment = new CanteenFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.fl_contain, thridFragment, "thrid").commit();
            }
        } else {
            getFragmentManager().beginTransaction()
                    .show(mainFragment).commit();
        }

    }

    public String getTagById(int checkedWidgetId) {

        if (checkedWidgetId == rb_first) {
            return "first";
        } else if (checkedWidgetId == R.id.rb_second) {
            return "second";
        } else {
            return "thrid";
        }
    }

    /**
     * 调整底部RadioButton的大小
     */
    public void initView() {
        //拿到购物车内商品总数对象
        buyCar_num = (TextView) findViewById(R.id.buyCar_num);

        //拿到购物车对象,并设置点击监听
        buyCar = (ImageView) findViewById(R.id.buycar);
        buyCar.setOnClickListener(this);

        //结算按钮
        settlement = (TextView) findViewById(R.id.settlement);
        settlement.setOnClickListener(this);

        RadioButton rb_first = (RadioButton) findViewById(R.id.rb_first);
        Drawable drawableFirst = getResources().getDrawable(tab_first_bankground);
        drawableFirst.setBounds(0, 0, 60, 60);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_first.setCompoundDrawables(null, drawableFirst, null, null);//只放上面

        RadioButton rb_second = (RadioButton) findViewById(R.id.rb_second);
        Drawable drawableSecond = getResources().getDrawable(tab_secend_bankground);
        drawableSecond.setBounds(0, 0, 60, 60);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_second.setCompoundDrawables(null, drawableSecond, null, null);//只放上面

        RadioButton rb_thrid = (RadioButton) findViewById(R.id.rb_thrid);
        Drawable drawableThird = getResources().getDrawable(tab_thrid_bankground);
        drawableThird.setBounds(0, 0, 60, 60);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_thrid.setCompoundDrawables(null, drawableThird, null, null);//只放上面

        //账户信息
        accountInformation = (TextView) findViewById(R.id.menu_account_information);
        accountInformation.setOnClickListener(this);

        //汇款记录
        remittanceRecord = (TextView) findViewById(R.id.menu_remittance_record);
        remittanceRecord.setOnClickListener(this);

        //购物记录
        shoppingRecord = (TextView) findViewById(R.id.menu_Shopping_record);
        shoppingRecord.setOnClickListener(this);

        //设置
        setting = (TextView) findViewById(R.id.setting);
        setting.setOnClickListener(this);

        //注销登录
        logOut = (TextView) findViewById(R.id.log_out);
        logOut.setOnClickListener(this);

        //设置侧滑菜单图片的大小
        Drawable drawableAI = getResources().getDrawable(user_info);
        //setBounds(x,y,width,height); x:组件在容器X轴上的起点 y:组件在容器Y轴上的起点 width:组件的长度 height:组件的
        drawableAI.setBounds(0, 0, 100, 100);
        accountInformation.setCompoundDrawables(drawableAI, null, null, null);//放在左边

        Drawable drawableRR = getResources().getDrawable(remittance_record);
        drawableRR.setBounds(0, 0, 100, 100);
        remittanceRecord.setCompoundDrawables(drawableRR, null, null, null);

        Drawable drawableSR = getResources().getDrawable(shopping_record);
        drawableSR.setBounds(0, 0, 100, 100);
        shoppingRecord.setCompoundDrawables(drawableSR, null, null, null);

        Drawable drawableS = getResources().getDrawable(menu_setting);
        drawableS.setBounds(0, 0, 100, 100);
        setting.setCompoundDrawables(drawableS, null, null, null);


        //获取自定义对话框工具类对象
        customDialog = new CustomDialog(this);

    }
}
