package com.gkzxhn.ywt_gkzx.main;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.activity.NewsOneActivity;
import com.gkzxhn.ywt_gkzx.activity.NewsThreeActivity;
import com.gkzxhn.ywt_gkzx.activity.NewsTwoActivity;
import com.gkzxhn.ywt_gkzx.firstfragment_activity.AdviceActivity;
import com.gkzxhn.ywt_gkzx.firstfragment_activity.BriefIntroductionActivity;
import com.gkzxhn.ywt_gkzx.firstfragment_activity.DependentServiceActivity;
import com.gkzxhn.ywt_gkzx.firstfragment_activity.DynamicActivity;
import com.gkzxhn.ywt_gkzx.firstfragment_activity.LawActivity;
import com.gkzxhn.ywt_gkzx.firstfragment_activity.PublicAffairsActivity;
import com.gkzxhn.ywt_gkzx.utils.Ad;
import com.gkzxhn.ywt_gkzx.utils.FocusNews;
import com.gkzxhn.ywt_gkzx.utils.FocusNewsAdapter;
import com.gkzxhn.ywt_gkzx.utils.Picture;

import java.util.ArrayList;
import java.util.List;

import static com.gkzxhn.ywt_gkzx.R.drawable.focus;
import static com.gkzxhn.ywt_gkzx.R.drawable.laws;

/**
 * Created by ZengWenZhi on 2016/8/19 0019.
 * 首页模块
 */

public class HomePageFragment extends Fragment implements AdapterView.OnItemClickListener {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

