package cn.junhua.android.permission.agent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import cn.junhua.android.permission.agent.callback.OnActivityResultCallback;
import cn.junhua.android.permission.agent.callback.OnPermissionResultCallback;

/**
 * 申请权限需要的操作
 *
 * @author junhua.lin<br />
 * CREATED 2018/12/7 14:42
 */
public interface PermissionHandler {

    /**
     * 请求权限
     */
    void requestPermissions(@NonNull String[] permissions, int requestCode);


    /**
     * 接收回调
     */
    void setOnPermissionResultCallback(OnPermissionResultCallback onPermissionResultCallback);

    /**
     * 特殊权限请求
     *
     * @param intent      含有特殊权限Action的Intent
     * @param requestCode requestCode
     */
    void startActivityForResult(Intent intent, int requestCode);

    void startActivity(Intent intent);

    /**
     * 接收回调
     */
    void setActivityResultCallback(OnActivityResultCallback onActivityResultCallback);


    boolean hasPermission(String permission);

    boolean shouldShowRationale(String permission);


    Activity getActivity();


    Context getContext();

}
