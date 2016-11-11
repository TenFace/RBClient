package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.SearchResultAdapter;
import com.itheima.rbclient.bean.SearchList;
import com.itheima.rbclient.bean.SearchText;
import com.itheima.rbclient.eventBean.EventId;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.ALog;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：pyf on 2016/8/3 10:56
 * <p/>
 * 邮箱：1173408486@qq.com
 */

public class SearchProjectFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, HttpLoader.HttpListener {


    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.rb_sales)
    RadioButton rbSales;
    @InjectView(R.id.rb_price)
    RadioButton rbPrice;
    @InjectView(R.id.rg_select)
    RadioGroup rgSelect;
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.ll_search_result)
    LinearLayout llSearchResult;
    @InjectView(R.id.rb_praise)
    RadioButton rbPraise;
    @InjectView(R.id.rb_time)
    RadioButton rbTime;
    @InjectView(R.id.tv_result)
    TextView tvResult;
    @InjectView(R.id.ll_empty)
    LinearLayout llEmpty;

    private String tv_search;

    private boolean flag = true;
    private String et_search;
    private SearchResultAdapter mAdapter;
    private ArrayList list = new ArrayList();
    private HttpParams params;
    private long num = 1;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return View.inflate(App.appliction, R.layout.search_result_success, null);
    }

    private static final String TAG = "SearchProjectFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);

        initView();
        initData();
        return rootView;
    }

    private void initView() {

        rgSelect.setOnCheckedChangeListener(this);
        //rgSelect.check(R.id.rb_sales);
    }

    private void initData() {
        EventBus.getDefault().register(this);
        //listview.setAdapter(new SearchResultAdapter());
    }

    @Subscribe(sticky = true)
    public void onEvent(SearchText searchText) {
        Toast.makeText(getActivity(), "searchText" + searchText.getText(), Toast.LENGTH_SHORT).show();
        et_search = searchText.getText();
        /*if ("奶粉".equals(et_search)){
            params = new HttpParams();
            params.put("keyword","奶粉").put("page","0").put("pageNum","10").put("orderby","saleDown");
        }*/

        RequestData("saleDown",searchText.getText());

    }

    private void RequestData(String orderby,String text) {
        params = new HttpParams();
        params.put("keyword", text).put("page", "1").put("pageNum", "10").put("orderby", orderby);

        String tru = "http://192.168.78.38:8080/RedBabyServer/";
        Log.i(TAG, "RequestData:===== "+RBConstants.URL_SEARCH_LIST);
        App.HL.get(RBConstants.URL_SEARCH_LIST, params, SearchList.class,
                RBConstants.REQUEST_CODE_SEARCH_LIST, this);
       // Logger.d("====="+RBConstants.URL_SERVER + params);
        ALog.e(RBConstants.URL_SERVER + ":::::::" + params.toString());
    }
   /* private void RequestData(String orderby) {
        params = new HttpParams();
        params.put("keyword", et_search).put("page", "1").put("pageNum", "10").put("orderby", orderby);
        App.HL.get(RBConstants.URL_SEARCH_LIST, params, SearchList.class,
                RBConstants.REQUEST_CODE_SEARCH_LIST, this);
    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.reset(this);
    }

    private boolean isUp = true;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        list.clear();
        num--;
        switch (checkedId) {
            case R.id.rb_sales:
                RequestData("saleDown",et_search);
                break;
            case R.id.rb_price:
                if (isUp) {
                    Toast.makeText(App.appliction,"点击了",Toast.LENGTH_SHORT).show();
                    RequestData("priceUp",et_search);
                } else {
                    RequestData("priceDown",et_search);
                }
                isUp = !isUp;
                break;
            case R.id.rb_praise:
                RequestData("commentDown",et_search);
                break;
            case R.id.rb_time:
                RequestData("shelvesDown",et_search);
                break;
        }

    }

    @OnClick(R.id.tv_back)
    public void onClick() {
       // mMainActivity.swi
        // 23tchFragment(new SearchFragment());
        getActivity().getSupportFragmentManager()
                .popBackStack();
    }


    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
      //  llEmpty.setVisibility(View.GONE);
        if (requestCode == RBConstants.REQUEST_CODE_SEARCH_LIST && num == 1) {
            SearchList searchList = (SearchList) response;
            final SearchList finalSearchList = (SearchList) response;
            tvResult.setText("搜索结果 (" + searchList.getProductList().size() + "条)");
            list.addAll(searchList.getProductList());
            Log.i(TAG, "onGetResponseSuccess: " + list);
            mAdapter = new SearchResultAdapter(list);
            listview.setAdapter(mAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EventBus.getDefault().postSticky(new EventId(finalSearchList.getProductList().get(position).getId()));
                    swichToChildFragment(new MarketDetailFragment(), true);
                }
            });
            num++;

        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        llEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(true);
    }
}
