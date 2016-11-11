package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.util.PrefUtils;

public class MoreFragment extends BaseFragment implements OnClickListener {
    private RelativeLayout rl_more_username, rl_more_record, rl_more_help, rl_more_feedback, rl_more_about;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, null);
        rl_more_username = (RelativeLayout) view.findViewById(R.id.rl_more_username);
        rl_more_record = (RelativeLayout) view.findViewById(R.id.rl_more_record);
        rl_more_help = (RelativeLayout) view.findViewById(R.id.rl_more_help);
        rl_more_feedback = (RelativeLayout) view.findViewById(R.id.rl_more_feedback);
        rl_more_about = (RelativeLayout) view.findViewById(R.id.rl_more_about);
        rl_more_username.setOnClickListener(this);
        rl_more_record.setOnClickListener(this);
        rl_more_help.setOnClickListener(this);
        rl_more_feedback.setOnClickListener(this);
        rl_more_about.setOnClickListener(this);

        return view;
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.rl_more_username:
				if (PrefUtils.getInt(getActivity(), RBConstants.USERID, 0) == 0) {
					swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(LoginFragment.class), true);
				}else {
					swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(UserCenterFragment.class), true);
				}
				break;
			case R.id.rl_more_record:
				swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(RecordFragment.class), true);
				break;
			case R.id.rl_more_help:
				swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(HelpCenterFragment.class), true);
				break;
			case R.id.rl_more_feedback:
				swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(FeedBackFragment.class), true);
				break;
			case R.id.rl_more_about:
				swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(AboutFragment.class), true);
				break;
		}
	}
}
