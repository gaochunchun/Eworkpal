/*******************************************************************************
 *
 * Copyright (c) Baina Info Tech Co. Ltd
 *
 * FragmentCallback
 *
 * app.ui.fragment.FragmentCallback.java
 * TODO: File description or class description.
 *
 * @author: gao_chun
 * @since:  Feb 16, 2015
 * @version: 1.0.0
 *
 * @changeLogs:
 *     1.0.0: First created this class.
 *
 ******************************************************************************/
package com.mainiway.eworkpal.callback;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * FragmentCallback
 */
public interface FragmentCallback {

    public void onFragmentCallback(Fragment fragment, int id, Bundle args);
}

