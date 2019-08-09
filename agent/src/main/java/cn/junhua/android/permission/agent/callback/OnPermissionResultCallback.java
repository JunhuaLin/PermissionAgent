package cn.junhua.android.permission.agent.callback;

import android.support.annotation.NonNull;

/**
 * 处理权限申请相关回调
 *
 * @author junhua.lin<br />
 * CREATED 2018/12/6 16:29
 */
public interface OnPermissionResultCallback {
    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
