package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.HomeListAdapter;
import com.itheima.rbclient.adapter.HomeTopicAdapter;
import com.itheima.rbclient.bean.HomeCategory;
import com.itheima.rbclient.bean.HomeTopicAppInfo;
import com.itheima.rbclient.bean.SearchText;
import com.itheima.rbclient.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment implements HttpLoader.HttpListener {

    List<HomeTopicAppInfo.HomeTopicBean> homeTopicBeens;
    HomeTopicAdapter mAdapter;
    @InjectView(R.id.lv_home_listview)
    ListView lv_home_listview;
    @InjectView(R.id.vp_home_viewpager)
    ViewPager vp_home_viewpager;
    @InjectView(R.id.ib_search)
    ImageButton ibSearch;
    @InjectView(R.id.et_sear)
    EditText etSear;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            //让ViewPager选中下一页
            vp_home_viewpager.setCurrentItem(vp_home_viewpager.getCurrentItem() + 1);
            //重新发消息
            handler.sendEmptyMessageDelayed(0, 2500);
            //LogUtil.e(this, "handleresg");
        }

        ;
    };
    private List<ImageView> mSlideViews;
    private View view;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        innitView();

        requestNetData();
        processLogic();
        lv_home_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        LogUtil.d(this,"切换限时抢购");
                        swichToChildFragment(new FlashSaleFragement(),true);
                        break;
                    case 1:
                        LogUtil.d(this,"切换促销快报");
                        swichToChildFragment(new SalesPromotionFragment(),true);
                    break;
                    case 2:
                        LogUtil.d(this,"切换新品上架");
                        swichToChildFragment(new NewproductFragment(),true);
                        break;
                    case 3:
                        LogUtil.d(this,"切换热卖单品");
                        swichToChildFragment(new HotproductFragment(),true);
                        break;
                    case 5:
                        swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(ClassificationFragment.class),true);
                        break;
                }
            }
        });
        return view;
    }


    private void innitView() {
        mSlideViews = new ArrayList<ImageView>();
        mSlideViews.add((ImageView) view.findViewById(R.id.home_imgPoint0));
        mSlideViews.add((ImageView) view.findViewById(R.id.home_imgPoint1));
        mSlideViews.add((ImageView) view.findViewById(R.id.home_imgPoint2));
        mSlideViews.add((ImageView) view.findViewById(R.id.home_imgPoint3));
        mSlideViews.add((ImageView) view.findViewById(R.id.home_imgPoint4));
        mSlideViews.add((ImageView) view.findViewById(R.id.home_imgPoint5));
        mSlideViews.add((ImageView) view.findViewById(R.id.home_imgPoint5));
    }

    @Override
    public void onStart() {
        super.onStart();
        //重新发消息
        handler.sendEmptyMessageDelayed(0, 2500);
    }

    @Override
    public void onStop() {
        super.onStop();
        //移除消息
        handler.removeMessages(0);
    }

    /**
     * listview设置数据
     */
    protected void processLogic() {
        List<HomeCategory> categroy = new ArrayList<HomeCategory>();
        categroy.add(new HomeCategory(R.drawable.home_classify_01, "限时抢购"));
        categroy.add(new HomeCategory(R.drawable.home_classify_02, "促销快报"));
        categroy.add(new HomeCategory(R.drawable.home_classify_03, "新品上架"));
        categroy.add(new HomeCategory(R.drawable.home_classify_04, "热卖单品"));
        categroy.add(new HomeCategory(R.drawable.home_classify_05, "推荐品牌"));
        categroy.add(new HomeCategory(R.drawable.home_classify_05, "分类"));
        lv_home_listview.setAdapter(new HomeListAdapter(this, categroy));
    }

    /**
     * 服务器请求数据
     */
    protected void requestNetData() {
        String url = RBConstants.URL_SERVER + "home";
        HttpParams params = new HttpParams();
        Class<? extends IResponse> clazz = HomeTopicAppInfo.class;
        int requestcode = RBConstants.REQUEST_CODE_HomeTopic;
        App.HL.get(url, params, clazz, requestcode, this);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    /**
     * 数据请求成功
     */
    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_HomeTopic) {
            homeTopicBeens = ((HomeTopicAppInfo) response).getHomeTopic();

            if (mAdapter == null) {
                mAdapter = new HomeTopicAdapter(homeTopicBeens);
            }
            //设置适配器
            vp_home_viewpager.setAdapter(mAdapter);
            vp_home_viewpager.setCurrentItem(100);
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @OnClick(R.id.ib_search)
    public void onClick() {
        String tv_search = etSear.getText().toString().trim();
        if (!TextUtils.isEmpty(tv_search)) {

            EventBus.getDefault().postSticky(new SearchText(tv_search));
            swichToChildFragment(new SearchProjectFragment(), true);
        } else {
            Toast.makeText(getActivity(), "请输入搜索关键字", Toast.LENGTH_SHORT).show();
        }
    }
}
