package cn.junhua.android.permission.dangerous;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.callback.OnPermissionResultCallback;
import cn.junhua.android.permission.agent.check.PermissionChecker;
import cn.junhua.android.permission.dangerous.checker.DoublePermissionChecker;
import cn.junhua.android.permission.dangerous.checker.Permission;
import cn.junhua.android.permission.dangerous.checker.StandardPermissionChecker;
import cn.junhua.android.permission.impl.BaseAgent;
import cn.junhua.android.permission.utils.AgentLog;
import cn.junhua.android.permission.utils.Executor;

/**
 * 危险权限申请
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/19 13:39
 */
public class DangerousPermissionAgent extends BaseAgent<List<String>> implements OnPermissionResultCallback {
    private static final String TAG = DangerousPermissionAgent.class.getSimpleName();

    private static final PermissionChecker DOUBLE_CHECKER = new DoublePermissionChecker();
    private static final PermissionChecker STANDARD_CHECKER = new StandardPermissionChecker();

    private PermissionHandler mPermissionHandler;
    private List<String> mPermissions;

    public DangerousPermissionAgent(Executor executor, PermissionHandler permissionHandler, String[] permissions) {
        super(executor);
        mPermissionHandler = permissionHandler;
        mPermissions = Permission.handleGroup(permissions);//处理权限组
        mPermissionHandler.setOnPermissionResultCallback(this);
    }


    /**
     * 执行请求操作
     */
    public void apply() {
        post(new Runnable() {
            @Override
            public void run() {
                if (STANDARD_CHECKER.hasPermissions(mPermissionHandler.getActivity(), mPermissions)) {
                    dispatchGranted(mPermissions);
                    return;
                }

                //给用户提示再请求权限
                List<String> rationaleList = new ArrayList<>(1);
                for (String permission : mPermissions) {
                    if (mPermissionHandler.shouldShowRationale(permission)) {
                        rationaleList.add(permission);
                    }
                }
                if (rationaleList.isEmpty()) {
                    execute();
                } else {
                    AgentLog.d(TAG, "dispatchRationale() called with: rationaleList = [" + rationaleList + "]");
                    dispatchRationale(rationaleList, DangerousPermissionAgent.this);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        AgentLog.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = " + Arrays.toString(permissions) + ", grantResults = " + Arrays.toString(grantResults));
        if (requestCode != mRequestCode || grantResults.length <= 0) return;

        asyncPost(new Runnable() {
            @Override
            public void run() {
                Context context = mPermissionHandler.getContext();
                List<String> grantedList = new ArrayList<>(1);
                List<String> deniedList = new ArrayList<>(1);

                for (String permission : mPermissions) {
                    if (DOUBLE_CHECKER.hasPermissions(context, permission)) {
                        grantedList.add(permission);
                    } else {
                        deniedList.add(permission);
                    }
                }

                AgentLog.d(TAG, "strict check -->onRequestPermissionsResult() called with:  permissions = "
                        + mPermissions + ", grantedList = "
                        + grantedList + ", deniedList = " + deniedList);

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
                mPermissionHandler.requestPermissions(mPermissions.toArray(new String[0]), mRequestCode);
            }
        });
    }

    @Override
    public void cancel() {
        dispatchDenied(mPermissions);
    }
}
