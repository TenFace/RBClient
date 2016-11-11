package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.rbclient.R;

/**
 * Created by lenovo on 2016/8/3.
 */
public class AboutFragment extends BaseFragment implements View.OnClickListener {

    private Button btnAboutBack;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, null);
        btnAboutBack = (Button) view.findViewById(R.id.btn_about_back);
        btnAboutBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_about_back:
                //回退至更多
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
                break;
        }
    }
}
