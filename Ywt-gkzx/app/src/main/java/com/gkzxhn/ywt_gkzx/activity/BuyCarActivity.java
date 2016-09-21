package com.gkzxhn.ywt_gkzx.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.fragment.BuyCarFragment;

import static com.gkzxhn.ywt_gkzx.R.drawable.clearbuycar;

/**
 * Created by ZengWenZhi on 2016/9/18 0018.
 * 购物车内商品内容详情及处理页面
 */

public class BuyCarActivity extends Activity implements View.OnClickListener {

    private Button settlementBtn;
    private String totalMoney;
    private Fragment buyCarFragment;
    private TextView totalNum;
    private TextView money;

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

        //购物车内商品数量对象
        totalNum = (TextView) findViewById(R.id.goods_number);
        totalNum.setText(totalNumber);

        //购物车内商品合计对象
        money = (TextView) findViewById(R.id.money);
        money.setText(totalMoney);

        //给购物车详情页中添加fragment碎片
        buyCarFragment = new BuyCarFragment();
        getFragmentManager().beginTransaction().add(R.id.buyCar_fl, buyCarFragment).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击结算
            case R.id.settlementBtn:
                //拿到商品详情页的商品合计
                String tMoney = (String) money.getText();

                //在这里做判断，如果商品合计为零，则不会跳转到结算页面。
                if (tMoney.equals("0.00")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuyCarActivity.this);
                    builder.setTitle("温馨提示！")
                            .setMessage("购物车为空，请前往网上商城选购您所需的商品。")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent data = new Intent();
                                    data.putExtra("money", money.getText());
                                    data.putExtra("number", totalNum.getText());
                                    BuyCarActivity.this.setResult(0, data);
                                    finish();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(BuyCarActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                } else {
                    Intent intent = new Intent(BuyCarActivity.this, PayActivity.class);
                    intent.putExtra("money", tMoney);
                    intent.putExtra("TAG", "购物车详情页");
                    startActivity(intent);
                }
                break;
        }
    }

    /**
     * 当点击返回键退出购物车详情页时将商品总数和和合计回传给电子商城模块，
     * 以确保这两项数据的实时同步更新
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent data = new Intent();
        data.putExtra("money", money.getText());
        data.putExtra("number", totalNum.getText());
        BuyCarActivity.this.setResult(0, data);
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
