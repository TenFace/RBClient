package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.Help;
import com.itheima.rbclient.ui.fragment.BaseFragment;
import com.itheima.rbclient.ui.fragment.FragmentInstanceManager;
import com.itheima.rbclient.ui.fragment.HelpDetailFragment;
import com.itheima.rbclient.util.PrefUtils;

import org.senydevpkg.base.BaseHolder;

/**
 * Created by lenovo on 2016/8/3.
 */
public class HelpCenterHolder extends BaseHolder<Help.HelpListBean> {

    private TextView tvItemHelp;

    public HelpCenterHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.appliction, R.layout.item_help, null);
        tvItemHelp = (TextView) view.findViewById(R.id.tv_item_help);
        return view;
    }

    @Override
    public void bindData(final Help.HelpListBean helpList) {
        tvItemHelp.setText(helpList.getTitle());
    }
}
