package com.mainiway.eworkpal.base; /**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/11/18.
 * 描    述：
 * ===========================================
 */


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.utils.HideIMEUtil;


/**
 *
 * 自定义顶部标题栏
 */
public class BaseTitleActivity extends BaseActivity implements OnClickListener{

    //private RelativeLayout mLayoutTitleBar;
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        HideIMEUtil.wrap(this);
    }

    /*  protected void isHideTitleBar(boolean show)
    {
        if(mLayoutTitleBar != null)
        {
            if(show)
            {
                mLayoutTitleBar.setVisibility(View.GONE);
            }else
            {
                mLayoutTitleBar.setVisibility(View.VISIBLE);
            }
        }
    }*/

    //back,显示图标和文字
    /*protected void showBackwardView(int backwardResid, boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText(backwardResid);
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }*/

    //back，只显示图标
    protected void showBackwardView(boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText("");
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }


    /**
     * forward 右侧标题栏 图标 - 文字
     * @param mtext
     * @param drawableId
     * @param leftOrRight 0表示图标在左边  其他表示在右边
     */
    protected void showForwardView(CharSequence mtext, int drawableId,int leftOrRight) {
        if (mForwardButton != null) {

            mForwardButton.setVisibility(View.VISIBLE);
            mForwardButton.setText(mtext);

            Drawable drawable = getResources().getDrawable(drawableId);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            if (leftOrRight ==0) {
                mForwardButton.setCompoundDrawables(drawable,null,null,null);
            }else {
                mForwardButton.setCompoundDrawables(null,null,drawable,null);
            }

        }// else ignored
    }


    /**
     * forward 右侧标题栏 图标
     * @param drawableId
     */
    protected void showForwardView(int drawableId) {
        if (mForwardButton != null) {

            mForwardButton.setVisibility(View.VISIBLE);

            Drawable drawable = getResources().getDrawable(drawableId);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            mForwardButton.setCompoundDrawables(null,null,drawable,null);

        }// else ignored
    }

    /**
     * 只显示文字
     * @param mtext
     */
    protected void showForwardView(CharSequence mtext) {
        if (mForwardButton != null) {

            mForwardButton.setVisibility(View.VISIBLE);
            mForwardButton.setText(mtext);
            mForwardButton.setCompoundDrawables(null,null,null,null);
        }// else ignored
    }


    //forward,显示图标
    protected void showForwardView(boolean show) {
        if (mForwardButton != null) {
            if (show) {
                mForwardButton.setVisibility(View.VISIBLE);
            } else {
                mForwardButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }


    protected void onBackward(View backwardView) {
        finish();
    }


    protected void onForward(View forwardView) {

    }


    /* (non-Javadoc)
     * @see android.app.Activity#setTitle(int)
     */
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setTitle(java.lang.CharSequence)
     */
    @Override
    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setTitleColor(int)
     */
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }


    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(int)
     */
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View)
     */
    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View view, LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_backward:
                onBackward(v);
                break;

            case R.id.button_forward:
                onForward(v);
                break;

            default:
                break;
        }
    }

    private void setupViews() {
        super.setContentView(R.layout.activity_title);
        //mLayoutTitleBar = (RelativeLayout)findViewById(R.id.layout_titlebar);
        mTitleTextView = (TextView) findViewById(R.id.text_title);
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        mBackwardbButton = (Button) findViewById(R.id.button_backward);
        mForwardButton = (Button) findViewById(R.id.button_forward);
        //mProgress = (ProgressBar) findViewById(R.id.progress);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
