package com.itheima.rbclient.util;

import android.widget.Toast;

import com.itheima.rbclient.App;

/**
 * Created by lenovo on 2016/7/23.
 */
public class ToastUtils {
    private static Toast toast;
    public static void showToast(String text) {
        if (toast == null){
            toast = Toast.makeText(App.appliction, text, Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);
        }
        toast.show();
    }
}
