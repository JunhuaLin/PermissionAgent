package cn.junhua.android.permission.dangerous;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.junhua.android.permission.agent.AgentExecutor;
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
 * @author junhua.lin<br />
 * CREATED 2018/12/19 13:39
 */
public class DangerousPermissionAgent extends BaseAgent<List<String>> implements OnPermissionResultCallback {
    private static final String TAG = DangerousPermissionAgent.class.getSimpleName();

    private static final PermissionChecker DOUBLE_CHECKER = new DoublePermissionChecker();
    private static final PermissionChecker STANDARD_CHECKER = new StandardPermissionChecker();

    private List<String> mPermissions;

    public DangerousPermissionAgent(Executor executor, PermissionHandler permissionHandler, String[] permissions) {
        super(executor, permissionHandler);
        mPermissions = onInitPermissions(permissions);
        mPermissionHandler.setOnPermissionResultCallback(this);
    }

    public List<String> onInitPermissions(String[] permissions) {
        //处理权限组
        return Permission.handleGroup(permissions);
    }

    public List<String> getPermissions() {
        return mPermissions;
    }

    /**
     * 执行请求操作
     */
    public void apply() {
        mExecutor.post(new Runnable() {
            @Override
            public void run() {
                Context context = mPermissionHandler.getContext();
                if (STANDARD_CHECKER.hasPermissions(context, getPermissions())) {
                    dispatchGranted(getPermissions());
                    return;
                }

                //给用户提示再请求权限
                final List<String> rationaleList = new ArrayList<>(1);
                final List<String> grantedList = new ArrayList<>(1);
                for (String permission : getPermissions()) {
                    if (mPermissionHandler.shouldShowRationale(permission)) {
                        rationaleList.add(permission);
                    } else if (STANDARD_CHECKER.hasPermissions(context, permission)) {
                        grantedList.add(permission);
                    }
                }
                if (rationaleList.isEmpty()) {
                    requestPermission(getPermissions());
                } else {
                    AgentLog.d(TAG, "dispatchRationale() called with: rationaleList = [" + rationaleList + "]");
                    dispatchRationale(rationaleList, new AgentExecutor() {
                        @Override
                        public void execute() {
                            //直接请求所有的请求
                            requestPermission(getPermissions());
                        }

                        @Override
                        public void cancel() {
                            //处理拒绝，不继续下面的请求，直接执行已授予的权限和拒绝(或未授予)的权限回调
                            List<String> tempPermissions = new ArrayList<>(getPermissions());
                            tempPermissions.removeAll(grantedList);

                            if (!tempPermissions.isEmpty()) {
                                dispatchDenied(tempPermissions);
                            }

                            if (!grantedList.isEmpty()) {
                                dispatchGranted(grantedList);
                            }
                        }
                    });
                }
            }
        }, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        AgentLog.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = " + Arrays.toString(permissions) + ", grantResults = " + Arrays.toString(grantResults));
        if (requestCode != mRequestCode || grantResults.length <= 0) return;

        mExecutor.asyncPost(new Runnable() {
            @Override
            public void run() {
                Context context = mPermissionHandler.getContext();
                List<String> grantedList = new ArrayList<>(1);
                //添加用户提示时候拒绝的权限
                List<String> deniedList = new ArrayList<>(1);

                for (String permission : permissions) {
                    if (DOUBLE_CHECKER.hasPermissions(context, permission)) {
                        grantedList.add(permission);
                    } else {
                        deniedList.add(permission);
                    }
                }

                AgentLog.d(TAG, "strict check -->onRequestPermissionsResult() called with:  permissions = "
                        + mPermissions + ", grantedList = "
                        + grantedList + ", deniedList = " + deniedList);

                if (!deniedList.isEmpty()) {
                    dispatchDenied(deniedList);
                }

                if (!grantedList.isEmpty()) {
                    dispatchGranted(grantedList);
                }
            }
        });
    }

    private void requestPermission(final List<String> permissions) {
        mExecutor.post(new Runnable() {
            @Override
            public void run() {
                mPermissionHandler.requestPermissions(permissions.toArray(new String[0]), mRequestCode);
            }
        });
    }
}
