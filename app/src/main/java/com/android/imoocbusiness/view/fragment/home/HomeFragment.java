package com.android.imoocbusiness.view.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.imoocbusiness.R;
import com.android.imoocbusiness.adapter.CourseAdapter;
import com.android.imoocbusiness.module.recommand.BaseRecommandModel;
import com.android.imoocbusiness.network.RequestCenter;
import com.android.imoocbusiness.view.fragment.BaseFragment;
import com.android.imoocbusiness.view.home.HomeHeaderLayout;
import com.android.imoocbusiness.zxing.app.CaptureActivity;
import com.android.jyc.okhttp.CommonOkHttpClient;
import com.android.jyc.okhttp.listener.DisposeDataListener;
import com.android.jyc.okhttp.request.CommonRequest;

/**
 * Created by Admin on 2017/2/17.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private static final int REQUEST_QRCODE = 0x01;
    /**
     * UI
     */
    private View mContentView;
    private ListView mListView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;
    private TextView mQRCodeView;

    /**
     * data
     */
    private CourseAdapter mAdapter;
    private BaseRecommandModel mRecommandData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout,container,false);
        initView();
        return mContentView;
    }

    private void initView() {
        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = (ListView) mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);
        //启动我们的LoadingView动画
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    /**
     * 发送首页列表请求
     */
    private void requestRecommandData() {
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.e("ceshi","onSuccess:"+responseObj.toString());

                /**
                 * 获取到数据后更新UI
                 */
                mRecommandData = (BaseRecommandModel) responseObj;
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                //提示用户网络有问题
                Log.e("ceshi","onFailure:"+reasonObj.toString());
            }
        });
    }

    /**
     * 请求成功执行的方法
     */
    private void showSuccessView() {
        //判断数据是否为空
        if (mRecommandData.data.list !=null&& mRecommandData.data.list.size()>0){
            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mAdapter = new CourseAdapter(mContext,mRecommandData.data.list);
            mListView.setAdapter(mAdapter);

            //为listview添加列表头
            mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));
            mAdapter = new CourseAdapter(mContext, mRecommandData.data.list);
            mListView.setAdapter(mAdapter);
        }else{
            showErrorView();
        }
    }

    /**
     *请求失败后执行的方法
     */
    private void showErrorView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qrcode_view:
                    doOpenCamera();
                break;
//            case R.id.category_view:
//                与我交谈
//                Intent intent2 = new Intent(Intent.ACTION_VIEW, Util.createQQUrl("277451977"));
//                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent2);
//                break;
//            case R.id.search_view:
//                Intent searchIntent = new Intent(mContext, SearchActivity.class);
//                mContext.startActivity(searchIntent);
//                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_QRCODE:
                if (requestCode == Activity.RESULT_OK){
                    String code = data.getStringExtra("SCAN_RESULT");
                }
                break;
        }
    }

    public void doOpenCamera() {
        Intent intent = new Intent(mContext, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QRCODE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
