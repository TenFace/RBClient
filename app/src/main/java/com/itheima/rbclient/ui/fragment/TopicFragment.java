package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.TopicAdapter;
import com.itheima.rbclient.bean.TopicResponse;
import com.itheima.rbclient.protocol.TopicProtocol;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by xiongmc on 2016/6/7.
 */
public class TopicFragment extends BaseFragment implements HttpLoader.HttpListener {

    private ListView mListView;
    private TopicAdapter mAdapter;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = new ListView(getActivity());
        initData();
        return mListView;
    }

    private void initData() {
        new TopicProtocol().doRequest(App.HL, this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        Log.d("AAAAA", "-----onGetResponseSuccess-----");
        if (requestCode == RBConstants.REQUEST_CODE_TOPIC) {
            TopicResponse topicResponse = (TopicResponse) response;
            if (mAdapter == null) {
                mAdapter = new TopicAdapter(topicResponse.topic);
            } else {
                mAdapter.notifyDataSetChanged(topicResponse.topic);
            }
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(getActivity(), "error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }
}
