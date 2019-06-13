package cn.junhua.android.permission.special;

import android.content.Intent;

import cn.junhua.android.permission.agent.AgentExecutor;
import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.callback.OnActivityResultCallback;
import cn.junhua.android.permission.impl.BaseAgent;
import cn.junhua.android.permission.utils.AgentLog;
import cn.junhua.android.permission.utils.Executor;

/**
 * 特殊权限申请
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/19 13:39
 */
public class SpecialPermissionAgent extends BaseAgent<SpecialPermission> implements OnActivityResultCallback, AgentExecutor {
    private static final String TAG = SpecialPermissionAgent.class.getSimpleName();

    private PermissionHandler mPermissionHandler;
    private SpecialPermission mSpecialPermission;

    public SpecialPermissionAgent(Executor executor, PermissionHandler permissionHandler, SpecialPermission specialPermission) {
        super(executor);
        mPermissionHandler = permissionHandler;
        mSpecialPermission = specialPermission;
        mPermissionHandler.setActivityResultCallback(this);
    }

    private boolean checkPermission() {
        return mSpecialPermission.checkPermission(mPermissionHandler.getContext());
    }

    @Override
    public void apply() {
        mExecutor.post(new Runnable() {
            @Override
            public void run() {
                if (checkPermission()) {
                    dispatchGranted(mSpecialPermission);
                    return;
                }

                //特殊权限默认提前提示用户
                dispatchRationale(mSpecialPermission, SpecialPermissionAgent.this);
            }
        });
    }

    @Override
    public void onActivityResultCallback(final int requestCode, final int resultCode, Intent data) {
        AgentLog.d(TAG, "onActivityResultCallback() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        if (mRequestCode != requestCode) {
            return;
        }

        mExecutor.post(new Runnable() {
            @Override
            public void run() {
                if (checkPermission()) {
                    dispatchGranted(mSpecialPermission);
                } else {
                    dispatchDenied(mSpecialPermission);
                }
            }
        });
    }

    @Override
    public void execute() {
        mExecutor.post(new Runnable() {
            @Override
            public void run() {
                mSpecialPermission.startActivityForResult(mPermissionHandler, mRequestCode);
            }
        });
    }

    @Override
    public void cancel() {
        dispatchDenied(mSpecialPermission);
    }
}
