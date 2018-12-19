package cn.junhua.android.permission.core;

import android.support.annotation.NonNull;

import cn.junhua.android.permission.core.callback.OnPermissionResultCallback;

/**
 * 申请权限需要的操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 14:42
 */
public interface DangerousPermissionHandler {

    /**
     * 请求权限
     */
    void requestPermissions(@NonNull String[] permissions, int requestCode);


    void setOnPermissionResultCallback(OnPermissionResultCallback onPermissionResultCallback);

    /**
     * 判断是否有权限
     */
    boolean hasPermission(@NonNull String... permissions);

    /**
     * 判断是否给提示
     */
    boolean shouldShowRationale(@NonNull String permission);

}
