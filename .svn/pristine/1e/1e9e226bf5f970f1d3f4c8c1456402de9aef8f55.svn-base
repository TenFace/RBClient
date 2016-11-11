package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by lenovo on 2016/8/4.
 */
public abstract class BaseListViewFragment extends BaseFragment implements View.OnClickListener {
    LoadingPage loadingPage;
    protected PullToRefreshListView ptr_listView;
    protected ListView listView;
    private LinearLayout rootView;
    private Button btnFavoriteBack;
    protected Button btnFavoriteClear;
    protected TextView tvFavoriteTitle;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (LinearLayout) View.inflate(getContext(), R.layout.fragment_favorite, null);
        initView();

        if (loadingPage == null) {
            loadingPage = new LoadingPage(getContext()) {
                @Override
                protected void initDataFromService() {
                    getDataFromService();
                }

                @Override
                public View createSuccessView() {
                    btnFavoriteClear.setVisibility(View.VISIBLE);
                    ptr_listView = (PullToRefreshListView) View.inflate(App.appliction, R.layout.ptr_listview, null);
                    ptr_listView.setMode(PullToRefreshBase.Mode.BOTH);
                    listView = ptr_listView.getRefreshableView();
                    ptr_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                        @Override
                        public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                            loadingPage.getData();
                        }
                    });
                    return ptr_listView;
                }

                @Override
                protected void fillData(int requestCode, IResponse response) {
                    setData(requestCode, response);
                }

                @Override
                public View createErrorView() {
                    View child = initErrorView();
                    return child;
                }
            };
        }
        rootView.addView(loadingPage, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return rootView;
    }

    protected void initView() {
        btnFavoriteBack = (Button) rootView.findViewById(R.id.btn_favorite_back);
        btnFavoriteClear = (Button) rootView.findViewById(R.id.btn_favorite_clear);
        tvFavoriteTitle = (TextView) rootView.findViewById(R.id.tv_favorite_title);

        setViewText();

        btnFavoriteBack.setOnClickListener(this);
        btnFavoriteClear.setOnClickListener(this);
    }

    protected abstract void setViewText();

    protected abstract View initErrorView();

    protected abstract void getDataFromService();

    protected abstract void setData(int requestCode, IResponse response);

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_favorite_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.btn_favorite_clear:
                RightButtonClick();
                break;
        }
    }

    protected abstract void RightButtonClick();

    @Override
    public void onStart() {
        super.onStart();
        if (loadingPage != null){
            loadingPage.getData();
        }
    }
}
