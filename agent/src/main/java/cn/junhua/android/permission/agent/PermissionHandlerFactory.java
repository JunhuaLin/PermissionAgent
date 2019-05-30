package cn.junhua.android.permission.agent;

/**
 * PermissionHandler工厂，用于不同的Context生成不同的PermissionHandler
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 16:54
 */
public interface PermissionHandlerFactory {
    PermissionHandler create();
}
