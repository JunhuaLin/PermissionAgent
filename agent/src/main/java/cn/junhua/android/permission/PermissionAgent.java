package cn.junhua.android.permission;

import android.app.Activity;
import android.app.Application;
import android.support.v4.content.PermissionChecker;

import java.util.List;

import cn.junhua.android.permission.agent.Agent;
import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.PermissionHandlerFactory;
import cn.junhua.android.permission.dangerous.DangerousPermissionAgent;
import cn.junhua.android.permission.impl.ActivityHolder;
import cn.junhua.android.permission.impl.PermissionHandlerFactoryImp;
import cn.junhua.android.permission.special.SpecialPermission;
import cn.junhua.android.permission.special.SpecialPermissionAgent;
import cn.junhua.android.permission.utils.AgentLog;
import cn.junhua.android.permission.utils.PermissionUtil;


/**
 * 权限申请代理类
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:44
 */
public class PermissionAgent {

    private PermissionHandlerFactory mPermissionHandlerFactory;
    private ActivityHolder mActivityHolder;

    private PermissionAgent() {
    }

    public static PermissionAgent getInstance() {
        return PermissionAgent.InstanceHolder.INSTANCE;
    }

    public static void setDebug(boolean debug) {
        AgentLog.setDebug(debug);
    }

    public void init(Application application) {
        if (mActivityHolder == null) {
            mActivityHolder = new ActivityHolder(application);
            mPermissionHandlerFactory = new PermissionHandlerFactoryImp(mActivityHolder);
        }
    }

    /**
     * 危险(Dangerous)权限
     *
     * @param permissions 权限列表
     * @return Agent危险权限申请操作
     */
    public Agent<List<String>> request(String... permissions) {
        return new DangerousPermissionAgent(getPermissionHandler(), permissions);
    }

    /**
     * 特殊(Special)权限
     *
     * @param permission 特殊权限枚举
     * @return Agent特殊权限申请操作
     */
    public Agent<SpecialPermission> request(SpecialPermission permission) {
        return new SpecialPermissionAgent(getPermissionHandler(), permission);
    }

    /**
     * 检测危险权限
     *
     * @param permissions {@link android.Manifest} 权限列表
     * @return 如果存在权限没有授予就返回false
     */
    public boolean checkPermission(String... permissions) {
        return PermissionUtil.hasPermissions(getCurrentActivity(), permissions);
    }

    /**
     * 检测特殊权限
     *
     * @param specialPermission {@link SpecialPermission}特殊权限枚举
     * @return 是否有该特殊权限
     */
    public boolean checkPermission(SpecialPermission specialPermission) {
        return specialPermission.checkPermission(getCurrentActivity());
    }

    private Activity getCurrentActivity() {
        return mActivityHolder.getCurrentActivity();
    }

    private PermissionHandler getPermissionHandler() {
        return mPermissionHandlerFactory.create();
    }

    private static class InstanceHolder {
        private static final PermissionAgent INSTANCE = new PermissionAgent();
    }

}
