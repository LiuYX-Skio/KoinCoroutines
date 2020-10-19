package com.skio.coroutines.customview;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by liujian on 09/10/2020.
 */

public class RadiosView extends View {

    private static final String PROPERTY_RADIUS = "property_radius";
    private static final String PROPERTY_SCAN = "property_scan";

    private static final int ANIMAL_TIME = 400;

    private LinearGradient backGradient;

    //起始颜色
    private int mStartColor = Color.parseColor("#00B5E4");
    //终止颜色
    private int mEndColor = Color.parseColor("#377BE6");
    //白色
    private int mWhiteColor = Color.parseColor("#FFFFFF");


    private RectF rectF = new RectF();

    private int mRadius = 0;
    private Paint mPaint;
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private ValueAnimator animator;
    private int mHeight;
    public int mWidth;
    //缩放的总距离(这个值会因为布局多次测量，数据变动，在测量时候第一次用到之后不会用到这个数据)
    private int mScanDistance;
    //最终测量完成后的缩放总距离（记录测量完成后真实的距离）
    private int mScanDistanceFinal;
    //正在缩放的距离
    public int scanX;

    private String mText;

    public RadiosView(Context context) {
        super(context);
        initView();
    }

    public RadiosView(Context context, AttributeSet attrs) {
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
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mCirclePaint = new Paint();
        mTextPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setColorFilter(new ColorFilter());
        mCirclePaint.setDither(true);
        mCirclePaint.setStrokeWidth(4);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setColorFilter(new ColorFilter());
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setStrokeWidth(8);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        backGradient = new LinearGradient(0, 0, w, 0, new int[]{mStartColor, mEndColor}, null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(0, 0, mWidth, mHeight, mRadius, mRadius, mPaint);
        } else {
            rectF.set(0, 0, mWidth, mHeight);
            canvas.drawRoundRect(rectF, mRadius, mRadius, mPaint);
        }
        float x = getWidth() / 2 - getHeight() / 2;
        //设置矩形内边距
        float padding = mHeight / 6;
        RectF oval = new RectF(x + padding, padding, x + mHeight - padding, mHeight - padding);
//        canvas.drawArc(oval,0,340,false,circlePaint);
        if (!TextUtils.isEmpty(mText)) {
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
            float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
            int baseLineY = (int) (oval.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
            canvas.drawText(mText, mWidth / 2, baseLineY, mTextPaint);
        }

    }


    /**
     * 开启缩放动画
     */
    public void startScanAnimation() {
        setAlphaPaint(GONE,false);
        //圆半径变化
        PropertyValuesHolder propertyRadius = PropertyValuesHolder.ofInt(PROPERTY_RADIUS, mRadius, mHeight / 2);
        //缩放比例变化
        PropertyValuesHolder propertyRotate = PropertyValuesHolder.ofInt(PROPERTY_SCAN, mScanDistance);
        mScanDistanceFinal = mScanDistance;

        animator = new ValueAnimator();
        animator.setValues(propertyRadius, propertyRotate);
        animator.setDuration(ANIMAL_TIME);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadius = (int) animation.getAnimatedValue(PROPERTY_RADIUS);
                scanX = (int) animation.getAnimatedValue(PROPERTY_SCAN);
                invalidate();
                requestLayout();
                if (scanX == mScanDistanceFinal) {
                    if (onAnimalListener != null) {
                        onAnimalListener.onAnimalFinish();
                    }
                } else {
                    if (onAnimalListener != null) {
                        onAnimalListener.onAnimalProcess();
                    }
                }

            }
        });
        animator.start();
    }

    /**
     * 重置动画，恢复view初始状态
     */
    public void resetScanAnimation() {
        mRadius = mHeight / 2;
        setAlphaPaint(View.VISIBLE,false);
        scanX = 2;
        invalidate();
        requestLayout();

//        //圆半径变化
//        PropertyValuesHolder propertyRadius = PropertyValuesHolder.ofInt(PROPERTY_RADIUS, mHeight / 2, mRadius);
//        //缩放比例变化
//        PropertyValuesHolder propertyRotate = PropertyValuesHolder.ofInt(PROPERTY_SCAN, mScanDistance);
//        mScanDistanceFinal = mScanDistance;
//
//        animator = new ValueAnimator();
//        animator.setValues(propertyRadius, propertyRotate);
//        animator.setDuration(0);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mRadius = (int) animation.getAnimatedValue(PROPERTY_RADIUS);
//                scanX = 0;
//                invalidate();
//                requestLayout();
//            }
//        });
//        animator.start();
    }

    public OnAnimalListener onAnimalListener;

    public interface OnAnimalListener {
        void onAnimalFinish();

        void onAnimalProcess();
    }

    public void setOnAnimalListener(OnAnimalListener onAnimalListener) {
        this.onAnimalListener = onAnimalListener;
    }


    /**
     * 计算背景渐变色
     *
     * @param mStartColor 渐变起始背景颜色
     * @param mEndColor   渐变终止背景颜色
     */

    public void setBackGradient(int mStartColor, int mEndColor) {
        this.mStartColor = mStartColor;
        this.mEndColor = mEndColor;
        backGradient = new LinearGradient(0, 0, getWidth(), 0, new int[]{mStartColor, mEndColor}, null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);
        invalidate();
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
        mScanDistance = Math.abs(mWidth - mHeight);
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
        mScanDistance = Math.abs(mWidth - mHeight);
        //判断控件宽高，最终最小值最为圆的直径
        if (mWidth >= mHeight) {
            size[0] = MeasureSpec.makeMeasureSpec(mWidth - scanX, widthMode);
            size[1] = MeasureSpec.makeMeasureSpec(mHeight, heightMode);
        } else {
            size[0] = MeasureSpec.makeMeasureSpec(mWidth, widthMode);
            size[1] = MeasureSpec.makeMeasureSpec(mHeight - scanX, heightMode);
        }
        return size;

    }


    /**
     * 设置文本
     *
     * @param text 文本内容
     */

    public void setText(String text) {
        this.mText = text;
        invalidate();
    }

    /**
     * 设置文本大小
     *
     * @param textSize 字体大小 sp
     */
    public void setTextSize(float textSize) {
        mTextPaint.setTextSize(textSize);
        invalidate();
    }


    /**
     * 设置文本颜色
     *
     * @param textColor 字体颜色
     */
    public void setTextColor(int textColor) {
        mTextPaint.setColor(textColor);
        invalidate();
    }


    /**
     * 设置文本内容以及字体大小
     *
     * @param text     文本内容
     * @param textSize 字体大小 sp
     */

    public void setText(String text, float textSize) {
        this.mText = text;
        mTextPaint.setTextSize(textSize);
        invalidate();
    }

    /**
     * 设置文本内容以及字体颜色
     *
     * @param text      文本内容
     * @param textColor 字体颜色 sp
     */
    public void setText(String text, int textColor) {
        this.mText = text;
        mTextPaint.setColor(textColor);
        invalidate();
    }

    /**
     * 设置文本内容以及字体颜色
     *
     * @param text            文本内容
     * @param textSize        字体大小 sp
     * @param textColor       字体颜色
     * @param backGroundColor 按钮背景颜色
     */
    public void setText(String text, float textSize, int textColor, int backGroundColor) {
        this.mText = text;
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(textColor);
        mPaint.setColor(backGroundColor);
        invalidate();
    }

    /**
     * 设置文本内容以及字体颜色
     *
     * @param text            文本内容
     * @param textSize        字体大小 sp
     * @param textColor       字体颜色
     * @param backGroundColor 按钮背景颜色
     */
    public void setText(String text, float textSize, int textColor, int backGroundColor, int mRadius) {
        this.mText = text;
        this.mRadius = mRadius;
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(textColor == 0 ? mWhiteColor : textColor);
        mPaint.setColor(backGroundColor == 0 ? mStartColor : backGroundColor);
        invalidate();
    }

    /**
     * 设置按钮背景颜色
     *
     * @param backGroundColor 背景颜色
     */
    public void setButtonBackGroundColor(int backGroundColor) {
        mPaint.setColor(backGroundColor);
        invalidate();
    }

    /**
     * 设置按钮圆角
     *
     * @param radius 设置按钮初始圆角
     */
    public void setRadios(int radius) {
        this.mRadius = radius;
    }

    /**
     * 设置是否显示文字
     */
    public void setAlphaPaint(int visible,boolean isRefresh) {
        if (mTextPaint != null) {
            mTextPaint.setAlpha(visible == View.VISIBLE ? 100 : 0);
        }
        if(isRefresh){
            invalidate();
        }

    }

}
