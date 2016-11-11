package com.itheima.rbclient.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.RegisterResponse;
import com.itheima.rbclient.util.DrawableUtil;
import com.itheima.rbclient.util.PrefUtils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xuan on 2016/8/3.
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener, HttpLoader.HttpListener {
    @InjectView(R.id.et_username)
    EditText et_Username;
    @InjectView(R.id.et_password)
    EditText et_Password;
    /**确认密码*/
    @InjectView(R.id.et_password2)
    EditText et_Password2;

    @InjectView(R.id.btn_register)
    Button btn_register;
    private String username;
    private String password1;
    private String password2;
    private TextView tv_title;
    private Button bt_title_left;
    private Button bt_title_right;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, null);
        ButterKnife.inject(this,view);
        initView();

        btn_register.setOnClickListener(this);
        return view;
    }

    /**获得用户名和密码*/
    private void getUsernameAndPassword() {
        //获得用户名
        username = et_Username.getText().toString().trim();
        //获取两次输入的密码
        password1 = et_Password.getText().toString().trim();
        password2 = et_Password2.getText().toString().trim();

    }

    /**初始化登录页面的view*/
    private void initView() {
        float[] header = new float[]{20,20,20,20,0,0,0,0};
        Drawable headerDrawable = DrawableUtil.generateDrawable(Color.BLACK,header);
        et_Username.setBackgroundDrawable(headerDrawable);
        float[] middle = new float[]{0,0,0,0,0,0,0,0};
        Drawable middleDrawable = DrawableUtil.generateDrawable(Color.BLACK,middle);
        et_Password.setBackgroundDrawable(middleDrawable);
        float[] end = new float[]{0,0,0,0,20,20,20,20};
        Drawable endDrawable = DrawableUtil.generateDrawable(Color.BLACK,end);
        et_Password2.setBackgroundDrawable(endDrawable);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        HttpParams params = new HttpParams();
        getUsernameAndPassword();
        //判断两次输入的密码是否相同以及用户名的校验
        if (checkoutPassword(password1,password2)) {
            params.put("username", username);
            params.put("password", password1);

            App.HL.post(RBConstants.URL_REGISTER, params,
                    RegisterResponse.class, RBConstants.REQUEST_CODE_REGISTER, this);
        }
}

    /**检查两次输入的密码是否一致,不一致或者不符合要求,return*/
    private boolean checkoutPassword(String password1, String password2) {
        if (password1 == null || password2 == null){
            Toast.makeText(getActivity(),"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (!password1.equals(password2)){
            Toast.makeText(getActivity(),"两次密码不一致",Toast.LENGTH_SHORT).show();
            return false;
        }else if (username == null){
            Toast.makeText(getActivity(),"用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //更改的部分,上次的原因是直接判断的requestCode,实际上点击注册应该根据返回的response值是error
    // 还是register来判断注册的成功或者失败
    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_REGISTER) {
            RegisterResponse registerResponse = (RegisterResponse) response;
            if ("error".equals(registerResponse.response)) {//注册失败
                Toast.makeText(getActivity(), registerResponse.error, Toast.LENGTH_SHORT).show();
            } else {//注册成功
                //获取userid
                int userid = registerResponse.userInfo.userid;
                PrefUtils.putInt(getActivity(), RBConstants.USERID, userid);
                //保存用户名
                PrefUtils.putString(getActivity(), RBConstants.USERNAME,username);
                //根据userid作为头信息进入账户中心
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(UserCenterFragment.class),true);
            }
        }
    }


    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Log.d("ddd", "onGetResponseError: " + requestCode);
        Toast.makeText(getActivity(),"error : "+error.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
