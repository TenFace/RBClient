package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.HelpCenterAdapter;
import com.itheima.rbclient.bean.Help;
import com.itheima.rbclient.protocol.HelperProtocol;
import com.itheima.rbclient.util.PrefUtils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;


public class HelpCenterFragment extends BaseFragment implements View.OnClickListener, HttpLoader.HttpListener {

    protected static final String TAG = "BaseFragment";
    private Button btnHelpcenterBack;
    private HelpCenterAdapter mAdapter;
    private ListView lvHelpcenter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_center, null);

        btnHelpcenterBack = (Button) view.findViewById(R.id.btn_helpcenter_back);
        lvHelpcenter = (ListView) view.findViewById(R.id.lv_helpcenter);
        intData();

        btnHelpcenterBack.setOnClickListener(this);
        return view;
    }

    private void intData() {
        new HelperProtocol().doRequest(App.HL, this).setTag(this);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_helpcenter_back:
                //回退上一级
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
                break;
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        Log.d("AAAAA", "-----onGetResponseSuccess-----");
        if (requestCode == RBConstants.REQUEST_CODE_HELP) {
            Help help = (Help) response;
            if (mAdapter == null) {
                mAdapter = new HelpCenterAdapter((ArrayList<Help.HelpListBean>) help.getHelpList());
            } else {
                mAdapter.notifyDataSetChanged();
            }
            lvHelpcenter.setAdapter(mAdapter);
            lvHelpcenter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PrefUtils.putInt(getActivity(), "HelpDetail", position);
                    swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(HelpDetailFragment.class), true);

                }
            });
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(getActivity(), "error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
