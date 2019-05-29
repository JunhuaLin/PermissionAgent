package cn.junhua.android.permission.impl;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.PermissionHandlerFactory;
import cn.junhua.android.permission.impl.fragment.ResultFragment;
import cn.junhua.android.permission.impl.fragment.ResultV4Fragment;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 12:38
 */
public class PermissionHandlerFactoryImp implements PermissionHandlerFactory {

    private ActivityHolder mActivityHolder;

    public PermissionHandlerFactoryImp(Application application) {
        mActivityHolder = new ActivityHolder(application);
    }

    @Override
    public PermissionHandler create() {
        Activity activity = mActivityHolder.getActivity();
        if (activity instanceof FragmentActivity) {
            return genHandler((FragmentActivity) activity);
        }
        return genHandler(activity);
    }

    private PermissionHandler genHandler(@NonNull FragmentActivity fragmentActivity) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment mResultFragment = fragmentManager.findFragmentByTag(ResultV4Fragment.FRAGMENT_TAG);
        if (!(mResultFragment instanceof ResultV4Fragment)) {
            mResultFragment = new ResultV4Fragment();
            fragmentManager.beginTransaction()
                    .add(mResultFragment, ResultV4Fragment.FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        return (PermissionHandler) mResultFragment;
    }


    private PermissionHandler genHandler(@NonNull Activity activity) {
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        android.app.Fragment mResultFragment = fragmentManager.findFragmentByTag(ResultFragment.FRAGMENT_TAG);
        if (!(mResultFragment instanceof ResultFragment)) {
            mResultFragment = new ResultFragment();
            fragmentManager.beginTransaction()
                    .add(mResultFragment, ResultFragment.FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        return (PermissionHandler) mResultFragment;
    }
}
