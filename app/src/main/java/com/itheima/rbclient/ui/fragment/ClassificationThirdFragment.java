package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.rbclient.R;
import com.itheima.rbclient.adapter.ClassificationAdapter;
import com.itheima.rbclient.bean.ClassificationResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/3.
 */
public class ClassificationThirdFragment extends BaseFragment {

    private ListView listView;
    private TextView classification_one_back;
    private TextView classification_title;
    private Map<Integer,ArrayList<ClassificationResponse.CategoryBean>> mapThirdPage;
    private ArrayList<ClassificationResponse.CategoryBean> list;
    private String title;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classification_interface,null);
        listView = (ListView) view.findViewById(R.id.classification_listview);
        classification_title = (TextView) view.findViewById(R.id.classification_title);
        EventBus.getDefault().register(this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到具体商品页面
            }
        });
        classification_title.setText(title);

        classification_one_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上一个Fragment，退栈
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    public void setClassification_title(String title){
        this.title = title;
    }
    public void setList(ArrayList<ClassificationResponse.CategoryBean> list){
        this.list = list;
        listView.setAdapter(new ClassificationAdapter(list));
    }


    @Subscribe(sticky = true)
    public void onEvent(ArrayList<ClassificationResponse.CategoryBean> list){
        this.list = list;
        listView.setAdapter(new ClassificationAdapter(list));
    }

    @Subscribe(sticky = true)
    public void onEvent(String title){
        this.title = title;
    }
}
