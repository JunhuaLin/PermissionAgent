package cn.junhua.android.permission.core;

import cn.junhua.android.permission.core.callback.OnActivityResultCallback;

/**
 * 申请权限需要的操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 14:42
 */
public interface OverlayPermissionHandler {

    /**
     * 悬浮窗权限
     */
    void requestAlertWindowPermission(int requestCode);

    void setActivityResultCallback(OnActivityResultCallback onActivityResultCallback);
}
