package com.android.jyc.adutil;

import android.content.Context;


/**
 * @author qndroid
 */
public class Utils {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale);
    }

}
