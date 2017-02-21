package com.android.imoocbusiness.network;

import com.android.imoocbusiness.module.recommand.BaseRecommandModel;
import com.android.jyc.okhttp.CommonOkHttpClient;
import com.android.jyc.okhttp.listener.DisposeDataHandle;
import com.android.jyc.okhttp.listener.DisposeDataListener;
import com.android.jyc.okhttp.request.CommonRequest;
import com.android.jyc.okhttp.request.RequestParams;

/**
 * Created by Admin on 2017/2/20.
 * @function: 存放应用中所有的请求
 */

public class RequestCenter {

    //根据参数发送所有post请求
    private static void postRequest(String url, RequestParams params,
                                    DisposeDataListener listener,Class<?> clazz){
        CommonOkHttpClient.get(CommonRequest.createGetRequest(
                url,params),new DisposeDataHandle(listener,clazz));
    }

    /**
     * 发送首页请求
     * @param listener
     */
    public static void requestRecommandData(DisposeDataListener listener){
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND,null,listener, BaseRecommandModel.class);
    }
}
