package cn.junhua.android.permission.rom;

import cn.junhua.android.permission.agent.PermissionHandler;

/**
 * 执行对应rom权限请求操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/6 10:37
 */
public interface OnRomAction {
    void onAction(PermissionHandler permissionHandler, int requestCode);
}
