package cn.junhua.android.permission.rom.huawei.overlay;

import android.content.Context;
import android.content.Intent;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 11:05
 */
public class KOverlayPageLauncher implements PageLauncher {
    @Override
    public boolean launch(PermissionHandler permissionHandler, int requestCode) {
        return ActivitiesFlat.create(permissionHandler, requestCode)
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
                .start();
    }
}
