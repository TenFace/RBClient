package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.itheima.rbclient.R;
import com.itheima.rbclient.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：pyf on 2016/8/8 19:55
 * <p/>
 * 邮箱：1173408486@qq.com
 */

public class SubmissionSuccessFragment extends BaseFragment {
    @InjectView(R.id.tv_order_num)
    TextView tvOrderNum;
    @InjectView(R.id.tv_payable)
    TextView tvPayable;
    @InjectView(R.id._pay_way)
    TextView PayWay;
    @InjectView(R.id.btn_go)
    Button btnGo;
    @InjectView(R.id.btn_see)
    Button btnSee;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_success_submission, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_go, R.id.btn_see})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go:
                mMainActivity.switchFragment(new HomeFragment());
                break;
            case R.id.btn_see:
                ToastUtils.showToast("跳转订单");
                mMainActivity.switchFragment(new OrderDetailFragment());
                break;
        }
    }
}
