package cn.junhua.android.permission.impl.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.callback.OnActivityResultCallback;
import cn.junhua.android.permission.agent.callback.OnPermissionResultCallback;

/**
 * 用于处理Agent权限请求相关操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:28
 */
public class SupportV4PermissionHandler extends Fragment implements PermissionHandler {
    public final static String FRAGMENT_TAG = "ResultV4Fragment_1206";

    private OnPermissionResultCallback mOnPermissionResultCallback;
    private OnActivityResultCallback mOnActivityResultCallback;

    public void setOnPermissionResultCallback(OnPermissionResultCallback mOnPermissionResultCallback) {
        this.mOnPermissionResultCallback = mOnPermissionResultCallback;
    }

    @Override
    public void setActivityResultCallback(OnActivityResultCallback onActivityResultCallback) {
        this.mOnActivityResultCallback = onActivityResultCallback;
    }

    @Override
    public boolean hasPermission(String permission) {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(getContext(), permission);
    }

    @Override
    public boolean shouldShowRationale(String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mOnActivityResultCallback != null) {
            mOnActivityResultCallback.onActivityResultCallback(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mOnPermissionResultCallback != null) {
            mOnPermissionResultCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
