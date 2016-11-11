package com.itheima.rbclient.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.Address;
import com.itheima.rbclient.bean.InvoiceInfo;
import com.itheima.rbclient.bean.SourceAddress;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/4.
 */
public class JiesuanCenterFragment extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.btn_jiesuan_back)
    Button btnJiesuanBack;//回退
    @InjectView(R.id.btn_jiesuan_submit)
    Button btnJiesuanSubmit;//提交
    //    @InjectView(R.id.rl_jiesuan_address)
    RelativeLayout rlJiesuanAddress;//选择地址
    @InjectView(R.id.rl_jiesuan_payway)
    RelativeLayout rlJiesuanPayway;//选择支付方式
    //    @InjectView(R.id.tv_jiesuan_payway)
    TextView tvJiesuanPayway;//显示支付方式
    @InjectView(R.id.rl_jiesuan_time)
    RelativeLayout rlJiesuanTime;//选择送货时间
    @InjectView(R.id.rl_jiesuan_courier)
    RelativeLayout rlJiesuanCourier;//选择快递
    @InjectView(R.id.rl_jiesuan_coupon)
    RelativeLayout rlJiesuanCoupon;//选择优惠券
    @InjectView(R.id.rl_jiesuan_invoice)
    RelativeLayout rlJiesuanInvoice;//选择发票
    @InjectView(R.id.tv_count_number)
    TextView tvCountNumber;//数量总计
    @InjectView(R.id.tv_count_origin)
    TextView tvCountOrigin;//初始价格总计
    @InjectView(R.id.tv_count_freight)
    TextView tvCountFreight;//运费
    @InjectView(R.id.tv_count_coupon)
    TextView tvCountCoupon;//优惠
    @InjectView(R.id.tv_count_money)
    TextView tvCountMoney;//最终价格
    @InjectView(R.id.btn_jiesuan_commit)
    Button btnJiesuanCommit;//提交
    @InjectView(R.id.lv_jiesuan)
    ListView lvJiesuan;//填充商品的ListView(item_jiesuan为其Item)
