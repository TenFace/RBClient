package com.itheima.rbclient.ui.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.util.CommonUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;


/**
 * 负责管理界面加载数据的逻辑
 *
 * @author Administrator
 */
public abstract class LoadingPage extends FrameLayout implements HttpLoader.HttpListener {
    //定义3种状态常量
    enum PageState {
        STATE_LOADING,//加载中的状态
        STATE_ERROR,//加载失败的状态
        STATE_SUCCESS;//加载成功的状态
    }

    private PageState mState = PageState.STATE_LOADING;//表示界面当前的state，默认是加载中
    private View loadingView;
    private View errorView;
    private View successView;

    public LoadingPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLoadingPage();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLoadingPage();
    }

    public LoadingPage(Context context) {
        super(context);
        initLoadingPage();
    }

    public void setmState(PageState mState) {
        this.mState = mState;
        showPage();
    }

    /**
     * 天然地往LoadingPage中添加3个view
     */
    private void initLoadingPage() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //1.依次添加3个view对象
        if (loadingView == null) {
            loadingView = View.inflate(getContext(), R.layout.page_loading, null);
        }
        addView(loadingView, params);

        if (errorView == null) {
            errorView = createErrorView();
        }
        addView(errorView, params);

        if (successView == null) {
            successView = createSuccessView();//需要不固定的successView
        }
        if (successView == null) {
            throw new IllegalArgumentException("The method createSuccessView() can not return null!");
        } else {
            CommonUtil.removeSelfFromParent(successView);//5.0之后系统不用调用该方法也可（因为FragmentManager已经不会对Fragment进行包裹）
            addView(successView, params);
        }

        //2.显示默认的loadingView
        showPage();
    }

    public void getData(){
        initDataFromService();
    }

    protected abstract void initDataFromService();

    public View createErrorView() {
        View view = View.inflate(getContext(), R.layout.page_error, null);
        ImageView page_iv = (ImageView) view.findViewById(R.id.page_iv);
        page_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.先显示loadingView
                mState = PageState.STATE_LOADING;
                showPage();
            }
        });
        return view;
    }

    /**
     * 根据当前的mState显示对应的View
     */
    private void showPage() {
        //1.先隐藏所有的view
        loadingView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
        successView.setVisibility(View.INVISIBLE);
        //2.谁的状态谁显示
        switch (mState) {
            case STATE_LOADING://加载中的状态
                loadingView.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR://加载失败的状态
                errorView.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCESS://加载成功的状态
                successView.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 获取successView，由于每个界面的successView都不一样，那么应该由每个界面自己实现
     *
     * @return
     */
    public abstract View createSuccessView();

  @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(App.appliction, "error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
        mState = PageState.STATE_ERROR;
        showPage();
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        mState = PageState.STATE_SUCCESS;
        fillData(requestCode,response);
        showPage();
    }

    /** 获取成功 */
    protected abstract void fillData(int requestCode, IResponse response);

    public HttpLoader.HttpListener getListener(){
        HttpLoader.HttpListener listener = this;
        return listener;
    }
}
