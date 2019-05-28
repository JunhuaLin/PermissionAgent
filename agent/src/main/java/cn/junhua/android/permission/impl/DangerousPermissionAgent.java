package cn.junhua.android.permission.impl;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.junhua.android.permission.core.AgentExecutor;
import cn.junhua.android.permission.core.DangerousPermissionHandler;
import cn.junhua.android.permission.core.callback.OnPermissionResultCallback;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/19 13:39
 */
public class DangerousPermissionAgent extends BaseAgent implements OnPermissionResultCallback {
    private static final String TAG = "Agent";

    private DangerousPermissionHandler mPermissionHandler;
    private String[] mPermissions;

    public DangerousPermissionAgent(DangerousPermissionHandler permissionHandler, String[] permissions) {
        mPermissionHandler = permissionHandler;
        mPermissions = permissions;
        mPermissionHandler.setOnPermissionResultCallback(this);
    }


    /**
     * 执行请求操作
     */
    public void apply() {
        if (mPermissionHandler.hasPermission(mPermissions)) {
            if (mOnGrantedCallback != null) {
                mOnGrantedCallback.onGranted(mPermissions);
            }
            return;
        }

        //直接请求权限
        if (mOnRationaleCallback == null) {
            requestPermissions();
            return;
        }

        //给用户提示再请求权限
        List<String> rationaleList = new ArrayList<>();
        for (String permission : mPermissions) {
            if (mPermissionHandler.shouldShowRationale(permission)) {
                rationaleList.add(permission);
            }
        }
        if (rationaleList.isEmpty()) {
            requestPermissions();
        } else {
            String[] rationaleArr = new String[rationaleList.size()];
            rationaleList.toArray(rationaleArr);
            mOnRationaleCallback.onRationale(rationaleArr, new AgentExecutor() {
                @Override
                public void execute() {
                    requestPermissions();
                }
            });
        }
    }

    private void requestPermissions() {
        mPermissionHandler.requestPermissions(mPermissions, mRequestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = " + Arrays.toString(permissions) + ", grantResults = " + Arrays.toString(grantResults));
        if (requestCode != mRequestCode || grantResults.length <= 0) return;

        List<String> grantedList = new ArrayList<>();
        List<String> deniedList = new ArrayList<>();
        for (int index = 0; index < grantResults.length; index++) {
            if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                grantedList.add(permissions[index]);
            } else {
                deniedList.add(permissions[index]);
            }
        }

        if (!grantedList.isEmpty() && mOnGrantedCallback != null) {
            String[] grantedArr = new String[grantedList.size()];
            grantedList.toArray(grantedArr);
            mOnGrantedCallback.onGranted(grantedArr);
        }

        if (!deniedList.isEmpty() && mOnDeniedCallback != null) {
            String[] deniedArr = new String[deniedList.size()];
            deniedList.toArray(deniedArr);
            mOnDeniedCallback.onDenied(deniedArr);
        }
    }
}