            handler.sendEmptyMessageDelayed(0, 3000);

        }
    };


    private View view;
    private ArrayList<Ad> list = new ArrayList<>();


    public GridView gridView;
    //图片的文字标题
    public String[] titles = new String[]
            {"家属服务", "法律法规", "监狱简介", "狱务公开", "投诉建议", "工作动态"};
    //图片ID数组
    public int[] images = new int[]{
            R.drawable.family_service, R.drawable.laws, R.drawable.prison_introduction,
            R.drawable.prison_open, R.drawable.sms, R.drawable.visit_service
    };
    private ViewPager viewPager;
    private LinearLayout dot_layout;
    private TextView tv_intro;
    private TextView textView;


    public HomePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);

        initView();
        initData();
        initListener();

        return view;
    }


    class PictureAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<com.gkzxhn.ywt_gkzx.utils.Picture> pictures;

        public PictureAdapter(String[] titles, int[] images, Context context) {
            super();
            pictures = new ArrayList<>();
            //获取布局填充器
            inflater = LayoutInflater.from(context);
            for (int i = 0; i < images.length; i++) {
                Picture picture = new Picture(titles[i], images[i]);
                pictures.add(picture);
            }
        }

        @Override
        //条目数量
        public int getCount() {
            if (null != pictures) {
                return pictures.size();
            } else {
                return 0;
            }
        }

        @Override
        //条目内容
        public Object getItem(int position) {
            return pictures.get(position);
        }

        @Override
        //获取条目的索引
        public long getItemId(int position) {
            return position;
        }

        @Override
        //将布局填充成一个view对象，作为条目显示到界面上
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.picture_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setTextSize(18);
            viewHolder.title.setText(pictures.get(position).getTitle());
            viewHolder.image.setImageResource(pictures.get(position).getImageId());
            return convertView;
        }

    }


    public void initView() {

        gridView = (GridView) view.findViewById(R.id.gridview);
        PictureAdapter adapter = new PictureAdapter(titles, images, getActivity());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position){
                    case 0:
                        //家属服务
                        Intent intent = new Intent(getActivity(), DependentServiceActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //法律法规
                        Intent intentOne = new Intent(getActivity(), LawActivity.class);
                        startActivity(intentOne);
                        break;
                    case 2:
                        //监狱简介
                        Intent intentTwo = new Intent(getActivity(), BriefIntroductionActivity.class);
                        startActivity(intentTwo);
                        break;
                    case 3:
                        //狱务公开
                        Intent intentThree = new Intent(getActivity(), PublicAffairsActivity.class);
                        startActivity(intentThree);
                        break;
                    case 4:
                        //投诉建议
                        Intent intentFour = new Intent(getActivity(), AdviceActivity.class);
                        startActivity(intentFour);
                        break;
                    case 5:
                        //工作动态
                        Intent intentFive = new Intent(getActivity(), DynamicActivity.class);
                        startActivity(intentFive);
                }
                Toast.makeText(getActivity(), "pic" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });


        tv_intro = (TextView) view.findViewById(R.id.tv_intro);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerOne);
        dot_layout = (LinearLayout) view.findViewById(R.id.dot_layout);

        //焦点关注的图片的大小
        textView = (TextView) view.findViewById(R.id.tv_focus);
        Drawable drawable = getResources().getDrawable(focus);
        drawable.setBounds(0, 0, 80, 80);
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public void initData() {
        list.add(new Ad(R.drawable.banner, "狱务通上线了！"));
        list.add(new Ad(R.drawable.banner2, "一站式服务！"));
        list.add(new Ad(R.drawable.banner3, "给你家的温馨！"));
        //初始化点
        initDots();

        viewPager.setAdapter(new MyPagerAdapter());

        int centerValue = Integer.MAX_VALUE / 2 - 3;
        viewPager.setCurrentItem(centerValue);
        updateIntroAndDot();

        handler.sendEmptyMessageDelayed(0, 3000);

        /**
         * 焦点关注模块,空格：&#160;
         */

        List<FocusNews> focusNewsList = new ArrayList<>();
        focusNewsList.add(new FocusNews("湖南监狱系统贯彻司法部安全稳定工作会议精神，全面整顿监狱监管秩序。", R.drawable.focusnewstwo,
                "近日，司法部召开了全国监狱戒毒场所安全稳定工作电话会议，湖南省监狱管理局迅速贯彻落实会议精神，迅速组织召开会议，传达司法部会议精神" +
                        "在全省监狱范围内部署展开为期3个月的“严肃法纪规定、整顿监管秩序”专项整治。"));
        focusNewsList.add(new FocusNews("湖南监狱系统贯彻司法部安全稳定工作会议精神，全面整顿监狱监管秩序。", R.drawable.focusnewsone,
                "近日，司法部召开了全国监狱戒毒场所安全稳定工作电话会议，湖南省监狱管理局迅速贯彻落实会议精神，迅速组织召开会议，传达司法部会议精神" +
                        "在全省监狱范围内部署展开为期3个月的“严肃法纪规定、整顿监管秩序”专项整治。"));
        focusNewsList.add(new FocusNews("湖南监狱系统贯彻司法部安全稳定工作会议精神，全面整顿监狱监管秩序。", R.drawable.focusnewsthree,
                "近日，司法部召开了全国监狱戒毒场所安全稳定工作电话会议，湖南省监狱管理局迅速贯彻落实会议精神，迅速组织召开会议，传达司法部会议精神" +
                        "在全省监狱范围内部署展开为期3个月的“严肃法纪规定、整顿监管秩序”专项整治。"));

        Log.e("size", focusNewsList.size() + "");

        ListView focusnews_lv = (ListView) view.findViewById(R.id.focusnews_lv);
        FocusNewsAdapter focusNewsAdapter = new FocusNewsAdapter(getActivity(), R.layout.item_news_focus, focusNewsList);
        focusnews_lv.setAdapter(focusNewsAdapter);
        focusnews_lv.setOnItemClickListener(this);
    }

    /**
     * 焦点关注，条目点击监听
     *
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent intentOne = new Intent(getActivity(), NewsOneActivity.class);
                startActivity(intentOne);
                break;
            case 1:
                Intent intentTwo = new Intent(getActivity(), NewsTwoActivity.class);
                startActivity(intentTwo);
                break;
            case 2:
                Intent intentThree = new Intent(getActivity(), NewsThreeActivity.class);
                startActivity(intentThree);
                break;
        }
        Toast.makeText(getActivity(), position + "", Toast.LENGTH_LONG).show();
    }


    private void initDots() {
        for (int i = 0; i < list.size(); i++) {
            //创建点的view对象
            View view = new View(getActivity());
            //设置点的宽、高
            LayoutParams params = new LayoutParams(8, 8);
            if (i != 0) {
                params.leftMargin = 5;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selector_dot);
            dot_layout.addView(view);
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = View.inflate(getActivity(), R.layout.adapter_ad, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_adapter);
            imageView.setImageResource(list.get(position % list.size()).getIconResId());

            container.addView(view);//一定不能少，将view加入到viewPager中

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.e("Activity", "position: " + position);
                updateIntroAndDot();
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

    private void updateIntroAndDot() {
        int currentPage = viewPager.getCurrentItem() % list.size();
        tv_intro.setText(list.get(currentPage).getIntro());

        for (int i = 0; i < dot_layout.getChildCount(); i++) {
            dot_layout.getChildAt(i).setEnabled(i == currentPage);
        }
    }


}

class ViewHolder {
    public TextView title;
    public ImageView image;
}
