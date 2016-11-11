package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.adapter.CartGoodsAdapter;
import com.itheima.rbclient.bean.CartResponse;
import com.itheima.rbclient.ui.fragment.BaseFragment;
import com.itheima.rbclient.ui.fragment.JiesuanCenterFragment;

import org.senydevpkg.base.BaseHolder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/3.
 */
public class CartHolder extends BaseHolder<CartResponse> {

    private final BaseFragment activity;
    private LinearLayout ll_detail_goods;

//    @InjectView(R.id.tv_title)
//    TextView tv_title;
    @InjectView(R.id.lv_goods)
    ListView lv_goods;
    @InjectView(R.id.tv_total_num)
    TextView tv_total_num;
    @InjectView(R.id.tv_total_money)
    TextView tv_total_money;
    @InjectView(R.id.tv_send)
    TextView tv_send;
    private Button btn_pay;
    private Button btn_free,btn_setting;

    public CartHolder(Context context,BaseFragment activity) {
        super(context);
        this.activity = activity;
    }

    public Button getBtn_pay() {
        return btn_pay;
    }

    public Button getBtn_setting() {
        return btn_setting;
    }

    public Button getBtn_free() {
        return btn_free;
    }

    @Override
    public View initView() {
        View view = View.inflate(App.appliction, R.layout.fragment_cart, null);
//        ButterKnife.inject(this, view);
        ll_detail_goods = (LinearLayout) view.findViewById(R.id.ll_detail_goods);
        btn_free = (Button) view.findViewById(R.id.btn_free);
        btn_setting = (Button) view.findViewById(R.id.btn_setting);
        btn_pay = (Button) view.findViewById(R.id.btn_pay);
        return view;
    }

    @Override
    public void bindData(CartResponse data) {
        //设置成功访问数据之后的两种情况的布局
        //判断list的大小是否大于0
        List<CartResponse.CartEntity> cart = data.getCart();
        if (cart.size() > 0) {
            //有商品，设置有商品界面，想布局中添加商品布局
            View view = View.inflate(App.appliction, R.layout.fragment_cart_goods, null);
            ButterKnife.inject(this, view);
            bindTitleData(data);
            lv_goods.setAdapter(new CartGoodsAdapter(cart,activity));
            ll_detail_goods.addView(view);

        } else {
            //没有商品，设置无商品界面，向布局中添加无商品布局
            View view = View.inflate(App.appliction, R.layout.fragment_cart_empty, null);
            Button btn_shopping = (Button) view.findViewById(R.id.btn_shopping);
            btn_setting.setVisibility(View.GONE);
            btn_pay.setVisibility(View.GONE);
            btn_shopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击进入浏览商品界面
                    Toast.makeText(App.appliction, "进入浏览商品界面", Toast.LENGTH_SHORT).show();
                    activity.swichToChildFragment(JiesuanCenterFragment.instantiate(App.appliction,"com.itheima.rbclient.ui.fragment.JiesuanCenterFragment"),true);

                }
            });
            ll_detail_goods.addView(view);
        }
    }

    private void bindTitleData(CartResponse data) {
        btn_pay.setVisibility(View.VISIBLE);
        btn_setting.setText("编辑");
        btn_pay.setText("去结算");
        tv_total_num.setText("数量总计：" + data.getTotalCount());
        tv_send.setText("赠送积分：" + data.getTotalPoint());
        tv_total_money.setText("商品总金额（不含运费）：" + data.getTotalPrice());
    }
}
