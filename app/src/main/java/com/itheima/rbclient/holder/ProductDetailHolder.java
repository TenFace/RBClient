package com.itheima.rbclient.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.EventBusBean;
import com.itheima.rbclient.bean.ProductDetail;
import com.itheima.rbclient.ui.activity.ColorAndSizeActivity;
import com.itheima.rbclient.ui.fragment.BaseFragment;
import com.itheima.rbclient.ui.fragment.BigMarketDetailFragment;
import com.itheima.rbclient.ui.fragment.FragmentInstanceManager;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by 赵志杰 on 2016/8/3.
 * 这是商品详情页面的holder界面(主要做的是初始化view和绑定服务器中返回的数据的操作。)
 */
public class ProductDetailHolder extends BaseHolder<ProductDetail.ProductBean> {
    //初始化产品详情页面中存放图片的LinearLayout
    @InjectView(R.id.ll_market_detail_screen)
    LinearLayout ll_market_detail_screen;
    @InjectView(R.id.tv_name)
    TextView tv_name;//商品的名称

    @InjectView(R.id.tv_market_price)
    TextView tv_market_price;//商品的市场价

    @InjectView(R.id.tv_member_price)
    TextView tv_member_price;//商品的市场价

    @InjectView(R.id.rb_star)
    RatingBar rb_star;

    @InjectView(R.id.tv_commentcount)
    TextView tv_commentcount;
    @InjectView(R.id.tv_look_sheengyu)
    TextView tv_look_sheengyu;

    @InjectView(R.id.bt_receive)
    Button bt_receive;//收藏按钮

    @InjectView(R.id.bt_add_market_car1)
    Button bt_add_market_car1;
    @InjectView(R.id.bt_add_market_car)
    Button bt_add_market_car;

    @InjectView(R.id.iv_market_detailddd)
    TextView iv_market_detailddd;

    @InjectView(R.id.ll_color_size_container)
    LinearLayout ll_color_size_container;

    @InjectView(R.id.btn_favorite_back)
    Button btn_favorite_back;
    OnAddMarketCartListener addMarket;
    //产品详情页面的rootView布局。
    public View marketDetailView;

    BaseFragment fragment;

    PopupWindow popupWindow;
    //这是存放选择颜色的数组
    ArrayList listColor = new ArrayList();
    ArrayList listSize = new ArrayList();
    //private String[] list ={"红色","绿色","蓝色","屎黄色"};//接收从服务器上获取的数据

    public ProductDetailHolder(Context context) {
        super(context);
    }

    //初始化View
    @Override
    protected View initView() {
        //添加一个产品详情页面的界面。。。。
        marketDetailView = View.inflate(App.appliction, R.layout.fragment_market_detail, null);
        ButterKnife.inject(this, marketDetailView);
        return marketDetailView;
    }

    /**
     * 这个方法的作用是：设置Fragment，可以在holder中调用赵森样自己定义的跳转到子Fragment的方法仅此而已。
     *
     * @param fragment
     */
    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public View getRootView() {
        return marketDetailView;
    }

    public Button getViewId() {
        return btn_favorite_back;
    }

    //给holder管理的View设置数据
    @Override
    public void bindData(final ProductDetail.ProductBean data) {
        List<String> smallPic = data.getBigPic();//这个得到的是小图的url

        //得到商品的productProperty，商品属性
        List<ProductDetail.ProductBean.ProductPropertyBean> productProperty = data.getProductProperty();
        for (ProductDetail.ProductBean.ProductPropertyBean pPropertyBean : productProperty) {
            //先判断一下是否是颜色（还是尺码）
            if (pPropertyBean.getK() == "颜色") {
                listColor.add(pPropertyBean.getV());//获取到属性中的颜色，并把这个颜色放到一个List集合中。
            } else {
                //如果是不是颜色那么就是尺码，就添加到尺码的集合中去。
                listSize.add(pPropertyBean.getV());
            }

        }



      /*  btn_favorite_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               *//* fragment.getActivity().getSupportFragmentManager()
                        .popBackStack();*//*
//                fragment.getFragmentManager().popBackStack();
                fragment.getActivity().onBackPressed();
            }
        });*/


        BigMarketDetailFragment f = new BigMarketDetailFragment();

        EventBus.getDefault().postSticky(new EventBusBean(data));
        //绑定数据的时候，需要将从服务器中访问的数据添加到LinearLayout中
        for (int i = 0; i < smallPic.size(); i++) {
            ImageView imageView = new ImageView(App.appliction);
            //显示图片
//          HttpLoader.display(imageView, RBConstants.URL_MarketDetail+bigPic.get(i));
            HttpLoader.getInstance(App.appliction).display(imageView, RBConstants.URL_SERVER + smallPic.get(i));
            //然后将ImageView加入到这个线性布局里。
            ll_market_detail_screen.addView(imageView);
            setData(data);
            //给图片设置缩放的点击事件。
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //当点击每个图片的时候，会跳转到大图的Fragment
                    fragment.swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(BigMarketDetailFragment.class), true);
                }
            });
        }
        //加入收藏夹设置点击事件
        bt_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_receive.setText("已收藏");
                // Toast.makeText(App.appliction,"点击了收藏夹",Toast.LENGTH_SHORT).show();
                //这里需要将data数据封装成一个bean对象。。。
                //FavoriterContainerBean favoriterBean = new FavoriterContainerBean();
            }
        });

        ll_color_size_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳到Activity中去。
                Intent intent = new Intent(App.appliction, ColorAndSizeActivity.class);//跳到大小和颜色的选择的Activity中去
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                EventBus.getDefault().postSticky(data);
                App.appliction.startActivity(intent);
            }
        });


        //加入购物车的点击事件
        bt_add_market_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳到Activity中去。
                Intent intent = new Intent(App.appliction, ColorAndSizeActivity.class);//跳到大小和颜色的选择的Activity中去
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                EventBus.getDefault().postSticky(data);
                App.appliction.startActivity(intent);
            }
        });

        bt_add_market_car1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳到Activity中去。
                Intent intent = new Intent(App.appliction, ColorAndSizeActivity.class);//跳到大小和颜色的选择的Activity中去
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                EventBus.getDefault().postSticky(data);
                App.appliction.startActivity(intent);
            }
        });
    }

    //设置一个监听事件，在ColorAndSizeFragment中监听这个事件，当监听到点击了加入购物车的时候，由ColorAndSizeFragment从下往上弹出一个popupwindow
    public void setAddMarketCartListener(OnAddMarketCartListener addMarket) {
        this.addMarket = addMarket;
    }

    public interface OnAddMarketCartListener {
        void onClick();
    }


    /**
     * 这是给商品详情页面中的控件设置数据
     */
    private void setData(ProductDetail.ProductBean data) {
        //下面是给商品详情页中的其他控件进行填充数据。。。。。
        tv_name.setText(data.getName());
        //因为这个getMarketPrice返回的是一个int类型，但是这里需要一个字符串类型
        tv_market_price.setText(data.getMarketPrice() + "");
        tv_member_price.setText(data.getPrice() + "");
        rb_star.setRating(data.getScore());
        tv_commentcount.setText(data.getCommentCount() + "");
        tv_look_sheengyu.setText("老子不知道还剩多少了");
    }


}
