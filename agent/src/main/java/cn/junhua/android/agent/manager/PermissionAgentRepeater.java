package cn.junhua.android.agent.manager;

/**
 * 用于继续权限申请操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 17:20
 */
public interface PermissionAgentRepeater {
    /**
     * 处理后续权限请求
     */
    void repeat();
}
