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
 * 焦点关注中显示新闻的listview的适配器
 * Created by ZengWenZhi on 2016/8/29 0029.
 */

public class FocusNewsAdapter extends ArrayAdapter<FocusNews> {
    private List<FocusNews> focusNewsList;
    private Context context;

    public FocusNewsAdapter(Context context, int resource, List<FocusNews> focusNewsList) {
        super(context, resource);
        this.focusNewsList = focusNewsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return focusNewsList.size();
    }

    @Override
    public FocusNews getItem(int position) {
        return focusNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news_focus, null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.tv_image);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FocusNews focusNews = focusNewsList.get(position);
        viewHolder.title.setText(focusNews.getTitle());
        viewHolder.content.setText(focusNews.getContent());
        viewHolder.image.setImageResource(focusNews.getImage());
        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView content;
        ImageView image;
    }
}
