package cn.junhua.android.permission;

import android.app.Application;

import cn.junhua.android.permission.agent.Agent;
import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.PermissionHandlerFactory;
import cn.junhua.android.permission.dangerous.DangerousPermissionAgent;
import cn.junhua.android.permission.impl.PermissionHandlerFactoryImp;
import cn.junhua.android.permission.special.SpecialPermission;
import cn.junhua.android.permission.special.SpecialPermissionAgent;
import cn.junhua.android.permission.utils.AgentLog;


/**
 * 权限申请代理类
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:44
 */
public class PermissionAgent {

    private PermissionHandlerFactory mPermissionHandlerFactory;

    private PermissionAgent() {
    }

    public static PermissionAgent getInstance() {
        return PermissionAgent.InstanceHolder.INSTANCE;
    }

    public static void setDebug(boolean debug) {
        AgentLog.setDebug(debug);
    }

    public void init(Application application) {
        if (mPermissionHandlerFactory == null) {
            mPermissionHandlerFactory = new PermissionHandlerFactoryImp(application);
        }
    }

    /**
     * 危险(Dangerous)权限
     *
     * @param permissions 权限列表
     * @return Agent权限申请操作
     */
    public Agent request(String... permissions) {
        return new DangerousPermissionAgent(getPermissionHandler(), permissions);
    }

    /**
     * 特殊权限
     *
     * @param permission 特殊权限枚举
     * @return Agent权限申请操作
     */
    public Agent request(SpecialPermission permission) {
        return new SpecialPermissionAgent(getPermissionHandler(), permission);
    }

    private PermissionHandler getPermissionHandler() {
        return mPermissionHandlerFactory.create();
    }

    private static class InstanceHolder {
        private static final PermissionAgent INSTANCE = new PermissionAgent();
    }

}
