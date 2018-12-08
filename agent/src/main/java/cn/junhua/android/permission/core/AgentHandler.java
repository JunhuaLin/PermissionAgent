package cn.junhua.android.permission.core;

/**
 * 申请权限相关操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 17:01
 */
public interface AgentHandler {

    /**
     * 当用户同意权限时候的操作
     */
    AgentHandler onGranted(Action onGrantedAction);

    /**
     * 当用户拒绝权限时候的操作
     */
    AgentHandler onDenied(Action onDeniedAction);

    /**
     * <p>
     * 仅在用户已拒绝某项权限请求时提供解释。
     * 如果用户继续尝试使用需要某项权限的功能，但继续拒绝权限请求，
     * 则可能表明用户不理解应用为什么需要此权限才能提供相关功能。
     * 对于这种情况，比较好的做法是显示解释。
     * </p>
     * 当shouldShowRequestPermissionRationale()返回true是起作用
     */
    AgentHandler onRationale(Rationale rationale);


    /**
     * 执行请求操作
     */
    void apply();
}
