package cn.junhua.android.permission.special.operation.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.special.operation.RomOverlaySpecialOperation;
import cn.junhua.android.permission.utils.ActivitiesFlat;
import cn.junhua.android.permission.utils.Const;
import cn.junhua.android.permission.special.rom.Rom;
import cn.junhua.android.permission.special.rom.OnRomAction;

/**
 * 推送通知权限操作,api>=19
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
class KNotificationSpecialOperation extends RomOverlaySpecialOperation {

    KNotificationSpecialOperation() {
        super(Const.OP_POST_NOTIFICATION);

        addMatchRom(Rom.Huawei, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setClassName("com.huawei.systemmanager",
                                        "com.huawei.notificationmanager.ui.NotificationManagmentActivity");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setClassName("com.huawei.systemmanager",
                                        "com.huawei.permissionmanager.ui.MainActivity");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("app_package", context.getPackageName());
                                intent.putExtra("app_uid", context.getApplicationInfo().uid);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(getAppDetailsIntentAction())
                        .start();
            }
        });

        addDefaultMatchRom(new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("app_package", context.getPackageName());
                                intent.putExtra("app_uid", context.getApplicationInfo().uid);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(getAppDetailsIntentAction())
                        .start();
            }
        });

    }

}
