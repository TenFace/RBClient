package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.SearchAdapter;
import com.itheima.rbclient.adapter.SearchHistoryAdapter;
import com.itheima.rbclient.bean.SearchResponse;
import com.itheima.rbclient.bean.SearchText;
import com.itheima.rbclient.dao.SearchHistoryDaoImpl;
import com.itheima.rbclient.ui.anim.PaddingTopAnim;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class SearchFragment extends BaseFragment implements HttpLoader.HttpListener {


    @InjectView(R.id.search_et_input)
    EditText searchEtInput;
    @InjectView(R.id.btn_search)
    Button btnSearch;
    @InjectView(R.id.lv_item1)
    ListView lvItem;
    @InjectView(R.id.lv_item2)
    ListView lvItem2;
    @InjectView(R.id.iv_arrow1)
    ImageView ivArrow1;
    @InjectView(R.id.iv_arrow2)
    ImageView ivArrow2;
    @InjectView(R.id.ll_his)
    LinearLayout llHis;


    private ArrayList<String> searchKeywords = new ArrayList<>();
    private SearchAdapter adapter;
    private int maxHeight = 0;
    private int minHeight;
    private int num = 1;
    private PaddingTopAnim anim;
    private int lvItem2height;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        initView();
        initData();
        return rootView;
    }

    /**
     * 网络请求数据
     */
    private void initData() {
        //App.HL.get(RBConstants.URL_SEARCH, SearchResponse.class,60,this);
        App.HL.get(RBConstants.URL_SEARCH, null, SearchResponse.class, RBConstants.REQUEST_CODE_SEARCH, this);

        lvItem2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                lvItem2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                lvItem2height = -1 * lvItem2.getHeight() - 10000050;
                lvItem2.setPadding(0, lvItem2height, 0, 0);
                RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setDuration(0);
                ra.setFillAfter(true);
                ivArrow2.startAnimation(ra);
            }
        });
    }

    private void initView() {
        adapter = new SearchAdapter(searchKeywords);
        lvItem.setAdapter(adapter);
        ArrayList<String> list = (ArrayList<String>) SearchHistoryDaoImpl.query();
        SearchHistoryAdapter adapter1 = new SearchHistoryAdapter(list);

        lvItem2.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        String tv_search = searchEtInput.getText().toString().trim();
        if (!TextUtils.isEmpty(tv_search)) {

            EventBus.getDefault().postSticky(new SearchText(tv_search));
            SearchHistoryDaoImpl.insert(tv_search);
            swichToChildFragment(new SearchProjectFragment(), true);
        } else {
            Toast.makeText(getActivity(), "请输入搜索关键字", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 返回成功
     *
     * @param requestCode response对应的requestCode
     * @param response    返回的response
     */
    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_SEARCH && response != null && num == 1) {
            SearchResponse bean = (SearchResponse) response;
            searchKeywords.addAll(bean.searchKeywords);
            // adapter.setSearchKeywords(searchKeywords);
            adapter.notifyDataSetChanged();
            lvItem.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    lvItem.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    minHeight = -1 * lvItem.getMeasuredHeight();
                    lvItem.setPadding(0, minHeight, 0, 0);
                    RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    ra.setDuration(0);
                    ra.setFillAfter(true);
                    ivArrow1.startAnimation(ra);
                }

            });
            num++;
        }
    }

    private static final String TAG = "SearchFragment";

    /**
     * 返回失败
     *
     * @param requestCode 请求码
     * @param error       异常详情
     */
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Log.i(TAG, "onGetResponseError: " + error + "requestCode" + requestCode);
    }

    private boolean isHotExtend = false;
    private boolean isHisExtend = false;//是否展开了

    @OnClick({R.id.tv_hot, R.id.tv_his})
    public void onClick(View view) {
     /*   if (isAniming) {
            return;
        }*/
        switch (view.getId()) {
            case R.id.tv_hot:
                anim = null;
                if (isHotExtend) {
                    //执行收缩动画
                    anim = new PaddingTopAnim(lvItem, maxHeight, minHeight);
                    RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    ra.setDuration(350);
                    ra.setFillAfter(true);
                    ivArrow1.startAnimation(ra);
                } else {
                    //执行伸展动画
                    anim = new PaddingTopAnim(lvItem, minHeight, maxHeight);
                    RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    ra.setDuration(350);
                    ra.setFillAfter(true);
                    ivArrow1.startAnimation(ra);
                }
                anim.start(350);
                isHotExtend = !isHotExtend;
                break;
            case R.id.tv_his:
                anim = null;
                if (isHisExtend) {
                    //执行收缩动画
                    anim = new PaddingTopAnim(lvItem2, maxHeight, lvItem2height);
                    RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    ra.setDuration(350);
                    ra.setFillAfter(true);
                    ivArrow2.startAnimation(ra);
                } else {
                    //执行伸展动画
                    anim = new PaddingTopAnim(lvItem2, lvItem2height, maxHeight);
                    RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    ra.setDuration(350);
                    ra.setFillAfter(true);
                    ivArrow2.startAnimation(ra);
                }
                anim.start(350);
                isHisExtend = !isHisExtend;
                break;
        }
    }
}
