package com.itheima.rbclient.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.AddressAdapter;
import com.itheima.rbclient.bean.Address;
import com.itheima.rbclient.bean.SourceAddress;
import com.itheima.rbclient.util.NetUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/8/5.
 */
public class AddressFragment extends BaseListViewFragment {

    private AddressAdapter mAdapter;
    private List<Address.AddressListBean> list = new ArrayList<>();
    private SourceAddress sourceAddress;

    @Override
    protected void setViewText() {
        btnFavoriteClear.setText("新增地址");
        tvFavoriteTitle.setText("地址管理");

        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void onEvent(SourceAddress sourceAddress){
        this.sourceAddress = sourceAddress;
    }

    @Override
    protected View initErrorView() {
        return View.inflate(App.appliction, R.layout.page_error_address, null);
    }

    @Override
    protected void getDataFromService() {
        initData();
    }

    private void initData() {
        Log.d("Address", "-----onGetResponse-----");
        HttpParams params = new HttpParams();
        Map<String, String> map = NetUtil.generateHeaders(App.appliction);
        //map.put(RBConstants.USERID, "" + PrefUtils.getInt(App.appliction, RBConstants.USERID, 0));
        params.addHeader(map);
        App.HL.get(RBConstants.URL_ADDRESS_LIST, params, Address.class, RBConstants.REQUEST_CODE_ADDRESS_LIST, loadingPage.getListener(), false);
    }

    @Override
    protected void setData(int requestCode, IResponse response) {
        Log.d("Address", "-----onGetResponseSuccess-----");
        if (requestCode == RBConstants.REQUEST_CODE_ADDRESS_LIST) {
            Address address = (Address) response;
            list.clear();
            list.addAll(address.getAddressList());
            if (mAdapter == null) {
                mAdapter = new AddressAdapter(list);
                listView.setAdapter(mAdapter);
            } else {
                mAdapter.notifyDataSetChanged(list);
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Address.AddressListBean addressBean = list.get(position-1);

                    EventBus.getDefault().postSticky(addressBean);
                    EventBus.getDefault().postSticky(false);

                    if (!sourceAddress.isSource()) {
                        swichToChildFragment(new NewAddressFragment(), true);
                    }else {
                        getFragmentManager().popBackStack();
                    }
                }
            });
        }
        ptr_listView.onRefreshComplete();
    }

    @Override
    protected void RightButtonClick() {
        EventBus.getDefault().postSticky(true);
        swichToChildFragment(new NewAddressFragment(), true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
