package cn.junhua.android.permission.impl;

import cn.junhua.android.permission.core.Agent;
import cn.junhua.android.permission.core.OverlayPermissionHandler;
import cn.junhua.android.permission.core.callback.Action;
import cn.junhua.android.permission.core.callback.Rationale;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/19 13:39
 */
public class OverlayPermissionAgent implements Agent {

    private OverlayPermissionHandler mPermissionHandler;

    public OverlayPermissionAgent(OverlayPermissionHandler overlayPermissionHandler) {
        mPermissionHandler = overlayPermissionHandler;
    }

    @Override
    public Agent code(int requestCode) {
        return this;
    }

    @Override
    public Agent onGranted(Action onGrantedAction) {
        return this;
    }

    @Override
    public Agent onDenied(Action onDeniedAction) {
        return this;
    }

    @Override
    public Agent onRationale(Rationale rationale) {
        return this;
    }

    @Override
    public void apply() {

    }
}
