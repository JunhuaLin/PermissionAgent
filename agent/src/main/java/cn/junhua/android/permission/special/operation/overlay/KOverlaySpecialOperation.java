package cn.junhua.android.permission.special.operation.overlay;

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
 * 浮窗权限操作 19<=api<23
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class KOverlaySpecialOperation extends BaseOverlaySpecialOperation {

    @Override
    public boolean checkPermission(Context context) {
        return PermissionUtil.checkOpNoThrow(context, Const.OP_SYSTEM_ALERT_WINDOW);
    }

    @Override
    public void startActivityForResult(PermissionHandler permissionHandler, int requestCode) {
        if (RomUtils.checkIsMiuiRom()) {
            miui(permissionHandler, requestCode);
        } else if (RomUtils.checkIsMeizuRom()) {
            meizu(permissionHandler, requestCode);
        } else if (RomUtils.checkIsHuaweiRom()) {
            huawei(permissionHandler, requestCode);
        } else if (RomUtils.checkIs360Rom()) {
            qihu360(permissionHandler, requestCode);
        } else if (RomUtils.checkIsOppoRom()) {
            oppo(permissionHandler, requestCode);
        } else if (RomUtils.checkIsVivoRom()) {
            vivo(permissionHandler, requestCode);
        } else {
            defaultRom(permissionHandler, requestCode);
        }
    }

    private void defaultRom(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();

        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        permissionHandler.startActivityForResult(appDetailsIntent(context), requestCode);
                    }
                });
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
                        intent.setClassName("com.huawei.systemmanager",
                                "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setClassName("com.Android.settings",
                                "com.android.settings.permission.TabItem");
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

    private void meizu(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();

        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setAction("com.meizu.safe.security.SHOW_APPSEC");
                        intent.putExtra("packageName", context.getPackageName());
                        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");//remove this line code for fix flyme6.3
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setAction("com.meizu.safe.security.SHOW_APPSEC");
                        intent.putExtra("packageName", context.getPackageName());
//                        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");//remove this line code for fix flyme6.3
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

    private void miui(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();
        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                        intent.putExtra("extra_pkgname", context.getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                        intent.putExtra("extra_pkgname", context.getPackageName());
                        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                        intent.putExtra("extra_pkgname", context.getPackageName());
                        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                        intent.setPackage("com.miui.securitycenter");
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

    private void oppo(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();
        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.putExtra("packageName", context.getPackageName());
                        intent.setClassName("com.coloros.safecenter",
                                "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.putExtra("packageName", context.getPackageName());
                        intent.setClassName("com.color.safecenter",
                                "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.putExtra("packageName", context.getPackageName());
                        intent.setClassName("com.oppo.safe",
                                "com.oppo.safe.permission.PermissionAppListActivity");
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

    private void vivo(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();
        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setClassName("com.iqoo.secure",
                                "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager");
                        intent.putExtra("packagename", context.getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setClassName("com.iqoo.secure",
                                "com.iqoo.secure.safeguard.SoftPermissionDetailActivity");
                        intent.putExtra("packagename", context.getPackageName());
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

    private void qihu360(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();
        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setClassName("com.android.settings",
                                "com.android.settings.Settings$OverlaySettingsActivity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() {
                        Intent intent = new Intent();
                        intent.setClassName("com.qihoo360.mobilesafe",
                                "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
                        intent.putExtra("packagename", context.getPackageName());
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
