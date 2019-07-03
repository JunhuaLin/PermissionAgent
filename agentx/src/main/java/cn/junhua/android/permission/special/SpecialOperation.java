package cn.junhua.android.permission.special;

import android.content.Context;

import cn.junhua.android.permission.agent.PermissionHandler;

/**
 * 特殊权限操作接口，方便后续扩展
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 10:35
 */
public interface SpecialOperation {

    void startActivityForResult(PermissionHandler permissionHandler, int requestCode);

    boolean checkPermission(Context context);

}
