package cn.junhua.android.agent;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.agent.manager.LifecycleListener;
import cn.junhua.android.agent.manager.PermissionAction;
import cn.junhua.android.agent.manager.PermissionAgentCreator;
import cn.junhua.android.agent.manager.PermissionAgentRepeater;
import cn.junhua.android.agent.manager.ResultManagerFragment;
import cn.junhua.android.agent.manager.ResultManagerV4Fragment;
import cn.junhua.android.agent.utils.PermissionUtil;

/**
 * 处理权限请求
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 11:15
 */
public class AgentManager implements PermissionAgentCreator {
    private static String TAG = "AgentManager";
    private static String FRAGMENT_TAG = "AgentManager_143";
    private static int REQUEST_CODE = 0x3223;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Activity mActivity;

    private String[] mPermissions;
    private OnPermissionGrantedListener mOnPermissionGrantedListener;
    private OnPermissionDeniedListener mOnPermissionDeniedListener;
    private OnPermissionRationaleListener mOnPermissionRationaleListener;

    private PermissionAction mPermissionAction;

    private LifecycleListener mLifecycleListener = new LifecycleListener() {

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String per : permissions) {
                stringBuilder.append(per).append(",");
            }

            StringBuilder stringBuilder1 = new StringBuilder();
            for (int g : grantResults) {
                if (g == PackageManager.PERMISSION_GRANTED) {
                    stringBuilder1.append("PackageManager.PERMISSION_GRANTED").append(",");
                } else {
                    stringBuilder1.append("PackageManager.PERMISSION_DENIED").append(",");
                }
            }

            Log.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = [" + stringBuilder.toString() + "], grantResults = [" + stringBuilder1.toString() + "]");
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

            if (!grantedList.isEmpty() && mOnPermissionGrantedListener != null) {
                String[] grantedArr = new String[grantedList.size()];
                grantedList.toArray(grantedArr);
                mOnPermissionGrantedListener.onGranted(grantedArr);
            }

            if (!deniedList.isEmpty() && mOnPermissionDeniedListener != null) {
                String[] deniedArr = new String[deniedList.size()];
                deniedList.toArray(deniedArr);
                mOnPermissionDeniedListener.onDenied(deniedArr);
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

        ((ResultManagerV4Fragment) mResultFragment).setLifecycle(mLifecycleListener);
        mPermissionAction = (PermissionAction) mResultFragment;
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

        ((ResultManagerFragment) mResultFragment).setLifecycle(mLifecycleListener);
        mPermissionAction = (PermissionAction) mResultFragment;
    }


    @Override
    public AgentManager request(String... permissions) {
        mPermissions = permissions;
        return this;
    }

    public AgentManager onGranted(OnPermissionGrantedListener onPermissionGrantedListener) {
        this.mOnPermissionGrantedListener = onPermissionGrantedListener;
        return this;
    }

    public AgentManager onDenied(OnPermissionDeniedListener onPermissionDeniedListener) {
        this.mOnPermissionDeniedListener = onPermissionDeniedListener;
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
    public AgentManager onShouldShowRationale(OnPermissionRationaleListener onPermissionRationaleListener) {
        this.mOnPermissionRationaleListener = onPermissionRationaleListener;
        return this;
    }


    /**
     * 执行请求操作
     */
    public void apply() {
        if (PermissionUtil.hasPermission(mActivity, mPermissions)) {
            if (mOnPermissionGrantedListener != null) {
                mOnPermissionGrantedListener.onGranted(mPermissions);
            }
            return;
        }

        //直接请求权限
        if (mOnPermissionRationaleListener == null) {
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
            mOnPermissionRationaleListener.onShowRationale(rationaleArr, new PermissionAgentRepeater() {
                @Override
                public void repeat() {
                    requestPermissions();
                }
            });
        }
    }

    private void requestPermissions() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mPermissionAction.requestPermissions(mPermissions, REQUEST_CODE);
            }
        });
    }
}
