package com.skio.coroutines.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.skio.coroutines.R;


/**
 * Created by liujian on 09/10/2020.
 */

public class RadiosAnimalButtonView extends RelativeLayout {
    private Context mContext;

    private RadiosView mAnimalView;

    private RadiosRotateView mRadioView;

    //缩放加旋转
    public static final int BUTTON_SCAN_RADIOS = 1;
    //旋转无需缩放
    public static final int BUTTON_RADIOS = 2;

    private float textSize;
    private int textColor;
    private int mRadius;
    //button是否选中状态
    private boolean mEnable;
    private String text;
    private int backgroundColor;

    private int mButtonType;


    public RadiosAnimalButtonView(Context context) {
        super(context);
        this.mContext = context;
        initView(null);
    }

    public RadiosAnimalButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(attrs);
    }


    private void initView(AttributeSet attrs) {
        LayoutInflater.from(mContext).inflate(R.layout.item_animal_view, this);
        if (attrs != null) {
            TypedArray attrsArray = mContext.obtainStyledAttributes(attrs, R.styleable.RadiosRotateButton);
            text = attrsArray.getString(R.styleable.RadiosRotateButton_button_text);
            textColor = attrsArray.getColor(R.styleable.RadiosRotateButton_button_textColor, 0);
            backgroundColor = attrsArray.getColor(R.styleable.RadiosRotateButton_button_backgroundColor, 0);
            textSize = attrsArray.getFloat(R.styleable.RadiosRotateButton_button_textSize, 0f);
            mRadius = attrsArray.getInteger(R.styleable.RadiosRotateButton_button_radios, 0);
            mButtonType = attrsArray.getInteger(R.styleable.RadiosRotateButton_button_type, 0);
            mEnable = attrsArray.getBoolean(R.styleable.RadiosRotateButton_button_type, false);
        }
        mAnimalView = findViewById(R.id.radios_animal_view);
        mRadioView = findViewById(R.id.radio_view);
        mAnimalView.setText(text, textSize, 0, backgroundColor, mRadius);
        setButtonEnable(mEnable);
        mAnimalView.setOnAnimalListener(new RadiosView.OnAnimalListener() {
            @Override
            public void onAnimalFinish() {
                mRadioView.startRotateAnimal();
            }

            @Override
            public void onAnimalProcess() {
                mRadioView.setCircleScanX(mAnimalView.scanX);
            }
        });

        mAnimalView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEnable){
                    startButtonAnimal();
                    //为了防止网络请求过快造成动画有点卡顿现象，先让动画执行
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(onRadiosClickListener!=null){
                                onRadiosClickListener.onClick(mAnimalView);
                            }
                        }
                    },500);
                }
            }
        });

    }

    /**
     * 恢复view到初始状态
     */
    public void resetView(){
        if(mAnimalView!=null){
            setButtonClickable(true);
            mRadioView.stopRotateAnimal();
            mAnimalView.resetScanAnimation();
            mRadioView.setCircleScanX(mAnimalView.scanX);


        }
    }



    public OnRadiosClickListener onRadiosClickListener;

    public interface OnRadiosClickListener {
        void onClick(View view);
    }

    public void setOnRadiosClickListener(OnRadiosClickListener onRadiosClickListener) {
        this.onRadiosClickListener = onRadiosClickListener;
    }


    /**
     * 启动动画
     */
    public void startButtonAnimal() {
        setButtonClickable(false);
        switch (mButtonType) {
            case BUTTON_SCAN_RADIOS:
                mAnimalView.startScanAnimation();
                break;
            case BUTTON_RADIOS:
                mAnimalView.setAlphaPaint(View.GONE,true);
                mRadioView.startRotateAnimal();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() > 0) {
            //获得子控件
            View child = getChildAt(0);
            // 计算子视图的左边偏移数值
            int new_left = (r - l) / 2 - child.getMeasuredWidth() / 2;
            // 计算子视图的上方偏移数值
            int new_top = (b - t) / 2 - child.getMeasuredHeight() / 2;
            // 根据最新的上下左右四周边界，重新放置该子视图
            child.layout(new_left + mAnimalView.scanX / 2, new_top,
                    new_left + child.getMeasuredWidth() - mAnimalView.scanX / 2, new_top + child.getMeasuredHeight());
        }

    }


    /**
     * 设置文本
     *
     * @param text 文本内容
     */

    public void setText(String text) {
        mAnimalView.setText(text);
    }


    /**
     * 设置文本大小
     *
     * @param textSize 字体大小 sp
     */
    public void setTextSize(float textSize) {
        mAnimalView.setTextSize(textSize);
    }


    /**
     * 设置文本颜色
     *
     * @param textColor 字体颜色
     */
    public void setTextColor(int textColor) {
        mAnimalView.setTextColor(textColor);
    }


    /**
     * 设置文本内容以及字体大小
     *
     * @param text     文本内容
     * @param textSize 字体大小 sp
     */

    public void setText(String text, float textSize) {
        mAnimalView.setText(text, textSize);
    }

    /**
     * 设置文本内容以及字体颜色
     *
     * @param text      文本内容
     * @param textColor 字体颜色 sp
     */
    public void setText(String text, int textColor) {
        mAnimalView.setText(text, textColor);
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
        mAnimalView.setText(text,textSize, textColor, backGroundColor);
    }

    /**
     * 设置按钮背景颜色
     *
     * @param backGroundColor 背景颜色
     */
    public void setButtonBackGroundColor(int backGroundColor) {
        mAnimalView.setButtonBackGroundColor(backGroundColor);
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
     * 设置按钮动画类型
     *
     * @param mButtonType 设置按钮动画类型
     */
    public void setButtonType(int mButtonType) {
        this.mButtonType = mButtonType;
    }

    /**
     * 设置按钮是否可以点击
     *
     * @param click 设置按钮点击状态
     */
    public void setButtonClickable(boolean click) {
        mAnimalView.setClickable(click);
    }


    /**
     * 设置按钮是否选中
     *
     * @param mEnable 设置按钮选中状态
     */
    public void setButtonEnable(boolean mEnable) {
        this.mEnable = mEnable;
//        if (!mEnable) {
//            mAnimalView.setBackGradient(getResources().getColor(R.color.disabled_c2), getResources().getColor(R.color.disabled_c3));
//        } else {
//            mAnimalView.setBackGradient(getResources().getColor(R.color.c5_start), getResources().getColor(R.color.c5_end));
//        }
    }

    /**
     * 获取按钮选中状态
     */

    public boolean getButtonEnable() {
        return mEnable;
    }

    /**
     * 获取按钮选中状态
     */

    public int getButtonType() {
        return mButtonType;
    }

    public void stopRotateAnimal(){
        if(mRadioView!=null){
            mRadioView.stopRotateAnimal();
        }
    }

    public void destroyRotateAnimal(){
        if(mRadioView!=null){
            mRadioView.destroyRotateAnimal();
        }
    }

}
