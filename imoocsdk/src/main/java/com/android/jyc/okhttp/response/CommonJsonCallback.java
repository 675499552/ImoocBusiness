package com.android.jyc.okhttp.response;

import com.android.jyc.okhttp.listener.DisposeDataListener;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Admin on 2017/2/17.
 */

public class CommonJsonCallback implements Callback {

    //与服务器返回的字段的一个对应关系
    protected final String RESULT_CODE = "ecode"; //有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    private Handler mDeliverHandler;//进行消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;


    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }
}
