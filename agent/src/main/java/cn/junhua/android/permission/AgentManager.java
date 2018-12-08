package cn.junhua.android.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.permission.core.Action;
import cn.junhua.android.permission.core.Rationale;
import cn.junhua.android.permission.core.AgentCreator;
import cn.junhua.android.permission.core.AgentExecutor;
import cn.junhua.android.permission.core.AgentHandler;
import cn.junhua.android.permission.manager.PermissionHandler;
import cn.junhua.android.permission.manager.ResultCallback;
import cn.junhua.android.permission.manager.ResultManagerFragment;
import cn.junhua.android.permission.manager.ResultManagerV4Fragment;
import cn.junhua.android.permission.utils.PermissionUtil;

/**
 * 处理权限请求
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 11:15
 */
class AgentManager implements AgentCreator, AgentHandler {
    private static String FRAGMENT_TAG = "AgentManager_143";
    private static int REQUEST_CODE = 0x3223;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Activity mActivity;

    private String[] mPermissions;
    private Action mOnGrantedAction;
    private Action mOnDeniedAction;
    private Rationale mRationale;

    private PermissionHandler mPermissionHandler;

    private ResultCallback mResultCallback = new ResultCallback() {

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if (requestCode != REQUEST_CODE || grantResults.length <= 0) return;

            List<String> grantedList = new ArrayList<>();
            List<String> deniedList = new ArrayList<>();
            for (int index = 0; index < grantResults.length; index++) {
                if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    grantedList.add(permissions[index]);
                } else {
                    deniedList.add(permissions[index]);
                }
            }

            if (!grantedList.isEmpty() && mOnGrantedAction != null) {
                String[] grantedArr = new String[grantedList.size()];
                grantedList.toArray(grantedArr);
                mOnGrantedAction.onAction(grantedArr);
            }

            if (!deniedList.isEmpty() && mOnDeniedAction != null) {
                String[] deniedArr = new String[deniedList.size()];
                deniedList.toArray(deniedArr);
                mOnDeniedAction.onAction(deniedArr);
            }
        }
    };


    AgentManager(@NonNull final FragmentActivity fragmentActivity) {
        mActivity = fragmentActivity;

        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment mResultFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (!(mResultFragment instanceof ResultManagerV4Fragment)) {
            mResultFragment = new ResultManagerV4Fragment();
            fragmentManager.beginTransaction()
                    .add(mResultFragment, FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        ((ResultManagerV4Fragment) mResultFragment).setResultCallback(mResultCallback);
        mPermissionHandler = (PermissionHandler) mResultFragment;
    }

    AgentManager(@NonNull final Activity activity) {
        mActivity = activity;

        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        android.app.Fragment mResultFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (!(mResultFragment instanceof ResultManagerFragment)) {
            mResultFragment = new ResultManagerFragment();
            fragmentManager.beginTransaction()
                    .add(mResultFragment, FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        ((ResultManagerFragment) mResultFragment).setResultCallback(mResultCallback);
        mPermissionHandler = (PermissionHandler) mResultFragment;
    }


    @Override
    public AgentHandler request(String... permissions) {
        mPermissions = permissions;
        return this;
    }

    public AgentHandler onGranted(Action onGrantedAction) {
        this.mOnGrantedAction = onGrantedAction;
        return this;
    }

    public AgentHandler onDenied(Action onDeniedAction) {
        this.mOnDeniedAction = onDeniedAction;
        return this;
    }

    /**
     * <p>
     * 仅在用户已拒绝某项权限请求时提供解释。
     * 如果用户继续尝试使用需要某项权限的功能，但继续拒绝权限请求，
     * 则可能表明用户不理解应用为什么需要此权限才能提供相关功能。
     * 对于这种情况，比较好的做法是显示解释。
     * </p>
     * 当shouldShowRequestPermissionRationale()返回true是起作用
     */
    public AgentHandler onRationale(Rationale rationale) {
        this.mRationale = rationale;
        return this;
    }


    /**
     * 执行请求操作
     */
    public void apply() {
        if (PermissionUtil.hasPermission(mActivity, mPermissions)) {
            if (mOnGrantedAction != null) {
                mOnGrantedAction.onAction(mPermissions);
            }
            return;
        }

        //直接请求权限
        if (mRationale == null) {
            requestPermissions();
            return;
        }

        //给用户提示再请求权限
        List<String> rationaleList = new ArrayList<>();
        for (String permission : mPermissions) {
            if (PermissionUtil.shouldShowRationale(mActivity, permission)) {
                rationaleList.add(permission);
            }
        }
        if (rationaleList.isEmpty()) {
            requestPermissions();
        } else {
            String[] rationaleArr = new String[rationaleList.size()];
            rationaleList.toArray(rationaleArr);
            mRationale.onShowRationale(rationaleArr, new AgentExecutor() {
                @Override
                public void execute() {
                    requestPermissions();
                }
            });
        }
    }

    private void requestPermissions() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mPermissionHandler.requestPermissions(mPermissions, REQUEST_CODE);
            }
        });
    }
}
