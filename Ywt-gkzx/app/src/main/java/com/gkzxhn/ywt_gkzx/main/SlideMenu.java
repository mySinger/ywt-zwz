package com.gkzxhn.ywt_gkzx.main;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * 自定义view
 * Created by ZengWenZhi on 2016/8/17 0017.
 */

public class SlideMenu extends FrameLayout {
    private View menuView, mainView;
    private int menuWidth = 0;
    private Scroller scroller;

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideMenu(Context context) {
        super(context);
        init();
    }

    /**
     * Android里Scroller类是为了实现View平滑滚动的一个Helper类。
     * 通常在自定义的View时使用，在View中定义一个私有成员mScroller = new Scroller(context)。
     * 设置mScroller滚动的位置时，并不会导致View的滚动，通常是用mScroller记录/计算View滚动的位置，再重写View的computeScroll()，完成实际的滚动。
     */
    private void init() {
        scroller = new Scroller(getContext());
    }

    /**
     * 当1级的子view全部加载完调用，可以用初始化子view的引用
     * 注意，这里无法获取子view的宽高
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
        menuWidth = menuView.getLayoutParams().width;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (downX < 50) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * l: 当前子view的左边在父view的坐标系中的x坐标
     * t: 当前子view的顶边在父view的坐标系中的y坐标
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        menuView.layout(-menuWidth, 0, 0, menuView.getMeasuredHeight());
        mainView.layout(0, 0, r, b);
    }

    private int downX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                downX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:


                int moveX = (int) event.getX();
                Log.d("moveX", moveX + "");
                Log.d("downX", downX + "");

                int deltaX = (moveX - downX);
                Log.d("deltaX", deltaX + "");
/**
 * newScrollX() 是指当前view的左上角将要相对于母视图的左上角的X轴滑动的偏移量，scrollTo()实现具体滑动。
 * getScrollX() 就是当前view的左上角相对于母视图的左上角的X轴偏移量。
 */
                int newScrollX = getScrollX() - deltaX;
                Log.d("getScrollX()", getScrollX() + "");
                Log.d("newScrollX()", newScrollX + "");

                if (newScrollX < -menuWidth) newScrollX = -menuWidth;
                if (newScrollX > 0) newScrollX = 0;
                Log.e("Main", "getScrollX: " + getScrollX());

                scrollTo(newScrollX, 0);


                Log.e("Main", "getScrollX: " + getScrollX());
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                //2.使用Scroller
                if (getScrollX() > -menuWidth / 2) {
                    //关闭菜单
                    closeMenu();
                } else {
                    //打开菜单
                    openMenu();
                }
                break;
        }
        return true;
    }

    private void closeMenu() {
        scroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0, 400);

        invalidate();
    }

    private void openMenu() {
        scroller.startScroll(getScrollX(), 0, -menuWidth - getScrollX(), 0, 400);
        invalidate();
    }

    /**
     * Scroller不主动去调用这个方法
     * 而invalidate()可以掉这个方法
     * invalidate->draw->computeScroll
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        //先判断Scroller滚动是否完成,返回true,表示动画没结束
        if (scroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动,scroller.getCurrX():获取Scroller当前水平滚动的位置
            scrollTo(scroller.getCurrX(), 0);
            //必须调用该方法，否则不一定能看到滚动效果
            invalidate();
        }
    }


    /**
     * 切换菜单的开和关
     */
    public void switchMenu() {
        if (getScrollX() == 0) {
            //需要打开
            openMenu();
        } else {
            //需要关闭
            closeMenu();
        }
    }


}

