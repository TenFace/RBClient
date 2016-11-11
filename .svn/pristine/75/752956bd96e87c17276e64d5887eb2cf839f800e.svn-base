package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.Address;

import org.senydevpkg.base.BaseHolder;

/**
 * Created by lenovo on 2016/8/5.
 */
public class AddressHolder extends BaseHolder<Address.AddressListBean> {

    private TextView tvAddressUser;
    private TextView tvAddressPhone;
    private TextView tvAddressDetail;
    private ImageView ivAddressDefault;

    public AddressHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.item_address, null);
        tvAddressUser = (TextView) view.findViewById(R.id.tv_address_user);
        tvAddressPhone = (TextView) view.findViewById(R.id.tv_address_phone);
        tvAddressDetail = (TextView) view.findViewById(R.id.tv_address_detail);
        ivAddressDefault = (ImageView) view.findViewById(R.id.iv_address_default);
        return view;
    }

    @Override
    public void bindData(Address.AddressListBean data) {
        if (data.getIsDefault() == 1){
            ivAddressDefault.setVisibility(View.VISIBLE);
        }else {
            ivAddressDefault.setVisibility(View.INVISIBLE);
        }
        tvAddressUser.setText(data.getName());
        tvAddressPhone.setText(data.getPhoneNumber());
        tvAddressDetail.setText(data.getProvince()+data.getCity()+data.getAddressArea()+data.getAddressDetail());
    }
}
