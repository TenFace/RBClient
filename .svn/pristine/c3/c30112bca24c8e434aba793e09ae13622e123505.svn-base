package com.itheima.rbclient.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by xuan on 2016/8/3.
 */
public class DrawableUtil {

    /**
     * 获得shape
     * @param color  边框颜色
     * @param radii  //长度为8,两位代表一个角,分别是左上,右上,左下,右下
     * @return
     */
    public static Drawable generateDrawable(int color,float[] radii){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(1,color);
        drawable.setCornerRadii(radii);
        return drawable;
    }
}
