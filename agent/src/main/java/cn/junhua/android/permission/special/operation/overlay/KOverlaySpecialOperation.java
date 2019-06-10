package cn.junhua.android.permission.special.operation.overlay;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.special.rom.Rom;
import cn.junhua.android.permission.special.rom.OnRomAction;
import cn.junhua.android.permission.special.operation.RomOverlaySpecialOperation;
import cn.junhua.android.permission.utils.ActivitiesFlat;
import cn.junhua.android.permission.utils.Const;

/**
 * 浮窗权限操作 19<=api<23
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
class KOverlaySpecialOperation extends RomOverlaySpecialOperation {

    KOverlaySpecialOperation() {
        super(Const.OP_SYSTEM_ALERT_WINDOW);

        addMatchRom(Rom.Xiaomi, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                                intent.putExtra("extra_pkgname", context.getPackageName());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })

                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                                intent.putExtra("extra_pkgname", context.getPackageName());
                                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                                intent.putExtra("extra_pkgname", context.getPackageName());
                                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                                intent.setPackage("com.miui.securitycenter");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(getAppDetailsIntentAction())
                        .start();
            }
        });

        addMatchRom(Rom.Meizu, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("com.meizu.safe.security.SHOW_APPSEC");
                                intent.putExtra("packageName", context.getPackageName());
                                intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");//remove this line code for fix flyme6.3
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })

                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("com.meizu.safe.security.SHOW_APPSEC");
                                intent.putExtra("packageName", context.getPackageName());
//                        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");//remove this line code for fix flyme6.3
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(getAppDetailsIntentAction())
                        .start();
            }
        });

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
                                intent.setClassName("com.huawei.systemmanager",
                                        "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setClassName("com.Android.settings",
                                        "com.android.settings.permission.TabItem");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(getAppDetailsIntentAction())
                        .start();
            }
        });

        addMatchRom(Rom.Qihu360, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setClassName("com.android.settings",
                                        "com.android.settings.Settings$OverlaySettingsActivity");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setClassName("com.qihoo360.mobilesafe",
                                        "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
                                intent.putExtra("packagename", context.getPackageName());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(getAppDetailsIntentAction())
                        .start();
            }
        });

        addMatchRom(Rom.Oppo, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.putExtra("packageName", context.getPackageName());
                                intent.setClassName("com.coloros.safecenter",
                                        "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.putExtra("packageName", context.getPackageName());
                                intent.setClassName("com.color.safecenter",
                                        "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.putExtra("packageName", context.getPackageName());
                                intent.setClassName("com.oppo.safe",
                                        "com.oppo.safe.permission.PermissionAppListActivity");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(getAppDetailsIntentAction())
                        .start();
            }
        });

        addMatchRom(Rom.Vivo, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setClassName("com.iqoo.secure",
                                        "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager");
                                intent.putExtra("packagename", context.getPackageName());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setClassName("com.iqoo.secure",
                                        "com.iqoo.secure.safeguard.SoftPermissionDetailActivity");
                                intent.putExtra("packagename", context.getPackageName());
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
                        .addAction(getAppDetailsIntentAction())
                        .start();
            }
        });
    }
}
