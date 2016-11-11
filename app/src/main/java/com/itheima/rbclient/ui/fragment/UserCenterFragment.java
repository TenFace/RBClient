package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.SourceAddress;
import com.itheima.rbclient.bean.UserInfo;
import com.itheima.rbclient.protocol.UserInfoProtocol;
import com.itheima.rbclient.util.PrefUtils;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by lenovo on 2016/8/2.
 */
public class UserCenterFragment extends BaseFragment implements View.OnClickListener, HttpLoader.HttpListener {

    private TextView tvUsernameUser;
    private TextView tvUsernameMember;
    private TextView tvUsernamePoints;
    private RelativeLayout rlUsernameOrder;
    private TextView tvUsernameOrder;
    private RelativeLayout rlUsernameAddress;
    private RelativeLayout rlUsernameCoupon;
    private TextView tvUsernameCoupon;
    private RelativeLayout rlUsernameFavorites;
    private TextView tvUsernameFavorites;
    private Button btnUsernameBack;
    private Button btnUsernameLogout;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_center, null);
        initView(view);

        intData();
        return view;
    }


    private void initView(View view) {
        tvUsernameUser = (TextView) view.findViewById(R.id.tv_username_user);
        tvUsernameMember = (TextView) view.findViewById(R.id.tv_username_member);
        tvUsernamePoints = (TextView) view.findViewById(R.id.tv_username_points);
        rlUsernameOrder = (RelativeLayout) view.findViewById(R.id.rl_username_order);
        rlUsernameAddress = (RelativeLayout) view.findViewById(R.id.rl_username_address);
        rlUsernameCoupon = (RelativeLayout) view.findViewById(R.id.rl_username_coupon);
        rlUsernameFavorites = (RelativeLayout) view.findViewById(R.id.rl_username_favorites);
        tvUsernameOrder = (TextView) view.findViewById(R.id.tv_username_order);
        tvUsernameCoupon = (TextView) view.findViewById(R.id.tv_username_coupon);
        tvUsernameFavorites = (TextView) view.findViewById(R.id.tv_username_favorites);
        btnUsernameBack = (Button) view.findViewById(R.id.btn_username_back);
        btnUsernameLogout = (Button) view.findViewById(R.id.btn_username_logout);

        tvUsernameUser.setText(PrefUtils.getString(getActivity(), RBConstants.USERNAME,""));

        rlUsernameOrder.setOnClickListener(this);
        rlUsernameAddress.setOnClickListener(this);
        rlUsernameCoupon.setOnClickListener(this);
        rlUsernameFavorites.setOnClickListener(this);
        btnUsernameBack.setOnClickListener(this);
        btnUsernameLogout.setOnClickListener(this);
    }

    private void intData() {
        Log.d("UserCenter", "-----onGetResponse-----");
        new UserInfoProtocol().doRequest(App.HL, this).setTag(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_username_back:
                //回退至更多
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
                break;
            case R.id.btn_username_logout:
                //退出登录
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
                PrefUtils.putInt(getActivity(), RBConstants.USERID, 0);
                break;
            case R.id.rl_username_order:
                //我的订单
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(MyOrderFormFragment.class),true);
                break;
            case R.id.rl_username_address:
                //地址管理
                SourceAddress sourceAddress = new SourceAddress();
                sourceAddress.setSource(false);
                EventBus.getDefault().postSticky(sourceAddress);
                swichToChildFragment(new AddressFragment(), true);
                break;
            case R.id.rl_username_coupon:
                //优惠券
                break;
            case R.id.rl_username_favorites:
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(FavoriteFragment.class), true);
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        Log.d("UserCenter", "-----onGetResponseSuccess-----");
        if (requestCode == RBConstants.REQUEST_CODE_USERINFO) {
            UserInfo userInfo = (UserInfo) response;
            if ("error".equals(userInfo.getResponse())){
                Toast.makeText(getActivity(),userInfo.error,Toast.LENGTH_SHORT).show();
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(LoginFragment.class),true);
            }else {
                UserInfo.UserInfoBean info = userInfo.getUserInfo();
                tvUsernameMember.setText("会员等级：" + info.getLevel());
                tvUsernamePoints.setText("总积分：" + info.getBonus());
                tvUsernameOrder.setText("我的订单(" + info.getOrderCount() + ")");
                tvUsernameFavorites.setText("收藏夹(" + info.getFavoritesCount() + ")");
            }
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(App.appliction, "error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        intData();
    }
}
