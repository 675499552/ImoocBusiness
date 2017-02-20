package com.android.jyc.okhttp.listener;

/**
 * Created by Admin on 2017/2/17.
 */

public class DisposeDataHandle {

    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener dataListener) {
        this.mListener = dataListener;
    }

    public DisposeDataHandle(DisposeDataListener dataListener, Class<?> clazz) {
        this.mListener = dataListener;
        this.mClass = clazz;
    }
}
