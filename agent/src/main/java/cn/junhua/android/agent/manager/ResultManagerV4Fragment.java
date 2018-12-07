package cn.junhua.android.agent.manager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * 用于处理Agent权限请求相关操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:28
 */
public class ResultManagerV4Fragment extends Fragment implements PermissionAction {

    private static final String TAG = "ResultManagerV4Fragment";
    private LifecycleListener lifecycle;

    public void setLifecycle(LifecycleListener lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (lifecycle != null) {
            lifecycle.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
