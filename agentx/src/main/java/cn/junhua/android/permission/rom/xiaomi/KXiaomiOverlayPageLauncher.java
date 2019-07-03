package cn.junhua.android.permission.rom.xiaomi;

import android.content.Context;
import android.content.Intent;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 11:05
 */
public class KXiaomiOverlayPageLauncher implements PageLauncher {
    @Override
    public boolean launch(PermissionHandler permissionHandler, int requestCode) {
        return ActivitiesFlat.create(permissionHandler, requestCode)
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                        intent.putExtra("extra_pkgname", context.getPackageName());
                    }
                })

                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                        intent.putExtra("extra_pkgname", context.getPackageName());
                        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                    }
                })
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                        intent.putExtra("extra_pkgname", context.getPackageName());
                        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                        intent.setPackage("com.miui.securitycenter");
                    }
                })
                .start();
    }
}
