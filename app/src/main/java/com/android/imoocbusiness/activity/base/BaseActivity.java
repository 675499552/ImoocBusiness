package com.android.imoocbusiness.activity.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @function 是为所有的activity提供公共的行为
 * Created by Admin on 2017/2/17.
 */

public class BaseActivity extends AppCompatActivity{

    /**
     * 输出日志,所需tag
     */
    public String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        TAG = getComponentName().getShortClassName();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
