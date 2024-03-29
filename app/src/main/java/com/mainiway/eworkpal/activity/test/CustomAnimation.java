package com.mainiway.eworkpal.activity.test;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.mainiway.library.adapter.base.animation.BaseAnimation;

/**
 * 自定义动画
 */
public class CustomAnimation implements BaseAnimation {

    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "scaleY", 1, 1.1f, 1),
                ObjectAnimator.ofFloat(view, "scaleX", 1, 1.1f, 1)
        };
    }
}
