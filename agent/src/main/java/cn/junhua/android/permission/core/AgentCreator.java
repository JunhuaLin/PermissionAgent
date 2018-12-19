package cn.junhua.android.permission.core;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 17:01
 */
public interface AgentCreator {
    /**
     * 危险(Dangerous)权限
     *
     * @param permissions 权限列表
     * @return 权限申请操作
     */
    Agent request(String... permissions);

    /**
     * 特殊权限Settings.ACTION_MANAGE_OVERLAY_PERMISSION
     */
    Agent overlay();
}
