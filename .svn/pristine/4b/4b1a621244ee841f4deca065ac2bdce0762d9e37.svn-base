package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.Favorite;
import com.itheima.rbclient.bean.Invoice;
import com.itheima.rbclient.bean.InvoiceInfo;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/8/8.
 */
public class InvoiceFragment extends BaseFragment implements HttpLoader.HttpListener {
    @InjectView(R.id.btn_invoice_back)
    Button btnInvoiceBack;
    @InjectView(R.id.btn_invoice_add)
    Button btnInvoiceAdd;
    @InjectView(R.id.iv_invoice_head_person)
    ImageView ivInvoiceHeadPerson;
    @InjectView(R.id.rl_invoice_head_person)
    RelativeLayout rlInvoiceHeadPerson;
    @InjectView(R.id.iv_invoice_head_company)
    ImageView ivInvoiceHeadCompany;
    @InjectView(R.id.rl_invoice_head_company)
    RelativeLayout rlInvoiceHeadCompany;
    @InjectView(R.id.ll_invoice)
    LinearLayout llInvoice;

    private ArrayList<ImageView> head = new ArrayList<>();
    private ArrayList<ImageView> itemImageView = new ArrayList<>();
    private InvoiceInfo invoiceInfo;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_invoice, null);
        intData();
        invoiceInfo = new InvoiceInfo();
        return view;
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

    @OnClick({R.id.btn_invoice_back, R.id.btn_invoice_add, R.id.iv_invoice_head_person, R.id.rl_invoice_head_person, R.id.iv_invoice_head_company, R.id.rl_invoice_head_company})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_invoice_add:
                EventBus.getDefault().postSticky(invoiceInfo);
            case R.id.btn_invoice_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.rl_invoice_head_person:
                ivInvoiceHeadPerson.setVisibility(View.VISIBLE);
                ivInvoiceHeadCompany.setVisibility(View.INVISIBLE);
                invoiceInfo.setHead("个人");
                break;
            case R.id.rl_invoice_head_company:
                ivInvoiceHeadPerson.setVisibility(View.INVISIBLE);
                ivInvoiceHeadCompany.setVisibility(View.VISIBLE);
                invoiceInfo.setHead("单位");
                break;
        }
    }

    private void intData() {
        Log.d("Favorite", "-----onGetResponse-----");
        App.HL.post(RBConstants.URL_INVOICE, null, Invoice.class, RBConstants.REQUEST_CODE_INVOICE, this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        Log.d("Invoice", "-----onGetResponseSuccess-----");
        if (requestCode == RBConstants.REQUEST_CODE_INVOICE) {
            Invoice invoice = (Invoice) response;
            List<Invoice.InvoiceBean> invoiceBeen = invoice.getInvoice();
            for (final Invoice.InvoiceBean i : invoiceBeen){
                View view = View.inflate(getActivity(), R.layout.item_invoice, null);
                TextView tvItemInvoiceName = (TextView) view.findViewById(R.id.tv_item_invoice_name);
                final ImageView ivItemInvoiceDuigou = (ImageView) view.findViewById(R.id.tv_item_invoice_duigou);
                itemImageView.add(ivItemInvoiceDuigou);
                tvItemInvoiceName.setText(i.getContent());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showImagViwe(ivItemInvoiceDuigou);
                        invoiceInfo.setBody(i.getContent());
                    }
                });
                llInvoice.addView(view);
            }
        }
    }

    private void showImagViwe(ImageView ivItemInvoiceDuigou) {
        for (ImageView iv : itemImageView){
            if (iv == ivItemInvoiceDuigou){
                iv.setVisibility(View.VISIBLE);
            }else {
                iv.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(App.appliction, "error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
