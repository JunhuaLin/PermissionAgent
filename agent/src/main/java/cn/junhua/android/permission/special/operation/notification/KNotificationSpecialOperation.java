package cn.junhua.android.permission.special.operation.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.special.operation.BaseOverlaySpecialOperation;
import cn.junhua.android.permission.utils.Const;
import cn.junhua.android.permission.utils.ExceptionFlat;
import cn.junhua.android.permission.utils.PermissionUtil;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * 推送通知权限操作,api>=19
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class KNotificationSpecialOperation extends BaseOverlaySpecialOperation {

    @Override
    public void startActivityForResult(final PermissionHandler permissionHandler, final int requestCode) {
        if (RomUtils.checkIsHuaweiRom()) {
            huawei(permissionHandler, requestCode);
        } else {
            defaultRom(permissionHandler, requestCode);
        }
    }


    @Override
    public boolean checkPermission(Context context) {
        return PermissionUtil.checkOpNoThrow(context, Const.OP_POST_NOTIFICATION);
    }

    private void huawei(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();

        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setClassName("com.huawei.systemmanager",
                                "com.huawei.notificationmanager.ui.NotificationManagmentActivity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setClassName("com.huawei.systemmanager",
                                "com.huawei.permissionmanager.ui.MainActivity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                        intent.putExtra("app_package", context.getPackageName());
                        intent.putExtra("app_uid", context.getApplicationInfo().uid);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        permissionHandler.startActivityForResult(appDetailsIntent(context), requestCode);
                    }
                });
    }

    private void defaultRom(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();
        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                        intent.putExtra("app_package", context.getPackageName());
                        intent.putExtra("app_uid", context.getApplicationInfo().uid);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        permissionHandler.startActivityForResult(appDetailsIntent(context), requestCode);
                    }
                });
    }
}
