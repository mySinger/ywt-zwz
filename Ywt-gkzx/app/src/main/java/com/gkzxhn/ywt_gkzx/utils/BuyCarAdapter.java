package com.gkzxhn.ywt_gkzx.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gkzxhn.ywt_gkzx.R;

import java.util.List;
import java.util.jar.Attributes;

import static android.R.attr.name;
import static com.gkzxhn.ywt_gkzx.R.drawable.e;

/**
 * Created by ZengWenZhi on 2016/9/18 0018.
 * 购物车界面listView的适配器
 */

public class BuyCarAdapter extends ArrayAdapter<Goods>{
    private List<Goods> goodsList;
    private Context context;
    private ViewHolder viewHolder;

    public BuyCarAdapter(Context context, int resource, List<Goods> goodsList) {
        super(context, resource);
        this.goodsList = goodsList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_buy_car,null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.num = (TextView) convertView.findViewById(R.id.tv_num);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(goodsList.get(position).getName());
        viewHolder.price.setText(goodsList.get(position).getPrice());
        viewHolder.num.setText(String.valueOf(goodsList.get(position).getNum()));

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

    class ViewHolder{
        private TextView name;
        private TextView price;
        private TextView num;
    }
}
