package cn.junhua.android.permission.rom;

import cn.junhua.android.permission.agent.PermissionHandler;

/**
 * 页面适配对象
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 9:58
 */
public interface PageLauncher {
    boolean launch(PermissionHandler permissionHandler, int requestCode);
}
