package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.rbclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/5.
 */
public class PaywayFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.btn_back)
    Button btn_back;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payway,null);
        ButterKnife.inject(this,view);

        btn_back.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(JiesuanCenterFragment.class),true);
    }
}
