package cn.junhua.android.permission.impl;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import cn.junhua.android.permission.core.AgentCreator;
import cn.junhua.android.permission.core.Agent;
import cn.junhua.android.permission.core.DangerousPermissionHandler;
import cn.junhua.android.permission.core.OverlayPermissionHandler;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/19 13:43
 */
public class AgentCreatorImpl implements AgentCreator {

    private DangerousPermissionHandler mDangerousPermissionHandler;
    private OverlayPermissionHandler mOverlayPermissionHandler;

    public AgentCreatorImpl(@NonNull FragmentActivity fragmentActivity) {
        PermissionHandlerImpl permissionHandler = new PermissionHandlerImpl(fragmentActivity);
        mDangerousPermissionHandler = permissionHandler;
        mOverlayPermissionHandler = permissionHandler;
    }

    public AgentCreatorImpl(@NonNull Activity activity) {
        PermissionHandlerImpl permissionHandler = new PermissionHandlerImpl(activity);
        mDangerousPermissionHandler = permissionHandler;
        mOverlayPermissionHandler = permissionHandler;
    }


    @Override
    public Agent request(String... permissions) {
        return new DangerousPermissionAgent(mDangerousPermissionHandler, permissions);
    }

    @Override
    public Agent overlay() {
        return new OverlayPermissionAgent(mOverlayPermissionHandler);
    }
}
