package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima.rbclient.R;

/**
 * Created by lenovo on 2016/8/3.
 */
public class FeedBackFragment extends BaseFragment implements View.OnClickListener {

    private Button btnFeedbackBack;
    private Button btnFeedbackSubmit;
    private EditText etFeedbackSuggest;
    private EditText etFeedbackUserinfo;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, null);
        btnFeedbackBack = (Button) view.findViewById(R.id.btn_feedback_back);
        btnFeedbackSubmit = (Button) view.findViewById(R.id.btn_feedback_submit);
        etFeedbackSuggest = (EditText) view.findViewById(R.id.et_feedback_suggest);
        etFeedbackUserinfo = (EditText) view.findViewById(R.id.et_feedback_userinfo);

        btnFeedbackBack.setOnClickListener(this);
        btnFeedbackSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_feedback_back:
                //回退至更多
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
                break;
            case R.id.btn_feedback_submit:
                //将反馈提交至服务器
                String suggest = etFeedbackSuggest.getText().toString();
                String usetinfo = etFeedbackUserinfo.getText().toString();
                Toast.makeText(getActivity(), "建议：" + suggest + "/n 用户信息：" + usetinfo, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
