package com.itheima.rbclient.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.OrderDetailResponse;
import com.itheima.rbclient.protocol.OrderDetailProtocol;
import com.itheima.rbclient.util.DrawableUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by xuan on 2016/8/7.
 */
public class OrderDetailFragment extends BaseFragment  {

    private OrderDetailResponse orderDetailResponse;
    private Drawable drawable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        Log.d("ddd", "1: ");
        Log.d("ddd", orderDetailResponse.response);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_order_detail,null);
        float[] floats = {20,20,20,20,20,20,20,20};
        drawable = DrawableUtil.generateDrawable(Color.BLACK,floats);
        initTitle(view);
        initViewWLZT(view);
        initViewDDXX(view);
        initViewDDXQ(view);
        Log.d("ddd", "2: ");
        return view;
    }

    /**初始化标题*/
    private void initTitle(View view) {
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        Button btn_title_left = (Button) view.findViewById(R.id.btn_title_left);
        Button btn_title_right = (Button) view.findViewById(R.id.btn_title_right);
        tv_title.setText("我的订单");
        btn_title_left.setText("我的订单");
        btn_title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(MyOrderFormFragment.class),false);
            }
        });
        btn_title_right.setVisibility(View.GONE);
    }

    /**初始化订单详情*/
    private void initViewDDXQ(View view) {
        LinearLayout ll_order_ddxq = (LinearLayout) view.findViewById(R.id.ll_order_ddxq);
        ll_order_ddxq.setBackgroundDrawable(drawable);

        TextView tv_order_ddzt = (TextView) ll_order_ddxq.findViewById(R.id.tv_order_ddzt);
        TextView tv_order_shfs = (TextView) ll_order_ddxq.findViewById(R.id.tv_order_shfs);
        TextView tv_order_zffs = (TextView) ll_order_ddxq.findViewById(R.id.tv_order_zffs);
        TextView tv_order_scsj = (TextView) ll_order_ddxq.findViewById(R.id.tv_order_scsj);
        TextView tv_order_fhsj = (TextView) ll_order_ddxq.findViewById(R.id.tv_order_fhsj);
        TextView tv_order_fp = (TextView) ll_order_ddxq.findViewById(R.id.tv_order_fp);
        TextView tv_order_fptt = (TextView) ll_order_ddxq.findViewById(R.id.tv_order_fptt);
        TextView tv_order_shyq = (TextView) ll_order_ddxq.findViewById(R.id.tv_order_shyq);

        OrderDetailResponse.PaymentInfo paymentInfo = orderDetailResponse.paymentInfo;
        if ("1".equals(paymentInfo.type)){
            tv_order_zffs.setText("货到付款-现金支付");
        }else if ("2".equals(paymentInfo.type)){
            tv_order_zffs.setText("货到付款-post机");
        }else if ("3".equals(paymentInfo.type)){
            tv_order_zffs.setText("支付宝");
        }

        OrderDetailResponse.DeliveryInfo deliveryInfo = orderDetailResponse.deliveryInfo;

        if ("1".equals(deliveryInfo.type)){
            tv_order_shyq.setText("周一至周五送货");
        }else if ("2".equals(deliveryInfo.type)){
            tv_order_shyq.setText("双休日及公众假期送货");
        }else if ("3".equals(deliveryInfo.type)){
            tv_order_shyq.setText("时间不限，工作日双休日及公众假期均可送货");
        }

        OrderDetailResponse.InvoiceInfo invoiceInfo = orderDetailResponse.invoiceInfo;
        if (invoiceInfo!=null) {
            tv_order_fptt.setText(invoiceInfo.invoiceTitle);
        }
    }

    /**初始化订单收货信息*/
    private void initViewDDXX(View view) {
        LinearLayout ll_orderdetail_ddxq = (LinearLayout) view.findViewById(R.id.ll_orderdetail_ddxq);
        ll_orderdetail_ddxq.setBackgroundDrawable(drawable);

        TextView tv_order_ddh = (TextView) view.findViewById(R.id.tv_order_ddh);
        TextView tv_order_xm = (TextView) view.findViewById(R.id.tv_order_xm);
        TextView tv_order_dh = (TextView) view.findViewById(R.id.tv_order_dh);
        TextView tv_order_dz = (TextView) view.findViewById(R.id.tv_order_dz);

        OrderDetailResponse.OrderDetailInfo orderDetailInfo = orderDetailResponse.orderInfo;
        //绑定数据
        tv_order_ddh.setText(orderDetailInfo.orderId);

        OrderDetailResponse.AddressInfo addressInfo = orderDetailResponse.addressInfo;
        tv_order_xm.setText(addressInfo.name);
        tv_order_dz.setText(addressInfo.addressArea+addressInfo.addressDetail);
    }

    /**初始化物流状态*/
    private void initViewWLZT(View view) {
        LinearLayout ll_detail_wlcx = (LinearLayout) view.findViewById(R.id.ll_detail_wlcx);
        ll_detail_wlcx.setBackgroundDrawable(drawable);
    }


    @Subscribe(sticky = true)
    public void onEvent(OrderDetailResponse orderDetailResponse){
        this.orderDetailResponse = orderDetailResponse;

        Log.d("ddd", "3: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
