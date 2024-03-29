package com.mainiway.eworkpal.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.mainiway.eworkpal.R;

import java.util.Arrays;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-11-19.
 * 描    述：SideBarView
 * ===========================================
 */
public class SideBarView extends View {
    private final static int DEFAULT_TEXT_SIZE = 15; // sp
    private final static int DEFAULT_MAX_OFFSET = 80; //dp

    private final static String[] DEFAULT_INDEX_ITEMS = {"↑","A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private String[] mIndexItems;

    /**
     * the index in {@link #mIndexItems} of the current selected index item,
     * it's reset to -1 when the finger up
     */
    private int mCurrentIndex = -1;

    /**
     * Y coordinate of the point where finger is touching,
     * the baseline is top of {@link #mStartTouchingArea}
     * it's reset to -1 when the finger up
     */
    private float mCurrentY = -1;

    private Paint mPaint;
    private int mTextColor;
    private float mTextSize;

    /**
     * the height of each index item
     */
    private float mIndexItemHeight;

    /**
     * offset of the current selected index item
     */
    private float mMaxOffset;

    /**
     * {@link #mStartTouching} will be set to true when {@link MotionEvent#ACTION_DOWN}
     * happens in this area, and the side bar should start working.
     */
    private RectF mStartTouchingArea = new RectF();

    /**
     * height and width of {@link #mStartTouchingArea}
     */
    private float mBarHeight;
    private float mBarWidth;

    /**
     * Flag that the finger is starting touching.
     * If true, it means the {@link MotionEvent#ACTION_DOWN} happened but
     * {@link MotionEvent#ACTION_UP} not yet.
     */
    private boolean mStartTouching = false;

    /**
     * if true, the {@link OnSelectIndexItemListener#onSelectIndexItem(String)}
     * will not be called until the finger up.
     * if false, it will be called when the finger down, up and move.
     */
    private boolean mLazyRespond = false;

    /**
     * the position of the side bar, default is {@link #POSITION_RIGHT}.
     * You can set it to {@link #POSITION_LEFT} for people who use phone with left hand.
     */
    private int mSideBarPosition;
    public static final int POSITION_RIGHT = 0;
    public static final int POSITION_LEFT = 1;

    /**
     * observe the current selected index item
     */
    private OnSelectIndexItemListener onSelectIndexItemListener;

    /**
     * the baseline of the first index item text to draw
     */
    private float mFirstItemBaseLineY;

    /**
     * for {@link #dp2px(int)} and {@link #sp2px(int)}
     */
    private DisplayMetrics mDisplayMetrics;


    public SideBarView(Context context) {
        this(context, null);
    }

    public SideBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDisplayMetrics = context.getResources().getDisplayMetrics();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideBarView);
        mLazyRespond = typedArray.getBoolean(R.styleable.SideBarView_sidebar_lazy_respond, false);
        mTextColor = typedArray.getColor(R.styleable.SideBarView_sidebar_text_color, Color.GRAY);
        mMaxOffset = typedArray.getDimension(R.styleable.SideBarView_sidebar_max_offset, dp2px(DEFAULT_MAX_OFFSET));
        mSideBarPosition = typedArray.getInt(R.styleable.SideBarView_sidebar_position, POSITION_RIGHT);
        typedArray.recycle();

        mTextSize = sp2px(DEFAULT_TEXT_SIZE);

        mIndexItems = DEFAULT_INDEX_ITEMS;

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mIndexItemHeight = fontMetrics.bottom - fontMetrics.top;
        mBarHeight = mIndexItems.length * mIndexItemHeight;

        // calculate the width of the longest text as the width of side bar
        for (String indexItem : mIndexItems) {
            mBarWidth = Math.max(mBarWidth, mPaint.measureText(indexItem));
        }

        float areaLeft = (mSideBarPosition == POSITION_LEFT) ? 0 : (width - mBarWidth - getPaddingRight());
        float areaRight = (mSideBarPosition == POSITION_LEFT) ? (getPaddingLeft() + areaLeft + mBarWidth) : width;
        float areaTop = height/2 - mBarHeight/2;
        float areaBottom = areaTop + mBarHeight;
        mStartTouchingArea.set(
                areaLeft,
                areaTop,
                areaRight,
                areaBottom);

