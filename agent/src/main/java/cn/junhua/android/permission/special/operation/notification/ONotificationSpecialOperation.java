package cn.junhua.android.permission.special.operation.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.special.operation.BaseOverlaySpecialOperation;
import cn.junhua.android.permission.utils.ExceptionFlat;

/**
 * 推送通知权限操作,api>=26
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class ONotificationSpecialOperation extends BaseOverlaySpecialOperation {

    @Override
    public void startActivityForResult(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();
        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

    @Override
    public boolean checkPermission(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager.areNotificationsEnabled();
    }
}
