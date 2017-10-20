package com.demo.tangminglong.fillblankdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ReplacementSpan;
import android.view.MotionEvent;
import android.widget.TextView;

import com.demo.tangminglong.fillblankdemo.utils.DensityUtils;

/**
 * Created by tangminglong on 17/10/19.
 * 自定义Span，用来绘制填空题
 */

public class ReplaceSpan extends ReplacementSpan {

    private final Context context;
    public String mText = "";//保存的String

    private final Paint mPaint;

    public Object mObject;//回调中的任意对象
    private int textWidth = 80;//单词的宽度

    public OnClickListener mOnClick;
    public int id = 0;//回调中的对应Span的ID


    public ReplaceSpan(Context context,Paint paint) {
        this.context = context;
        mPaint = paint;
        textWidth = DensityUtils.dp2px(context,textWidth);

    }

    public void setDrawTextColor(int res){
        mPaint.setColor(context.getResources().getColor(res));
    }


    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        //返回自定义Span宽度

        return textWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {

        float bottom1 =  paint.getFontMetrics().bottom;
        float y1 = y+bottom1;

        CharSequence ellipsize = TextUtils.ellipsize(mText, (TextPaint) paint, textWidth, TextUtils.TruncateAt.END);
        int width = (int)paint.measureText(ellipsize,0,ellipsize.length());

        width = (textWidth-width)/2;

        canvas.drawText(ellipsize,0,ellipsize.length(),  x+width, (float) y,mPaint);

        //需要填写的单词下方画线
        //这里bottom-1，是为解决有时候下划线超出canvas
        Paint linePaint = new Paint();

        linePaint.setColor(mPaint.getColor());
        linePaint.setStrokeWidth(2);
        canvas.drawLine(x, y1, x + textWidth, y1, linePaint);
    }

    public void onClick(TextView v, Spannable buffer, boolean isDown, int x, int y, int line, int off){
        if (mOnClick != null){
            mOnClick.OnClick(v,id,this);
        }
    }

    public  interface OnClickListener{
        void OnClick(TextView v, int id, ReplaceSpan span);
    }

}