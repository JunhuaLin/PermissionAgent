package cn.junhua.android.permission.special;

import android.content.Context;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.special.operation.DefaultSpecialOperation;
import cn.junhua.android.permission.special.operation.install.InstallSpecialOperationFactory;
import cn.junhua.android.permission.special.operation.overlay.OverlaySpecialOperationFactory;
import cn.junhua.android.permission.special.operation.settings.SettingsSpecialOperationFactory;

/**
 * 枚举特殊权限
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 10:00
 */
public enum SpecialPermission implements SpecialOperation {
    /**
     * 安装未知apk权限
     */
    REQUEST_INSTALL_PACKAGES(new InstallSpecialOperationFactory()),
    /**
     * 修改设置权限
     */
    WRITE_SETTINGS(new SettingsSpecialOperationFactory()),
    /**
     * 系统窗口权限
     */
    SYSTEM_ALERT_WINDOW(new OverlaySpecialOperationFactory());

    private SpecialOperation mOperation;

    SpecialPermission(SpecialOperationFactory operationFactory) {
        mOperation = operationFactory.create();

        if (mOperation == null) {
            mOperation = new DefaultSpecialOperation();
        }
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