//    @InjectView(R.id.tv_jiesuan_time)
    TextView tvJiesuanTime;
    private Address.AddressListBean addressBean;

    private TextView tvAddressUser;
    private TextView tvAddressPhone;
    private TextView tvAddressDetail;
    private ImageView ivAddressDefault;
    private TextView tvJiesuanCourier;
    private TextView tvJiesuanCoupon;
    private String invoce;
    private TextView tvJiesuanInvoice;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jiesuancenter, null);
        EventBus.getDefault().register(this);
        rlJiesuanAddress = (RelativeLayout) view.findViewById(R.id.rl_jiesuan_address);
        tvJiesuanPayway = (TextView) view.findViewById(R.id.tv_jiesuan_payway);
        tvJiesuanTime = (TextView) view.findViewById(R.id.tv_jiesuan_time);
        tvJiesuanCourier = (TextView) view.findViewById(R.id.tv_jiesuan_courier);
        tvJiesuanCoupon = (TextView) view.findViewById(R.id.tv_jiesuan_coupon);
        tvJiesuanInvoice = (TextView) view.findViewById(R.id.tv_jiesuan_invoice);
        tvJiesuanPayway.setVisibility(View.GONE);
        tvJiesuanTime.setVisibility(View.GONE);
        tvJiesuanCourier.setVisibility(View.GONE);
        tvJiesuanInvoice.setVisibility(View.GONE);
        return view;
    }


    @Subscribe(sticky = true)
    public void onEvent(Address.AddressListBean addressBean) {
        this.addressBean = addressBean;
        //清空该RelativeLayout并添加新布局
        rlJiesuanAddress.removeAllViews();
        rlJiesuanAddress.setBackgroundResource(0);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        rlJiesuanAddress.setLayoutParams(params);
        View view = View.inflate(getContext(), R.layout.item_address, null);
        tvAddressUser = (TextView) view.findViewById(R.id.tv_address_user);
        tvAddressPhone = (TextView) view.findViewById(R.id.tv_address_phone);
        tvAddressDetail = (TextView) view.findViewById(R.id.tv_address_detail);
        ivAddressDefault = (ImageView) view.findViewById(R.id.iv_address_default);
        //添加数据
        if (addressBean.getIsDefault() == 1) {
            ivAddressDefault.setVisibility(View.VISIBLE);
        } else {
            ivAddressDefault.setVisibility(View.INVISIBLE);
        }
        tvAddressUser.setText(addressBean.getName());
        tvAddressPhone.setText(addressBean.getPhoneNumber());
        tvAddressDetail.setText(addressBean.getProvince() + addressBean.getCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());
        rlJiesuanAddress.addView(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_jiesuan_back, R.id.btn_jiesuan_submit, R.id.rl_jiesuan_address, R.id.rl_jiesuan_payway, R.id.rl_jiesuan_time, R.id.rl_jiesuan_courier, R.id.rl_jiesuan_coupon, R.id.rl_jiesuan_invoice, R.id.btn_jiesuan_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jiesuan_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.rl_jiesuan_address:
                SourceAddress sourceAddress = new SourceAddress();
                sourceAddress.setSource(true);
                EventBus.getDefault().postSticky(sourceAddress);
                swichToChildFragment(new AddressFragment(), true);
                break;
            case R.id.rl_jiesuan_payway:
                //弹出支付方式对话框
                showPayWayDialog();
                break;
            case R.id.rl_jiesuan_time:
                //弹出送货时间对话框
                showTimeDialog();
                break;
            case R.id.rl_jiesuan_courier:
                //弹出快递对话框
                showCourierDialog();
                break;
            case R.id.rl_jiesuan_coupon:
                break;
            case R.id.rl_jiesuan_invoice:
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(InvoiceFragment.class), true);
                break;
            case R.id.btn_jiesuan_submit:
            case R.id.btn_jiesuan_commit:
                //提交
                swichToChildFragment(new SubmissionSuccessFragment(),true);
                break;
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(InvoiceInfo invoiceInfo){
        invoce = invoiceInfo.getHead() + "/" +invoiceInfo.getBody();
        tvJiesuanInvoice.setText(invoce);
        tvJiesuanInvoice.setVisibility(View.VISIBLE);
    }

    private int tempWhich;
    private int currentPayWay = 0;

    private void showPayWayDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mMainActivity);
        AlertDialog.Builder builder = new AlertDialog.Builder(mMainActivity, R.style.MyDialogTheme);
        builder.setTitle("请选择支付方式");
        String[] payWay = {"到付-现金", "到付-POS", "支付宝"};
        builder.setSingleChoiceItems(payWay, currentPayWay,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tempWhich = which;
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (tempWhich) {
                    case 0:
                        tvJiesuanPayway.setText("货到付款-现金支付");
                        break;
                    case 1:
                        tvJiesuanPayway.setText("货到付款-POS机");
                        break;
                    case 2:
                        tvJiesuanPayway.setText("在线付款-支付宝");
                        break;
                }
                tvJiesuanPayway.setVisibility(View.VISIBLE);
                currentPayWay = tempWhich;
            }
        });

        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int tempTime;
    private int currentTime = 0;

    private void showTimeDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mMainActivity);
        AlertDialog.Builder builder = new AlertDialog.Builder(mMainActivity, R.style.MyDialogTheme);
        builder.setTitle("请选择送货时间");
        String[] time = {"工作日、双休日、节假日均可送货", "只双休日、节假日送货（工作日不送货）", "只工作日送货（双休日、节假日不送货）"};
        builder.setSingleChoiceItems(time, currentTime,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tempTime = which;
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (tempTime) {
                    case 0:
                        tvJiesuanTime.setText("工作日、双休日、节假日均可送货");
                        break;
                    case 1:
                        tvJiesuanTime.setText("只双休日、节假日送货（工作日不送货）");
                        break;
                    case 2:
                        tvJiesuanTime.setText("只工作日送货（双休日、节假日不送货）");
                        break;
                }
                tvJiesuanTime.setVisibility(View.VISIBLE);
                currentTime = tempTime;
            }
        });

        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int tempCourier;
    private int currentCourier = 0;

    private void showCourierDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mMainActivity);
        AlertDialog.Builder builder = new AlertDialog.Builder(mMainActivity, R.style.MyDialogTheme);
        builder.setTitle("请选择送货时间");
        String[] time = {"申通", "圆通", "韵达", "EMS"};
        builder.setSingleChoiceItems(time, currentCourier,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tempCourier = which;
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (tempCourier) {
                    case 0:
                        tvJiesuanCourier.setText("申通");
                        break;
                    case 1:
                        tvJiesuanCourier.setText("圆通");
                        break;
                    case 2:
                        tvJiesuanCourier.setText("韵达");
                        break;
                    case 3:
                        tvJiesuanCourier.setText("EMS");
                        break;
                }
                tvJiesuanCourier.setVisibility(View.VISIBLE);
                currentCourier = tempCourier;
            }
        });

        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
