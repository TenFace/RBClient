package com.itheima.rbclient;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.android.volley.toolbox.ImageLoader;

import org.senydevpkg.net.HttpLoader;

/**
 * 全局Application类
 * 通常做一些初始化工作，
 * Notice：记得在AndroidManife.xml清单文件中配置APP路径
 *
 * Created by xiongmc on 2016/4/26.
 */
public class App extends Application{

    /**
     * 全局上下文，方便使用
     */
    public static Context appliction;

    public static HttpLoader HL;
    public static ImageLoader IL;
    public static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        appliction = this;
        HL = HttpLoader.getInstance(appliction);
        IL = HL.getImageLoader();
        mHandler = new Handler();
    }
}
