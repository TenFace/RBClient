package com.itheima.rbclient.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.itheima.rbclient.R;
import com.itheima.rbclient.ui.fragment.BrandFragment;
import com.itheima.rbclient.ui.fragment.CartFragment;
import com.itheima.rbclient.ui.fragment.FragmentInstanceManager;
import com.itheima.rbclient.ui.fragment.HomeFragment;
import com.itheima.rbclient.ui.fragment.MoreFragment;
import com.itheima.rbclient.ui.fragment.SearchFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {

    @InjectView(R.id.rg_content_fragment)
    RadioGroup mRadioGroup;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mFragmentManager = getSupportFragmentManager();

        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioGroup.check(R.id.rb_content_fragment_home);
    }


    //切换到底部导航的Fragment
    public void switchNavigationFragment(int checkedId) {
        mRadioGroup.check(checkedId);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        switch (checkedId) {
            case R.id.rb_content_fragment_home:
                switchFragment(FragmentInstanceManager.getInstance().getFragment(HomeFragment.class));
//                switchFragment(FragmentInstanceManager.getInstance().getFragment(TopicFragment.class));
                break;
            case R.id.rb_content_fragment_search:
                switchFragment(FragmentInstanceManager.getInstance().getFragment(SearchFragment.class));
                break;
            case R.id.rb_content_fragment_brand:
                switchFragment(FragmentInstanceManager.getInstance().getFragment(BrandFragment.class));
                break;
            case R.id.rb_content_fragment_shopping:
                switchFragment(FragmentInstanceManager.getInstance().getFragment(CartFragment.class));
                break;
            case R.id.rb_content_fragment_more:
                switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
                break;
            default:
                break;
        }
    }

    //提供方法切换Fragment，点击RadioButton的时候需要清空回退栈
    public void switchFragment(Fragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //循环的的pop回退栈
        int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
        while (backStackEntryCount > 0) {
            mFragmentManager.popBackStack();
            backStackEntryCount--;
        }
        transaction.replace(R.id.fl_container, fragment);
        transaction.commit();
    }

}
