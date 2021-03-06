package com.itheima.rbclient.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.Address;
import com.itheima.rbclient.bean.AddressDelete;
import com.itheima.rbclient.bean.Favorite;
import com.itheima.rbclient.util.NetUtil;
import com.itheima.rbclient.widget.ChangeAddressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/8/5.
 */
public class NewAddressFragment extends BaseFragment implements View.OnClickListener, HttpLoader.HttpListener, View.OnFocusChangeListener {

    private Button btnAddress;
    private Button btnNewaddressSave;
    private EditText etNewaddressUser;
    private EditText etNewaddressPhone;
    private EditText etNewaddressZipcode;
    private TextView tvNewaddressProvider;
    private EditText etNewaddressDetail;
    private String mProvince;
    private String mCity;
    private String mArea;
    private ImageView ivNewaddressUserClear;
    private ImageView ivNewaddressPhoneClear;
    private ImageView ivNewaddressZipcodeClear;
    private ImageView ivNewaddressDetailClear;
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private boolean isNew;
    private boolean isDefault;
    private Address.AddressListBean addressBean;
    private Button btnNewaddressDefault;
    private Button btnNewaddressDelete;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_new_address, null);
        EventBus.getDefault().register(this);
        isDefault = false;
        initView(inflate);
        return inflate;
    }

    @Subscribe(sticky = true)
    public void onEvent(Boolean b){
        isNew = b;
        Log.d("EventBus", b ? "true":"flase");
    }

    @Subscribe(sticky = true)
    public void onEvent(Address.AddressListBean addressBean){
        this.addressBean = addressBean;
    }


    private void initView(View inflate) {
        TextView tvNewaddressTitle = (TextView) inflate.findViewById(R.id.tv_newaddress_title);
        btnAddress = (Button) inflate.findViewById(R.id.btn_address);
        btnNewaddressSave = (Button) inflate.findViewById(R.id.btn_newaddress_save);
        etNewaddressUser = (EditText) inflate.findViewById(R.id.et_newaddress_user);
        etNewaddressPhone = (EditText) inflate.findViewById(R.id.et_newaddress_phone);
        etNewaddressZipcode = (EditText) inflate.findViewById(R.id.et_newaddress_zipcode);
        tvNewaddressProvider = (TextView) inflate.findViewById(R.id.tv_newaddress_provider);
        etNewaddressDetail = (EditText) inflate.findViewById(R.id.et_newaddress_detail);
        ivNewaddressUserClear = (ImageView) inflate.findViewById(R.id.iv_newaddress_user_clear);
        ivNewaddressPhoneClear = (ImageView) inflate.findViewById(R.id.iv_newaddress_phone_clear);
        ivNewaddressZipcodeClear = (ImageView) inflate.findViewById(R.id.iv_newaddress_zipcode_clear);
        ivNewaddressDetailClear = (ImageView) inflate.findViewById(R.id.iv_newaddress_detail_clear);
        btnNewaddressDefault = (Button) inflate.findViewById(R.id.btn_newaddress_default);
        btnNewaddressDelete = (Button) inflate.findViewById(R.id.btn_newaddress_delete);
        if (isNew){
            tvNewaddressTitle.setText("新增地址");
            btnNewaddressDefault.setVisibility(View.GONE);
            btnNewaddressDelete.setVisibility(View.GONE);
            etNewaddressUser.setText("");
            etNewaddressPhone.setText("");
            etNewaddressZipcode.setText("");
            etNewaddressDetail.setText("");
            tvNewaddressProvider.setText("(必填)");
            tvNewaddressProvider.setTextColor(Color.GRAY);
        }else {
            tvNewaddressTitle.setText("修改地址");
            btnNewaddressDefault.setVisibility(View.VISIBLE);
            btnNewaddressDelete.setVisibility(View.VISIBLE);
            etNewaddressUser.setText(addressBean.getName());
            etNewaddressPhone.setText(addressBean.getPhoneNumber());
            etNewaddressZipcode.setText(addressBean.getZipCode());
            etNewaddressDetail.setText(addressBean.getAddressDetail());
            String address = addressBean.getProvince() + "-" + addressBean.getCity();
            if (addressBean.getAddressArea() != null){
                address += "-" + addressBean.getAddressArea();
            }
            tvNewaddressProvider.setText(address);
        }

        imageViews.add(ivNewaddressUserClear);
        imageViews.add(ivNewaddressPhoneClear);
        imageViews.add(ivNewaddressZipcodeClear);
        imageViews.add(ivNewaddressDetailClear);

        tvNewaddressProvider.setOnClickListener(this);
        btnAddress.setOnClickListener(this);
        btnNewaddressSave.setOnClickListener(this);
        etNewaddressUser.setOnFocusChangeListener(this);
        etNewaddressPhone.setOnFocusChangeListener(this);
        etNewaddressZipcode.setOnFocusChangeListener(this);
        etNewaddressDetail.setOnFocusChangeListener(this);
        ivNewaddressUserClear.setOnClickListener(this);
        ivNewaddressPhoneClear.setOnClickListener(this);
        ivNewaddressZipcodeClear.setOnClickListener(this);
        ivNewaddressDetailClear.setOnClickListener(this);
        btnNewaddressDefault.setOnClickListener(this);
        btnNewaddressDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_newaddress_provider:
                //打开一个选择地区的对话框选择地区
                chooseAddress();
                break;
            case R.id.btn_address:
                getFragmentManager().popBackStack();
                break;
            case R.id.btn_newaddress_save:
                //保存地址
                SaveAddress();
                break;
            case R.id.iv_newaddress_user_clear:
                etNewaddressUser.setText("");
                break;
            case R.id.iv_newaddress_phone_clear:
                etNewaddressPhone.setText("");
                break;
            case R.id.iv_newaddress_zipcode_clear:
                etNewaddressZipcode.setText("");
                break;
            case R.id.iv_newaddress_detail_clear:
                etNewaddressDetail.setText("");
                break;
            case R.id.btn_newaddress_default:
                //设置默认地址
                addressBean.setIsDefault(1);
                isDefault = true;
                Toast.makeText(getContext(), "设置成功，保存后生效", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_newaddress_delete:
                //删除地址
                deleteAddress();
                break;
        }
    }

    private void deleteAddress() {
        HttpParams params = new HttpParams().put("id", addressBean.getId()+"");
        Map<String, String> map = NetUtil.generateHeaders(App.appliction);
        //map.put(RBConstants.USERID, "" + PrefUtils.getInt(App.appliction, RBConstants.USERID, 0));
        params.addHeader(map);
        App.HL.get(RBConstants.URL_ADDRESS_DELETE, params, AddressDelete.class, RBConstants.REQUEST_CODE_ADDRESS_DELETE, this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_newaddress_user:
                    showImageView(ivNewaddressUserClear);
                    break;
                case R.id.et_newaddress_phone:
                    showImageView(ivNewaddressPhoneClear);
                    break;
                case R.id.et_newaddress_zipcode:
                    showImageView(ivNewaddressZipcodeClear);
                    break;
                case R.id.et_newaddress_detail:
                    showImageView(ivNewaddressDetailClear);
                    break;
            }
        }
    }
    /** 显示被选中的ImageView */
    private void showImageView(ImageView imageView) {
        for (ImageView iv : imageViews){
            if (iv == imageView){
                iv.setVisibility(View.VISIBLE);
            }else {
                iv.setVisibility(View.GONE);
            }
        }
    }

    private void chooseAddress() {
        ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(getContext());
        String allAddress = tvNewaddressProvider.getText().toString();
        if (!allAddress.contains("-")) {
            mChangeAddressDialog.setAddress("北京", "东城区", "");
        }else {
            int index = allAddress.indexOf("-");
            mProvince = allAddress.substring(0,index);
            String temp = allAddress.substring(index+1);
            if(temp.contains("-")){
                index = temp.indexOf("-");
                mCity = temp.substring(0,index);
                mArea = temp.substring(index+1);
            } else {
                mCity = temp;
                mArea = "";
            }
            mChangeAddressDialog.setAddress(mProvince, mCity, mArea);
        }
        mChangeAddressDialog.show();
        mChangeAddressDialog
                .setAddresskListener(new ChangeAddressDialog.OnAddressCListener() {

                    @Override
                    public void onClick(String province, String city, String area) {
                        // TODO Auto-generated method stub
                        String addressString;
                        if (!TextUtils.isEmpty(area)) {
                            addressString = province + "-" + city + "-" + area;
                        }else {
                            addressString = province + "-" + city;
                        }
                        tvNewaddressProvider.setText(addressString);
                        mProvince = province;
                        mCity = city;
                        mArea = area;
                        Toast.makeText(getContext(),
                                addressString,
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void SaveAddress() {
        String name = etNewaddressUser.getText().toString().trim();
        String phone = etNewaddressPhone.getText().toString().trim();
        String zipcode = etNewaddressZipcode.getText().toString().trim();
        String detail = etNewaddressDetail.getText().toString().trim();
        if (!isNew) {
            mProvince = addressBean.getProvince();
            mCity = addressBean.getCity();
            mArea = addressBean.getAddressArea();
        }
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(zipcode) || TextUtils.isEmpty(mProvince) || TextUtils.isEmpty(detail)){
            Toast.makeText(getContext(), "必填项不能为空，请检查输入", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpParams params = new HttpParams().put("name", name)
                .put("phoneNumber", phone)
                .put("province", mProvince).put("city", mCity).put("addressArea", mArea)
                .put("addressDetail", detail)
                .put("zipCode", zipcode)
                .put("isDefault", 0+"");
        if (!isNew){
            params.put("id", addressBean.getId()+"");
        }
        if (isDefault){
            params.put("isDefault", 1+"");
        }
        Map<String, String> map = NetUtil.generateHeaders(App.appliction);
        //map.put(RBConstants.USERID, "" + PrefUtils.getInt(App.appliction, RBConstants.USERID, 0));
        params.addHeader(map);
        App.HL.get(RBConstants.URL_SAVE_ADDRESS, params, Address.class, RBConstants.REQUEST_CODE_SAVE_ADDRESS, this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        Log.d("NewAddress", "-----onGetResponseSuccess-----");
        if (requestCode == RBConstants.REQUEST_CODE_SAVE_ADDRESS) {
            Toast.makeText(getActivity(), "保存成功！", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == RBConstants.REQUEST_CODE_ADDRESS_DELETE) {
            Toast.makeText(getActivity(), "删除成功！", Toast.LENGTH_SHORT).show();
        }
        getFragmentManager().popBackStack();
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(getContext(), "保存失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
        EventBus.getDefault().unregister(this);
    }
}
