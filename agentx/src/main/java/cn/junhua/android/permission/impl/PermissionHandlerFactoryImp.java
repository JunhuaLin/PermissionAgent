package cn.junhua.android.permission.impl;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.PermissionHandlerFactory;
import cn.junhua.android.permission.impl.fragment.DefaultPermissionHandler;
import cn.junhua.android.permission.impl.fragment.SupportV4PermissionHandler;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 12:38
 */
public class PermissionHandlerFactoryImp implements PermissionHandlerFactory {

    private ActivityHolder mActivityHolder;

    public PermissionHandlerFactoryImp(ActivityHolder activityHolder) {
        mActivityHolder = activityHolder;
    }

    @Override
    public PermissionHandler create() {
        Activity activity = mActivityHolder.getCurrentActivity();
        if (activity instanceof FragmentActivity) {
            return genV4Handler((FragmentActivity) activity);
        }
        return genHandler(activity);
    }

    private PermissionHandler genV4Handler(@NonNull FragmentActivity fragmentActivity) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(SupportV4PermissionHandler.FRAGMENT_TAG);
        if (!(fragment instanceof SupportV4PermissionHandler)) {
            fragment = new SupportV4PermissionHandler();
            fragmentManager.beginTransaction()
                    .add(fragment, SupportV4PermissionHandler.FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        return (PermissionHandler) fragment;
    }


    private PermissionHandler genHandler(@NonNull Activity activity) {
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        android.app.Fragment fragment = fragmentManager.findFragmentByTag(DefaultPermissionHandler.FRAGMENT_TAG);
        if (!(fragment instanceof DefaultPermissionHandler)) {
            fragment = new DefaultPermissionHandler();
            fragmentManager.beginTransaction()
                    .add(fragment, DefaultPermissionHandler.FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        return (PermissionHandler) fragment;
    }
}
