package com.mainiway.eworkpal.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mainiway.eworkpal.base.BaseFragment;
import com.mainiway.eworkpal.widgets.RoundedLetterView;

/**
 * 我
 */
public class testFragment extends BaseFragment {

    private TextView tv_me_name;
    private ImageView iv_me_sex;
    private RoundedLetterView mRoundTextName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_me, container, false);
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
