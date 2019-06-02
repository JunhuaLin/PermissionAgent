package cn.junhua.android.permission.dangerous;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.callback.OnPermissionResultCallback;
import cn.junhua.android.permission.impl.BaseAgent;
import cn.junhua.android.permission.utils.AgentLog;
import cn.junhua.android.permission.utils.PermissionUtil;

/**
 * 危险权限申请
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/19 13:39
 */
public class DangerousPermissionAgent extends BaseAgent<List<String>> implements OnPermissionResultCallback {
    private static final String TAG = DangerousPermissionAgent.class.getSimpleName();

    private PermissionHandler mPermissionHandler;
    private String[] mPermissions;

    public DangerousPermissionAgent(PermissionHandler permissionHandler, String[] permissions) {
        mPermissionHandler = permissionHandler;
        mPermissions = permissions;
        mPermissionHandler.setOnPermissionResultCallback(this);
    }


    /**
     * 执行请求操作
     */
    public void apply() {
        post(new Runnable() {
            @Override
            public void run() {
                if (PermissionUtil.hasPermission(mPermissionHandler.getActivity(), mPermissions)) {
                    dispatchGranted(Arrays.asList(mPermissions));
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
                    execute();
                } else {
                    dispatchRationale(rationaleList, DangerousPermissionAgent.this);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        AgentLog.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = " + Arrays.toString(permissions) + ", grantResults = " + Arrays.toString(grantResults));
        if (requestCode != mRequestCode || grantResults.length <= 0) return;

        post(new Runnable() {
            @Override
            public void run() {
                List<String> grantedList = new ArrayList<>();
                List<String> deniedList = new ArrayList<>();
                for (int index = 0; index < grantResults.length; index++) {
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                        grantedList.add(permissions[index]);
                    } else {
                        deniedList.add(permissions[index]);
                    }
                }

                if (!grantedList.isEmpty()) {
                    dispatchGranted(grantedList);
                }

                if (!deniedList.isEmpty()) {
                    dispatchDenied(deniedList);
                }
            }
        });
    }

    @Override
    public void execute() {
        post(new Runnable() {
            @Override
            public void run() {
                mPermissionHandler.requestPermissions(mPermissions, mRequestCode);
            }
        });
    }

    @Override
    public void cancel() {
        dispatchDenied(Arrays.asList(mPermissions));
    }
}
