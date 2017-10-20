package com.demo.tangminglong.fillblankdemo.utils;

import android.content.Context;
import android.util.TypedValue;

/**
*
*@author TML
@description 常用单位转换辅助类
@email 1289079103@qq.com
*create at 2016/6/2 21:44
*/

public class DensityUtils {
    private DensityUtils()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal){

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal){

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal,context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal){

        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal){

        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
