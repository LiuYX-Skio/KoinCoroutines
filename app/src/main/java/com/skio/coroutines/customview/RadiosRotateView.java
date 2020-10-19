package com.skio.coroutines.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * Created by liujian on 09/10/2020.
 */

public class RadiosRotateView extends View {

    private Paint mCirclePaint;
    private int mWidth;
    private int mScanX;
    private ObjectAnimator mObjectAnimator;

    public RadiosRotateView(Context context) {
        super(context);
        initView();

    }

    public RadiosRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        getSize(widthMeasureSpec, heightMeasureSpec);
        int size[] = getMeasureSize(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size[0], size[1]);
    }

    private void initView() {
        mCirclePaint = new Paint();
        mCirclePaint.setDither(true);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setStrokeWidth(4);
        mCirclePaint.setColorFilter(new ColorFilter());

    }

    private int mHeight;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = mWidth / 2 - mHeight / 2;
        Log.w("数据返回状态","=="+mWidth+"==="+mHeight);
        //设置矩形内边距
        float padding = mHeight / 6;
        RectF oval = new RectF(x + padding, padding, x + mHeight - padding, mHeight - padding);
        canvas.drawArc(oval, 0, 340, false, mCirclePaint);
    }

    public void setCircleScanX(int mScanX) {
        this.mScanX = mScanX;
        requestLayout();
    }

    /**
     * 开启旋转动画
     */
    public void startRotateAnimal() {
        setVisibility(VISIBLE);
        mObjectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0, 359);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.setDuration(1600);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.start();
    }

    /**
     * 计算移动后的实际控件的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    private void getSize(int widthMeasureSpec, int heightMeasureSpec) {
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    /**
     * 计算移动后的实际控件的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    private int[] getMeasureSize(int widthMeasureSpec, int heightMeasureSpec) {
        int[] size = new int[2];
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //判断控件宽高，最终最小值最为圆的直径
        if (mWidth >= mHeight) {
            size[0] = MeasureSpec.makeMeasureSpec(mWidth - mScanX, widthMode);
            size[1] = MeasureSpec.makeMeasureSpec(mHeight, heightMode);
        } else {
            size[0] = MeasureSpec.makeMeasureSpec(mWidth, widthMode);
            size[1] = MeasureSpec.makeMeasureSpec(mHeight - mScanX, heightMode);
        }
        return size;

    }

    public void stopRotateAnimal(){
//        setVisibility(GONE);
        if(mObjectAnimator!=null){
            mObjectAnimator.pause();
        }
    }

    public void destroyRotateAnimal(){
        if(mObjectAnimator!=null){
            mObjectAnimator.pause();
            mObjectAnimator.cancel();
            mObjectAnimator.clone();
        }
    }

}
