package cn.junhua.android.permission.special;

import android.content.Context;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.special.operation.InstallSpecialOperation;
import cn.junhua.android.permission.special.operation.NotificationSpecialOperation;
import cn.junhua.android.permission.special.operation.OverlaySpecialOperation;
import cn.junhua.android.permission.special.operation.WriteSettingsSpecialOperation;

/**
 * 枚举特殊权限
 *
 * @author junhua.lin<br />
 * CREATED 2019/5/29 10:00
 */
public enum SpecialPermission implements SpecialOperation {
    /**
     * 安装未知apk权限
     */
    REQUEST_INSTALL_PACKAGES(new InstallSpecialOperation()),
    /**
     * 修改设置权限
     */
    WRITE_SETTINGS(new WriteSettingsSpecialOperation()),
    /**
     * 系统窗口权限
     */
    SYSTEM_ALERT_WINDOW(new OverlaySpecialOperation()),
    /**
     * 推送通知权限
     */
    ACCESS_NOTIFICATION_POLICY(new NotificationSpecialOperation());

    private SpecialOperation mOperation;

    SpecialPermission(SpecialOperation operation) {
        mOperation = operation;
    }

    @Override
    public void startActivityForResult(PermissionHandler permissionHandler, int requestCode) {
        mOperation.startActivityForResult(permissionHandler, requestCode);
    }

    @Override
    public boolean checkPermission(Context context) {
        return mOperation.checkPermission(context);
    }
}
