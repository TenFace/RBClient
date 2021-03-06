package com.itheima.rbclient.ui.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.FavoriteAdapter;
import com.itheima.rbclient.bean.Favorite;
import com.itheima.rbclient.eventBean.EventId;
import com.itheima.rbclient.util.NetUtil;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/8/3.
 */
public class FavoriteFragment extends BaseListViewFragment {

    private FavoriteAdapter mAdapter;
    private List<Favorite.ProductListBean> list = new ArrayList<>();

    @Override
    protected void setViewText() {
    }

    @Override
    protected View initErrorView() {
        btnFavoriteClear.setVisibility(View.GONE);
        return View.inflate(App.appliction, R.layout.page_error_favorite, null);
    }

    @Override
    protected void getDataFromService() {
        intData();
    }

    @Override
    protected void setData(int requestCode, IResponse response) {
        Log.d("Favorite", "-----onGetResponseSuccess-----");
        if (requestCode == RBConstants.REQUEST_CODE_FAVORITE) {
            final Favorite favorite = (Favorite) response;
            list.addAll(favorite.getProductList());
            if (list.size() > 0) {
                if (mAdapter == null) {
                    mAdapter = new FavoriteAdapter(list);
                } else {
                    mAdapter.notifyDataSetChanged(list);
                }
                listView.setAdapter(mAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        EventBus.getDefault().postSticky(new EventId(Integer.parseInt(list.get(position).getId()) - 1));
                        swichToChildFragment(new MarketDetailFragment(), true);
                    }
                });
            }else {
                loadingPage.setmState(LoadingPage.PageState.STATE_ERROR);
            }
        }
        ptr_listView.onRefreshComplete();
    }

    @Override
    protected void RightButtonClick() {
        list.clear();
        mAdapter.notifyDataSetChanged(list);
        Toast.makeText(getContext(), "没有接口，只是清空了本地", Toast.LENGTH_SHORT).show();
        loadingPage.setmState(LoadingPage.PageState.STATE_ERROR);
    }


    private void intData() {
        Log.d("Favorite", "-----onGetResponse-----");
        if (ptr_listView != null && ptr_listView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START){
            list.clear();
        }
        HttpParams params = new HttpParams().put("page", list.size()/10 + "").put("pageNum", "10");
        Map<String, String> map = NetUtil.generateHeaders(App.appliction);
        //map.put(RBConstants.USERID, "" + PrefUtils.getInt(App.appliction, RBConstants.USERID, 0));
        params.addHeader(map);
        App.HL.get(RBConstants.URL_FAVORITE, params, Favorite.class, RBConstants.REQUEST_CODE_FAVORITE, loadingPage.getListener(), false);
    }
}
