package com.gkzxhn.ywt_gkzx.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gkzxhn.ywt_gkzx.R;

import java.util.List;

/**
 * Created by ZengWenZhi on 2016/9/18 0018.
 * 购物车界面listView的适配器
 */

public class BuyCarAdapter extends ArrayAdapter<Goods> {
    private List<Goods> goodsList;
    private Context context;
    private ViewHolder viewHolder;
    private TextView number;
    private TextView money;

    public BuyCarAdapter(Context context, int resource, List<Goods> goodsList, TextView number, TextView money) {
        super(context, resource);
        this.goodsList = goodsList;
        this.context = context;
        this.money = money;
        this.number = number;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_buy_car, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.num = (TextView) convertView.findViewById(R.id.tv_num);
            viewHolder.add = (ImageView) convertView.findViewById(R.id.img_add);
            viewHolder.reduce = (ImageView) convertView.findViewById(R.id.img_reduce);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(goodsList.get(position).getName());
        viewHolder.price.setText(goodsList.get(position).getPrice());
        viewHolder.num.setText(String.valueOf(goodsList.get(position).getNum()));

        final DatabaseHelper databaseHelper = new DatabaseHelper(context);
        //购物车详情页面的listview的加号点击事件监听
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goods goods = goodsList.get(position);
                goods.setNum(goods.getNum() + 1);

                //拿到购物车商品数量对象
                int num = Integer.parseInt((String) number.getText());
                num++;
                number.setText(String.valueOf(num));
                //拿到购物车商品合计对象
                float price = Float.parseFloat((String) money.getText());
                price += Float.parseFloat(goods.price);
                money.setText(String.valueOf(price));

                databaseHelper.updateAGoods(goods);
                notifyDataSetChanged();
            }
        });

        //购物车详情页面的listview的减号点击事件监听
        viewHolder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goods goods = goodsList.get(position);
                goods.setNum(goods.getNum() - 1);

                //拿到购物车商品数量对象
                int num = Integer.parseInt((String) number.getText());
                num--;

                if (goods.getNum() == 0) {
                    //当购物车详情页的商品数量减为零时将该商品从购物车中删除
                    goodsList.remove(goods);
                    number.setText(String.valueOf(num));
                    //拿到购物车商品合计对象
                    float price = Float.parseFloat((String) money.getText());
                    price -= Float.parseFloat(goods.price);
                    money.setText(String.valueOf(price));

                    databaseHelper.updateAGoods(goods);
                } else {
                    number.setText(String.valueOf(num));
                    //拿到购物车商品合计对象
                    float price = Float.parseFloat((String) money.getText());
                    price -= Float.parseFloat(goods.price);
                    money.setText(String.valueOf(price));

                    databaseHelper.updateAGoods(goods);
                }

                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public Goods getItem(int position) {
        return goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }


    class ViewHolder {
        private TextView name;
        private TextView price;
        private TextView num;
        private ImageView reduce;
        private ImageView add;
    }
}
