package com.itheima.rbclient.ui.anim;

import android.view.View;

/**
 * 执行paddingTop变化的动画
 *
 * @author Administrator
 */
public class PaddingTopAnim extends BaseAnim {

    public PaddingTopAnim(View target, int startValue, int endValue) {
        super(target, startValue, endValue);
    }

    @Override
    protected void doAnim(int animatedValue) {
        //将动画的值设置为view的当前的paddingTop
        target.setPadding(target.getPaddingLeft(), animatedValue, target.getPaddingRight()
                , target.getPaddingBottom());
    }


}
