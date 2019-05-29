package cn.junhua.android.permission.special;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.callback.OnActivityResultCallback;
import cn.junhua.android.permission.impl.BaseAgent;

import static android.app.Activity.RESULT_OK;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/19 13:39
 */
public class SpecialPermissionAgent extends BaseAgent implements OnActivityResultCallback {

    private PermissionHandler mPermissionHandler;
    private SpecialPermission mSpecialPermission;
    private String[] mPermission;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public SpecialPermissionAgent(PermissionHandler permissionHandler, SpecialPermission specialPermission) {
        mPermissionHandler = permissionHandler;
        mSpecialPermission = specialPermission;
        mPermissionHandler.setActivityResultCallback(this);
        mPermission = new String[]{mSpecialPermission.getPermission()};
    }

    private boolean checkPermission() {
        return mSpecialPermission.checkPermission(mPermissionHandler.getActivity());
    }

    @Override
    public void apply() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (checkPermission()) {
                    dispatchGranted(mPermission);
                    return;
                }
                Intent intent = mSpecialPermission.getIntent(mPermissionHandler.getActivity());
                mPermissionHandler.startActivityForResult(intent, mRequestCode);
            }
        });
    }

    @Override
    public void onActivityResultCallback(final int requestCode, final int resultCode, Intent data) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mRequestCode == requestCode) {
                    if (resultCode == RESULT_OK && checkPermission()) {
                        dispatchGranted(mPermission);
                    } else {
                        dispatchDenied(mPermission);
                    }
                }
            }
        });
    }
}
