package cn.junhua.android.permission;

import android.app.Activity;
import android.app.Application;

import java.util.List;

import cn.junhua.android.permission.agent.Agent;
import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.agent.PermissionHandlerFactory;
import cn.junhua.android.permission.agent.check.PermissionChecker;
import cn.junhua.android.permission.dangerous.DangerousPermissionAgent;
import cn.junhua.android.permission.dangerous.EachDangerousPermissionAgent;
import cn.junhua.android.permission.dangerous.checker.DoublePermissionChecker;
import cn.junhua.android.permission.dangerous.checker.Permission;
import cn.junhua.android.permission.impl.ActivityHolder;
import cn.junhua.android.permission.impl.PermissionHandlerFactoryImp;
import cn.junhua.android.permission.impl.SettingPage;
import cn.junhua.android.permission.special.SpecialPermission;
import cn.junhua.android.permission.special.SpecialPermissionAgent;
import cn.junhua.android.permission.utils.AgentLog;
import cn.junhua.android.permission.utils.Executor;


/**
 * 权限申请代理类
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:44
 */
public class PermissionAgent {

    private PermissionHandlerFactory mPermissionHandlerFactory;
    private ActivityHolder mActivityHolder;
    private Executor mExecutor;
    private PermissionChecker mDoubleChecker;

    private PermissionAgent() {
        mExecutor = new Executor();
        mDoubleChecker = new DoublePermissionChecker();
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
     * 并行申请危险(Dangerous)权限
     *
     * @param permissions 权限列表
     * @return Agent危险权限申请操作
     */
    public Agent<List<String>> request(String... permissions) {
        return new DangerousPermissionAgent(mExecutor, getPermissionHandler(), permissions);
    }

    /**
     * 并行申请危险(Dangerous)权限
     *
     * @param permissions 权限列表
     * @return Agent危险权限申请操作
     */
    public Agent<List<String>> request(List<String> permissions) {
        return request(permissions.toArray(new String[0]));
    }

    /**
     * 串行申请危险(Dangerous)权限
     *
     * @param permissions 权限列表
     * @return Agent危险权限申请操作
     */
    public Agent<List<String>> requestEach(String... permissions) {
        return new EachDangerousPermissionAgent(mExecutor, getPermissionHandler(), permissions);
    }

    /**
     * 串行申请危险(Dangerous)权限
     *
     * @param permissions 权限列表
     * @return Agent危险权限申请操作
     */
    public Agent<List<String>> requestEach(List<String> permissions) {
        return requestEach(permissions.toArray(new String[0]));
    }

    /**
     * 特殊(Special)权限
     *
     * @param permission 特殊权限枚举
     * @return Agent特殊权限申请操作
     */
    public Agent<SpecialPermission> request(SpecialPermission permission) {
        return new SpecialPermissionAgent(mExecutor, getPermissionHandler(), permission);
    }

    /**
     * 检测危险权限
     *
     * @param permissions {@link android.Manifest} 权限列表
     * @return 如果存在权限没有授予就返回false
     */
    public boolean checkPermission(String... permissions) {
        return mDoubleChecker.hasPermissions(getCurrentActivity(), Permission.handleGroup(permissions));
    }

    /**
     * 检测危险权限
     *
     * @param permissions {@link android.Manifest} 权限列表
     * @return 如果存在权限没有授予就返回false
     */
    public boolean checkPermission(List<String> permissions) {
        return checkPermission(permissions.toArray(new String[0]));
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

    /**
     * 当权限被拒绝时候调用此方法，检测权限是否永远被拒绝
     *
     * @param deniedPermissions 拒绝的权限列表
     * @return true 永远拒绝.
     */
    public boolean hasAlwaysDeniedPermission(String... deniedPermissions) {
        PermissionHandler permissionHandler = getPermissionHandler();
        for (String permission : deniedPermissions) {
            if (!permissionHandler.shouldShowRationale(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 当权限被拒绝时候调用此方法，检测权限是否永远被拒绝
     *
     * @param deniedPermissions 拒绝的权限列表
     * @return true 永远拒绝.
     */
    public boolean hasAlwaysDeniedPermission(List<String> deniedPermissions) {
        return hasAlwaysDeniedPermission(deniedPermissions.toArray(new String[0]));
    }

    /**
     * 启动设置页面
     *
     * @param requestCode 请求code
     */
    public void startSettingPage(int requestCode) {
        new SettingPage().start(getPermissionHandler(), requestCode);
    }

    /**
     * 当前栈顶activity
     *
     * @return Activity
     */
    public Activity getCurrentActivity() {
        return mActivityHolder.getCurrentActivity();
    }

    private PermissionHandler getPermissionHandler() {
        return mPermissionHandlerFactory.create();
    }

    private static class InstanceHolder {
        private static final PermissionAgent INSTANCE = new PermissionAgent();
    }

}
