package com.itheima.rbclient.ui.fragment;

import android.view.View;
import android.widget.AdapterView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.adapter.FavoriteAdapter;
import com.itheima.rbclient.bean.Favorite;
import com.itheima.rbclient.bean.ProductDetail;
import com.itheima.rbclient.bean.Record;
import com.itheima.rbclient.eventBean.EventId;
import com.itheima.rbclient.protocol.UserInfoProtocol;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/8/9.
 */
public class RecordFragment extends BaseListViewFragment {

    private FavoriteAdapter mAdapter;
    private List<Favorite.ProductListBean> temp;

    @Override
    protected void setViewText() {
        tvFavoriteTitle.setText("浏览记录");
    }

    @Override
    protected View initErrorView() {
        return View.inflate(getActivity(), R.layout.page_error_record, null);
    }

    @Override
    protected void getDataFromService() {
        new UserInfoProtocol().doRequest(App.HL, loadingPage.getListener());
    }

    @Override
    protected void setData(int requestCode, IResponse response) {
        List<ProductDetail.ProductBean> productBeans = Record.getInstance().getProductBeans();
        if (productBeans.size() > 0) {
            temp = new ArrayList<>();
            for (ProductDetail.ProductBean bean : productBeans) {
                Favorite.ProductListBean favorite = new Favorite.ProductListBean();
                favorite.setName(bean.getName());
                favorite.setId(bean.getId() + "");
                favorite.setPrice(bean.getPrice() + "");
                favorite.setMarketPrice(bean.getMarketPrice() + "");
                temp.add(favorite);
            }
            if (mAdapter == null) {
                mAdapter = new FavoriteAdapter(temp);
            } else {
                mAdapter.notifyDataSetChanged(temp);
            }
            listView.setAdapter(mAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EventBus.getDefault().postSticky(new EventId(Integer.parseInt(temp.get(position).getId()) - 1));
                    swichToChildFragment(new MarketDetailFragment(), true);
                }
            });
        }else {
            loadingPage.setmState(LoadingPage.PageState.STATE_ERROR);
        }
    }

    @Override
    protected void RightButtonClick() {
        Record.getInstance().getProductBeans().clear();
        temp.clear();
        mAdapter.notifyDataSetChanged(temp);
        loadingPage.setmState(LoadingPage.PageState.STATE_ERROR);
    }
}
