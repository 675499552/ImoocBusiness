package com.android.jyc.okhttp.exception;

/**
 * Created by Admin on 2017/2/20.
 */

public class OKHttpException extends Exception {

    private static final long serialVersionUID = 1;

    private int ecode;

    private Object emsg;

    public OKHttpException(int ecode,Object emsg){
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public void setEcode(int ecode) {
        this.ecode = ecode;
    }

    public Object getEmsg() {
        return emsg;
    }

    public void setEmsg(Object emsg) {
        this.emsg = emsg;
    }
}
