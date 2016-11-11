package com.itheima.rbclient.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.AddCartColorSizeBean;
import com.itheima.rbclient.bean.ProductDetail;
import com.itheima.rbclient.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ColorAndSizeActivity extends Activity {
    @InjectView(R.id.tv_color1)
    TextView tv_color1;
    @InjectView(R.id.tv_color2)
    TextView tv_color2;

    @InjectView(R.id.tv_size1)
    TextView tv_size1;
    @InjectView(R.id.tv_size2)
    TextView tv_size2;
    @InjectView(R.id.tv_size3)
    TextView tv_size3;
    @InjectView(R.id.bt_add_market_car)
    Button bt_add_market_car;
    @InjectView(R.id.et_market_number)
    EditText et_market_number;
    @InjectView(R.id.rg_container_color)
    RadioGroup rg_container_color;
    @InjectView(R.id.rg_container_size)
    RadioGroup rg_container_size;

    ProductDetail.ProductBean data;

    //这是存放选择颜色的数组
    ArrayList listColor = new ArrayList();
    ArrayList listSize = new ArrayList();
    private RadioButton radioButton;
    private String msgNum;
    private String msgColor;
    private String msgSize;

    //这个javabean用于保存颜色，尺寸和数量的
    AddCartColorSizeBean abean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_and_size);
        abean = new AddCartColorSizeBean();
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        //获取到传递过里的颜色和尺码
        getColorAndSizeList(data);
        //这里获取到颜色和尺码之后呢，我们需要动态添加到指定的控件上
        setTextView(listColor, listSize);

        //颜色尺码的点击事件
        //rg_container_color.setO
        //设置各种点击事件
        setOnClickaListener();
    }

    /**
     * 设置各种点击事件
     */
    private void setOnClickaListener() {
        rg_container_size.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                msgSize = radioButton.getText().toString();
                ToastUtils.showToast(msgSize);
            }
        });
        rg_container_color.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                msgColor = radioButton.getText().toString();
                ToastUtils.showToast(msgColor);
            }
        });
        msgNum = et_market_number.getText().toString();
        bt_add_market_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (msgSize != null && msgColor != null && msgNum != null) {
                    //把购物车所需的信息都保存在了AddCartColorSizeBean中
                    abean.setColor(msgColor);
                    abean.setSize(msgSize);
                    abean.setNum(msgNum);
                    abean.setId(data.getId() + "");
                    abean.setName(data.getName());
                    abean.setPrice(data.getPrice());
                    //这个图片的url只是设置下第一张图片
                    abean.setUrl(data.getBigPic().get(0)+"");
                    ToastUtils.showToast("已加入购物车");
                    //将这个bean传到孔斌的购物车界面
                    EventBus.getDefault().postSticky(abean);
                    finish();
                } else {
                    ToastUtils.showToast("不要漏选项");
                }
//                if(msgSize == null){
//                    ToastUtils.showToast("请选择尺码");
//                }else if(msgColor == null){
//                    ToastUtils.showToast("请选择颜色");
//                }else if(msgNum == null){
//                    ToastUtils.showToast("请选择数量");
//                }
            }
        });
    }

    /**
     * 这个方法是动态设置下颜色和尺码控件的内容
     * 参数是颜色和尺码的集合
     */
    public void setTextView(ArrayList listColor, ArrayList listSize) {
        //颜色只有两个
        if (listColor.size() > 1) {//如果有两个颜色，那么要定义两个TextView了
            tv_color1.setText(listColor.get(0) + "");
            tv_color2.setText(listColor.get(1) + "");
        } else {
            tv_color1.setText(listColor.get(0) + "");
            //并将第二个颜色的TextView隐藏掉
            tv_color2.setVisibility(View.GONE);
        }
        //但是尺码最多有三个。
        if (listSize.size() == 3) {
            tv_size1.setText(listSize.get(0) + "");
            tv_size2.setText(listSize.get(1) + "");
            tv_size3.setText(listSize.get(2) + "");
        } else if (listSize.size() == 2) {
            tv_size1.setText(listSize.get(0) + "");
            tv_size2.setText(listSize.get(1) + "");
            tv_size3.setVisibility(View.GONE);
        } else {
            tv_size1.setText(listSize.get(0) + "");
            tv_size2.setVisibility(View.GONE);
            tv_size3.setVisibility(View.GONE);
        }
    }

    //用于接收EventBus传递过来的数据
    @Subscribe(sticky = true)
    public void onEvent(ProductDetail.ProductBean data) {
        this.data = data;
    }

    /**
     * 获取到商品的颜色和尺码，并将他们放到各自的集合中
     */
    public void getColorAndSizeList(ProductDetail.ProductBean data) {
        //得到商品的productProperty，商品属性
        List<ProductDetail.ProductBean.ProductPropertyBean> productProperty = data.getProductProperty();
        for (ProductDetail.ProductBean.ProductPropertyBean pPropertyBean : productProperty) {
            //先判断一下是否是颜色（还是尺码）
            if (pPropertyBean.getK().equals("颜色")) {
                listColor.add(pPropertyBean.getV());//获取到属性中的颜色，并把这个颜色放到一个List集合中。
            } else {
                //如果是不是颜色那么就是尺码，就添加到尺码的集合中去。
                listSize.add(pPropertyBean.getV());
            }
        }
    }


    //给颜色和尺码设置点击事件
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.tv_color1:
//                ToastUtil.showToast("选择了红色");
//            break;
//            case R.id.tv_color2:
//                ToastUtil.showToast("选择了绿色");
//                break;
//            case R.id.tv_size1:
//                ToastUtil.showToast("选择了M码");
//                break;
//            case R.id.tv_size2:
//                ToastUtil.showToast("选择了XXL码");
//                break;
//            case R.id.tv_size3:
//                ToastUtil.showToast("选择了XXXL码");
//                break;
//        }
    //  }
}
