package cn.junhua.android.permission.impl.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import cn.junhua.android.permission.core.DangerousPermissionHandler;
import cn.junhua.android.permission.core.callback.OnActivityResultCallback;
import cn.junhua.android.permission.core.callback.OnPermissionResultCallback;
import cn.junhua.android.permission.core.OverlayPermissionHandler;
import cn.junhua.android.permission.utils.PermissionUtil;

/**
 * 用于处理Agent权限请求相关操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:28
 */
public class ResultV4Fragment extends Fragment implements DangerousPermissionHandler, OverlayPermissionHandler {

    private OnPermissionResultCallback mOnPermissionResultCallback;
    private OnActivityResultCallback mOnActivityResultCallback;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void requestAlertWindowPermission(int requestCode) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getContext().getPackageName()));
        startActivityForResult(intent, requestCode);
    }

    public void setOnPermissionResultCallback(OnPermissionResultCallback mOnPermissionResultCallback) {
        this.mOnPermissionResultCallback = mOnPermissionResultCallback;
    }

    @Override
    public boolean hasPermission(@NonNull String... permissions) {
        return PermissionUtil.hasPermission(getContext(), permissions);
    }

    @Override
    public boolean shouldShowRationale(@NonNull String permission) {
        return PermissionUtil.shouldShowRationale(getActivity(), permission);
    }

    @Override
    public void setActivityResultCallback(OnActivityResultCallback onActivityResultCallback) {
        this.mOnActivityResultCallback = onActivityResultCallback;
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
