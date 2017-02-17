package com.android.jyc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.jyc.okhttp.CommonOkHttpClient;
import com.android.jyc.okhttp.request.CommonRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Admin on 2017/2/17.
 */

public class BaseOkhttpTest extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void test(){
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("http://www.imooc.com", null), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //1.异常 2.数据解析 3.数据转发
            }
        });
    }
}
