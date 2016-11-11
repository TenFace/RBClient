package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.ClassificationAdapter;
import com.itheima.rbclient.bean.ClassificationResponse;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 */
public class ClassificationFragment extends BaseFragment implements HttpLoader.HttpListener {
    private static final String TAG = "classificationFragment";
    //记录上一个页面的点击的位置，来确定三级页面map对应的list集合
    private int currentPosition;
    //第三个页面的position
    private int thirdPosition;
    //记录当前页面是第几页
    private final int FIRS_TPAGE = 1;
    private final int SECOND_TPAGE = 2;
    private final int THIRD_TPAGE = 3;
    private int page = FIRS_TPAGE;
    private ListView listView;
    private TextView classification_one_back;
    private TextView classification_title;
    private ImageView classification_item_icon;
    private ClassificationResponse mResponse;
    //key-parentId  value - 二级页面集合
    private Map<Integer,ArrayList<ClassificationResponse.CategoryBean>> mapSecondPage;
    private Map<Integer,ArrayList<ClassificationResponse.CategoryBean>> mapThirdPage;
    private ArrayList<ClassificationResponse.CategoryBean> firstList;
    private ArrayList<ClassificationResponse.CategoryBean> secondList;
    private ArrayList<ClassificationResponse.CategoryBean> thirdList;
    private ArrayList<ClassificationResponse.CategoryBean> tempList;
    private ArrayList<ClassificationResponse.CategoryBean> tempList1;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classification_interface,null);
        listView = (ListView) view.findViewById(R.id.classification_listview);
        classification_one_back = (TextView) view.findViewById(R.id.classification_one_back);
        classification_title = (TextView) view.findViewById(R.id.classification_title);
        classification_one_back.setVisibility(View.VISIBLE);
        classification_title.setText("分类");
        if(mResponse == null){
            initData();
        }

//        classification_item_icon = (ImageView) view.findViewById(R.id.classification_item_icon);
//        classification_item_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(MyProductListFragment.class), true);
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(TAG, "onItemClick: list:"+mapSecondPage.get(position).size());
//                Log.i(TAG, "onItemClick: list:"+mapSecondPage.get(position).size());
//                for (ClassificationResponse.CategoryBean list:mapSecondPage.get(position)) {
//                    Log.i(TAG, "bian li : list"+list.getName()+":"+list.getId());
//                }
//                ClassificationSecondFragment fragment = new ClassificationSecondFragment();
//                fragment.setClassification_title(firstList.get(position).getName());
//                fragment.setList(mapSecondPage.get(position));
//                fragment.setMapThirdPage(mapThirdPage);
                EventBus eventBus = EventBus.getDefault();
                eventBus.postSticky(mapSecondPage.get(position));
                eventBus.postSticky(firstList.get(position).getName());
                eventBus.postSticky(mapThirdPage);
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(ClassificationSecondFragment.class), true);
            }
        });
        classification_one_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上一个Fragment，退栈
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    public void initData(){
        App.HL.get(RBConstants.URL_SERVER+"category",null,ClassificationResponse.class,45,this);
    }

    //按层次划分数据
    public void hierarchical(){
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();
        thirdList = new ArrayList<>();
        mapSecondPage = new HashMap<>();
        mapThirdPage = new HashMap<>();
        for (int i = 0; i <mResponse.getCategory().size(); i++) {
            if(mResponse.getCategory().get(i).getParentId() == 0){
                firstList.add(mResponse.getCategory().get(i));
            }
        }
        for (int i = 0; i < mResponse.getCategory().size(); i++) {
            if (mResponse.getCategory().get(i).isIsLeafNode()){
                thirdList.add(mResponse.getCategory().get(i));
            }else if(mResponse.getCategory().get(i).getParentId() > 0){
                secondList.add(mResponse.getCategory().get(i));
            }
        }

        //设置一级分类点击事件对应的二级分类
        for (int i = 0; i < firstList.size(); i++) {
            tempList = new ArrayList<>();
            for (int j = 0; j < secondList.size(); j++) {
                if(secondList.get(j).getParentId() == firstList.get(i).getId()){
                    tempList.add(secondList.get(j));
                    Log.i(TAG, "------------------------"+secondList.get(j).getName());
                }
            }
            for (ClassificationResponse.CategoryBean list:tempList) {
                Log.i(TAG, "....."+i+"************templist********"+list.getName()+"::"+list.getId());
            }
            mapSecondPage.put(i,tempList);
        }
        //设置二级分类点击事件对应的三级分类
        for (int i = 0; i < secondList.size(); i++) {
            tempList1 = new ArrayList<>();
            for (int j = 0; j < thirdList.size(); j++) {
                if(thirdList.get(j).getParentId() == secondList.get(i).getId()){
                    tempList1.add(thirdList.get(j));
                }
            }
            mapThirdPage.put(i,tempList1);
            tempList1.clear();
        }
        Log.i(TAG, "22222222222hierarchical: mapSconedList.size"+mapSecondPage.get(0).size());
        Log.i(TAG, "hierarchical: SconedList.size"+secondList.size());
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        mResponse = (ClassificationResponse) response;
        hierarchical();
        Log.i(TAG, "onGetResponseSuccess: 请求数据成功");
        listView.setAdapter(new ClassificationAdapter(firstList));
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(App.appliction,"请求数据失败",Toast.LENGTH_SHORT).show();
    }
}
