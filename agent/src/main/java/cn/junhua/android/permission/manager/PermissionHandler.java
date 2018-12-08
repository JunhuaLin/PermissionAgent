package cn.junhua.android.permission.manager;

import android.support.annotation.NonNull;

/**
 * 申请权限需要的操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 14:42
 */
public interface PermissionHandler {

    /**
     * 请求权限
     */
    void requestPermissions(@NonNull String[] permissions, int requestCode);
}
