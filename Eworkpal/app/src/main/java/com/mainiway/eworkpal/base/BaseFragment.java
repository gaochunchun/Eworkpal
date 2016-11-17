package com.mainiway.eworkpal.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.mainiway.eworkpal.callback.FragmentCallback;

public abstract class BaseFragment extends Fragment implements OnTouchListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 模拟后退键
     */
    /*protected void ic_back() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStackImmediate();
    }*/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.setOnTouchListener(this);
        super.onViewCreated(view, savedInstanceState);
        //HideIMEUtil.wrap(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 拦截触摸事件，防止传递到下一层的View
        return true;
    }


    /**
     * Fragment向Activity传值
     * @param id
     * @param args
     */
    public void dispatchCommand(int id, Bundle args) {
        if (getActivity() instanceof FragmentCallback) {
            FragmentCallback callback = (FragmentCallback) getActivity();
            callback.onFragmentCallback(this, id, args);
        } else {
            throw new IllegalStateException("The host activity does not implement FragmentCallback.");
        }
    }

    /**
     * Fragment状态显示时
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }


    /**
     * 替代findviewById
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T find(View view, int id)
    {
        return (T) view.findViewById(id);
    }

}