        // the baseline Y of the first item' text to draw
        mFirstItemBaseLineY = (height/2 - mIndexItems.length*mIndexItemHeight/2)
                + (mIndexItemHeight/2 - (fontMetrics.descent-fontMetrics.ascent)/2)
                - fontMetrics.ascent;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw each item
        for (int i = 0, mIndexItemsLength = mIndexItems.length; i < mIndexItemsLength; i++) {
            float baseLineY = mFirstItemBaseLineY + mIndexItemHeight*i;

            // calculate the scale factor of the item to draw
            float scale = getScale(i);

            int alphaScale = (i == mCurrentIndex) ? (255) : (int) (255 * (1-scale));
            mPaint.setAlpha(alphaScale);

            mPaint.setTextSize(mTextSize + mTextSize*scale);

            float drawX = (mSideBarPosition == POSITION_LEFT) ?
                    (getPaddingLeft() + mBarWidth/2 + mMaxOffset*scale) :
                    (getWidth() - getPaddingRight() - mBarWidth/2 - mMaxOffset*scale);

            // draw
            canvas.drawText(
                    mIndexItems[i], //item text to draw
                    drawX, //center text X
                    baseLineY, // baseLineY
                    mPaint);
        }
    }

    /**
     * calculate the scale factor of the item to draw
     *
     * @param index the index of the item in array {@link #mIndexItems}
     * @return the scale factor of the item to draw
     */
    private float getScale(int index) {
        float scale = 0;
        if (mCurrentIndex != -1) {
            float distance = Math.abs(mCurrentY - (mIndexItemHeight*index+mIndexItemHeight/2)) / mIndexItemHeight;
            scale = 1 - distance*distance/16;
            scale = Math.max(scale, 0);
//                Log.i("scale", mIndexItems[index] + ": " + scale);
        }
        return scale;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mIndexItems.length == 0) {
            return super.onTouchEvent(event);
        }

        float eventY = event.getY();
        float eventX = event.getX();
        mCurrentIndex = getSelectedIndex(eventY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mStartTouchingArea.contains(eventX, eventY)) {
                    mStartTouching = true;
                    if (!mLazyRespond && onSelectIndexItemListener != null) {
                        onSelectIndexItemListener.onSelectIndexItem(mIndexItems[mCurrentIndex]);
                    }
                    invalidate();
                    return true;
                } else {
                    mCurrentIndex = -1;
                    return false;
                }

            case MotionEvent.ACTION_MOVE:
                if (mStartTouching && !mLazyRespond && onSelectIndexItemListener != null) {
                    onSelectIndexItemListener.onSelectIndexItem(mIndexItems[mCurrentIndex]);
                }
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mLazyRespond && onSelectIndexItemListener != null) {
                    onSelectIndexItemListener.onSelectIndexItem(mIndexItems[mCurrentIndex]);
                }
                mCurrentIndex = -1;
                mStartTouching = false;
                invalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }

    private int getSelectedIndex(float eventY) {
        mCurrentY = eventY - (getHeight()/2 - mBarHeight /2);
        if (mCurrentY <= 0) {
            return 0;
        }

        int index = (int) (mCurrentY / this.mIndexItemHeight);
        if (index >= this.mIndexItems.length) {
            index = this.mIndexItems.length - 1;
        }
        return index;
    }

    private float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.mDisplayMetrics);
    }

    private float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this.mDisplayMetrics);
    }

    public void setIndexItems(String... indexItems) {
        mIndexItems = Arrays.copyOf(indexItems, indexItems.length);
        requestLayout();
    }

    public void setTextColor(int color) {
        mTextColor = color;
        mPaint.setColor(color);
        invalidate();
    }

    public void setPosition(int position) {
        if (position != POSITION_RIGHT && position != POSITION_LEFT) {
            throw new IllegalArgumentException("the position must be POSITION_RIGHT or POSITION_LEFT");
        }

        mSideBarPosition = position;
        requestLayout();
    }

    public void setMaxOffset(int offset) {
        mMaxOffset = offset;
        invalidate();
    }

    public void setLazyRespond(boolean lazyRespond) {
        mLazyRespond = lazyRespond;
    }

    public void setOnSelectIndexItemListener(OnSelectIndexItemListener onSelectIndexItemListener) {
        this.onSelectIndexItemListener = onSelectIndexItemListener;
    }

    public interface OnSelectIndexItemListener {
        void onSelectIndexItem(String index);
    }
}
