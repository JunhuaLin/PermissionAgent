package cn.junhua.android.permission.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import cn.junhua.android.permission.core.DangerousPermissionHandler;
import cn.junhua.android.permission.core.callback.OnActivityResultCallback;
import cn.junhua.android.permission.core.callback.OnPermissionResultCallback;
import cn.junhua.android.permission.core.OverlayPermissionHandler;
import cn.junhua.android.permission.impl.fragment.ResultFragment;
import cn.junhua.android.permission.impl.fragment.ResultV4Fragment;
import cn.junhua.android.permission.utils.PermissionUtil;

/**
 * 执行权限请求的管理类
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/10 15:06
 */
public class PermissionHandlerImpl implements DangerousPermissionHandler, OverlayPermissionHandler {

    private final static String FRAGMENT_TAG = "ResultManager_143";
    private final static int REQUEST_CODE = 0x3223;

    private Activity mActivity;
    private DangerousPermissionHandler mDangerousPermissionHandler;
    private OverlayPermissionHandler mOverlayPermissionHandler;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    PermissionHandlerImpl(@NonNull final FragmentActivity fragmentActivity) {
        mActivity = fragmentActivity;

        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment mResultFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (!(mResultFragment instanceof ResultV4Fragment)) {
            mResultFragment = new ResultV4Fragment();
            fragmentManager.beginTransaction()
                    .add(mResultFragment, FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        mDangerousPermissionHandler = (DangerousPermissionHandler) mResultFragment;
        mOverlayPermissionHandler = (OverlayPermissionHandler) mResultFragment;
    }

    PermissionHandlerImpl(@NonNull final Activity activity) {
        mActivity = activity;

        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        android.app.Fragment mResultFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (!(mResultFragment instanceof ResultFragment)) {
            mResultFragment = new ResultFragment();
            fragmentManager.beginTransaction()
                    .add(mResultFragment, FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        mDangerousPermissionHandler = (DangerousPermissionHandler) mResultFragment;
        mOverlayPermissionHandler = (OverlayPermissionHandler) mResultFragment;
    }

    /**
     * 判断是否有权限
     */
    @Override
    public boolean hasPermission(@NonNull String... permissions) {
        return PermissionUtil.hasPermission(mActivity, permissions);
    }

    /**
     * 判断是否给提示
     */
    @Override
    public boolean shouldShowRationale(@NonNull String permission) {
        return PermissionUtil.shouldShowRationale(mActivity, permission);
    }

    @Override
    public void requestPermissions(@NonNull final String[] permissions, final int requestCode) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDangerousPermissionHandler.requestPermissions(permissions, requestCode);
            }
        });
    }

    @Override
    public void requestAlertWindowPermission(final int requestCode) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mOverlayPermissionHandler.requestAlertWindowPermission(requestCode);
            }
        });
    }

    @Override
    public void setOnPermissionResultCallback(OnPermissionResultCallback onPermissionResultCallback) {
        mDangerousPermissionHandler.setOnPermissionResultCallback(onPermissionResultCallback);
    }

    @Override
    public void setActivityResultCallback(OnActivityResultCallback onActivityResultCallback) {
        mOverlayPermissionHandler.setActivityResultCallback(onActivityResultCallback);
    }
}
