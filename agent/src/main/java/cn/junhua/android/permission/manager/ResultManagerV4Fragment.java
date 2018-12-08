package cn.junhua.android.permission.manager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * 用于处理Agent权限请求相关操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:28
 */
public class ResultManagerV4Fragment extends Fragment implements PermissionHandler {

    private ResultCallback mResultCallback;

    public void setResultCallback(ResultCallback mResultCallback) {
        this.mResultCallback = mResultCallback;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mResultCallback != null) {
            mResultCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
