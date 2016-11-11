package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.HelpDetail;
import com.itheima.rbclient.protocol.HelpDetailProtocol;
import com.itheima.rbclient.util.PrefUtils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by lenovo on 2016/8/3.
 */
public class HelpDetailFragment extends BaseFragment implements HttpLoader.HttpListener, View.OnClickListener {

    private TextView tvHelpdetailQuestion;
    private TextView tvHelpdetailAnswer;
    private Button btnhelpdetailBack;
    private HelpDetail helpDetail;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_detail, null);
        tvHelpdetailQuestion = (TextView) view.findViewById(R.id.tv_helpdetail_question);
        tvHelpdetailAnswer = (TextView) view.findViewById(R.id.tv_helpdetail_answer);
        btnhelpdetailBack = (Button) view.findViewById(R.id.btn_helpdetail_back);
        btnhelpdetailBack.setOnClickListener(this);

        intData();
        return view;
    }

    private void intData() {
        new HelpDetailProtocol().doRequest(App.HL, this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        Log.d("AAAAA", "-----onGetResponseSuccess-----");
        if (requestCode == RBConstants.REQUEST_CODE_HELP_DETAIL) {
            helpDetail = (HelpDetail) response;
            refreshView();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (helpDetail != null) {
            refreshView();
        }
    }

    private void refreshView() {
        Toast.makeText(getContext(), PrefUtils.getInt(getActivity(), "HelpDetail", 0)+"", Toast.LENGTH_SHORT).show();
        HelpDetail.HelpDetailListBean detail = helpDetail.getHelpDetailList().get(PrefUtils.getInt(getActivity(), "HelpDetail", 0));
        tvHelpdetailQuestion.setText(detail.getTitle());
        tvHelpdetailAnswer.setText(detail.getContent());
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(getActivity(), "error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_helpdetail_back:
                //回退上一级
                getFragmentManager().popBackStack();
                break;
        }
    }
}
