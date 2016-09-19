package com.gkzxhn.ywt_gkzx.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.fragment.BuyCarFragment;
import com.gkzxhn.ywt_gkzx.utils.Goods;

import java.util.List;

import static android.R.attr.button;
import static com.gkzxhn.ywt_gkzx.R.drawable.clearbuycar;

/**
 * Created by ZengWenZhi on 2016/9/18 0018.
 * 购物车内商品内容详情及处理页面
 */

public class BuyCarActivity extends Activity implements View.OnClickListener{

    private List<Goods> list;
    private Button settlementBtn;
    private String totalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //系统状态栏透明化
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_buy_car);

        initData();
        initView();
    }

    private void initView() {
        //指定清理购物车的图片的大小
        TextView tvClear = (TextView) findViewById(R.id.tv_clear);
        Drawable drawableClear = getResources().getDrawable(clearbuycar);
        drawableClear.setBounds(0, 0, 50, 50);
        tvClear.setCompoundDrawables(drawableClear, null, null, null);
           //拿到购物车详情页的结算按钮对象
        settlementBtn = (Button) findViewById(R.id.settlementBtn);
        settlementBtn.setOnClickListener(this);
    }

    private void initData() {
        //拿到从电子商务模块传过来的购物车内所选商品的总数和总价
        Bundle data = getIntent().getExtras();
        String totalNumber = (String) data.getCharSequence("totalnumber");
        totalMoney = (String) data.getCharSequence("totalmoney");

        TextView totalNum = (TextView) findViewById(R.id.goods_number);
        totalNum.setText(totalNumber);

        TextView money = (TextView) findViewById(R.id.money);
        money.setText(totalMoney);

        Fragment buyCarFragment = new BuyCarFragment();
        getFragmentManager().beginTransaction().add(R.id.buyCar_fl,buyCarFragment).commit();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.settlementBtn:
                Intent intent = new Intent(BuyCarActivity.this,PayActivity.class);
                intent.putExtra("money",totalMoney);
                intent.putExtra("TAG","购物车详情页");
                startActivity(intent);
                break;
        }
    }
}
