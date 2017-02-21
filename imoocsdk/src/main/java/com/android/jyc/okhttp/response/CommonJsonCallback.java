package com.android.jyc.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.android.jyc.okhttp.exception.OKHttpException;
import com.android.jyc.okhttp.listener.DisposeDataHandle;
import com.android.jyc.okhttp.listener.DisposeDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
    protected final int NETWORK_ERROR = -1; //网络异常
    protected final int JSON_ERROR = -2; //JSON 解析异常
    protected final int OTHER_ERROR = -3; //其他异常

    private Handler mDeliverHandler;//进行消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliverHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败处理
    @Override
    public void onFailure(Call call, final IOException ioexception) {
        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OKHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    /**
     * 真正的响应处理函数
     *
     * @param call
     * @param response
     * @throws IOException
     */
    @Override
    public void onResponse(Call call, Response response) throws IOException {
//        if (response.code() ==200){
//            final String result = response.body().string();
//            mDeliverHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    handleResponse(result);
//                }
//            });
//        }else{
//            onFailure(call,null);
//        }

        final String result = response.body().string();
        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });

    }

    /**
     * 处理服务器返回的响应数据
     *
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {
        //为了保证代码的健壮性
        if (responseObj == null && responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OKHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

//   需要根据服务器返回码决定是否成功返回
        try {
            JSONObject reult = new JSONObject(responseObj.toString());

            //开始尝试解析json
            if (reult.has(RESULT_CODE)){
                //从json对象中取出我们的响应吗,若为0，则是正确响应
                if (reult.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        mListener.onSuccess(responseObj);
                    } else {
                        //需要将json对象转化为实体对象
                        Object obj = JSON.parseObject(responseObj.toString(), mClass);
                        //表明正确的转化了实体对象
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            //返回的不是合法的json
                            mListener.onFailure(new OKHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                }else{
                    //讲服务器返回给我们的异常回调到应用处去处理
                    mListener.onFailure(new OKHttpException(OTHER_ERROR,reult.get(RESULT_CODE)));
                }
            }
        } catch (JSONException e) {
            mListener.onFailure(new OKHttpException(OTHER_ERROR,e.getMessage()));
        }


//        try {
//            //从json对象中取出我们的响应吗,若为0，则是正确响应
//            if (mClass == null) {
//                mListener.onSuccess(responseObj);
//            } else {
//                //需要将json对象转化为实体对象
//                Object obj = JSON.parseObject(responseObj.toString(), mClass);
//                //表明正确的转化了实体对象
//                if (obj !=null){
//                    mListener.onSuccess(obj);
//                }else {
//                    //返回的不是合法的json
//                    mListener.onFailure(new OKHttpException(JSON_ERROR,EMPTY_MSG));
//                }
//            }
//        } catch (Exception e) {
//            mListener.onFailure(new OKHttpException(OTHER_ERROR,e.getMessage()));
//        }
    }


}
