package com.itheima.rbclient.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xuan on 2016/8/6.
 */
public class OrderViewPager extends ViewPager{
    private int startX;
    private int startY;

    public OrderViewPager(Context context) {
        super(context);
    }

    public OrderViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int dx = endX - startX;
                int dy = endY - startY;

                if (Math.abs(dx) > Math.abs(dy)){//左右滑动
                    //请求父类拦截事件
                    getParent().requestDisallowInterceptTouchEvent(true);
                }else {

                }

                break;
            case MotionEvent.ACTION_UP:

                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
