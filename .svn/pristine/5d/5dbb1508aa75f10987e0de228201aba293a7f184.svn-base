package com.itheima.rbclient.ui.anim;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * 高度变化的动画
 *
 * @author Administrator
 */
public class HeightAnim extends BaseAnim {

    public HeightAnim(View target, int startValue, int endValue) {
        super(target, startValue, endValue);
    }

    @Override
    protected void doAnim(int animatedValue) {
        //将animatedValue设置给tv_des的高度
        LayoutParams params = target.getLayoutParams();
        params.height = animatedValue;
        target.setLayoutParams(params);

        //将动画的值的改变通过接口暴漏给外界
        if (listener != null) {
            listener.onHeightChange(animatedValue);
        }
    }

    private OnHeightChangeListener listener;

    public void setOnHeightChangeListener(OnHeightChangeListener listener) {
        this.listener = listener;
    }

    /**
     * 当height改变的监听器
     *
     * @author Administrator
     */
    public interface OnHeightChangeListener {
        void onHeightChange(int animatedValue);
    }



}
