package cn.junhua.android.permission.impl;

import android.content.Intent;

import cn.junhua.android.permission.core.OverlayPermissionHandler;
import cn.junhua.android.permission.core.callback.OnActivityResultCallback;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/19 13:39
 */
public class OverlayPermissionAgent extends BaseAgent implements OnActivityResultCallback {

    private OverlayPermissionHandler mPermissionHandler;

    public OverlayPermissionAgent(OverlayPermissionHandler overlayPermissionHandler) {
        mPermissionHandler = overlayPermissionHandler;
        mPermissionHandler.setActivityResultCallback(this);
    }

    @Override
    public void apply() {
        if (mPermissionHandler.canDrawOverlays()) {
            mOnGrantedCallback.onGranted(null);
            return;
        }

        mPermissionHandler.requestAlertWindowPermission(mRequestCode);
    }

    @Override
    public void onActivityResultCallback(int requestCode, int resultCode, Intent data) {
        if (mRequestCode == requestCode) {

        }

    }
}
