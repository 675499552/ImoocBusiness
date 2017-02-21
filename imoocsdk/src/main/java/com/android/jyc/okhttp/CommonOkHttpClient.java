package com.android.jyc.okhttp;

import com.android.jyc.okhttp.https.HttpsUtils;
import com.android.jyc.okhttp.listener.DisposeDataHandle;
import com.android.jyc.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Admin on 2017/2/17.
 * @function 请求的发送,请求参数的配置,https支持
 */

public class CommonOkHttpClient {

    private static final int TIEE_OUT = 30;//超时参数.
    private static OkHttpClient mOkHttpClient;

    //client去配置参数
    static {

        //创建client对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //为构建者填充超时时间
        okHttpBuilder.connectTimeout(TIEE_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIEE_OUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIEE_OUT,TimeUnit.SECONDS);

        //允许重定向
        okHttpBuilder.followRedirects(true);

        //https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        //
        okHttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(),HttpsUtils.initTrustManager());

        //生成client对象
        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 发送具体的http/https请求
     * @param request
     * @param commCallBack
     * @return Call
     */
    public static Call sendRequest(Request request, CommonJsonCallback commCallBack){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commCallBack);

        return call;
    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }
}
