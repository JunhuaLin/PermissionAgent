package cn.junhua.android.permission.rom.vivo;

import android.content.Context;
import android.content.Intent;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 11:05
 */
public class VivoAppDetailPageLauncher implements PageLauncher {
    @Override
    public boolean launch(PermissionHandler permissionHandler, int requestCode) {
        return ActivitiesFlat.create(permissionHandler, requestCode)
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.putExtra("packagename", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName(
                                "com.vivo.permissionmanager",
                                "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
                    }
                })
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.putExtra("packagename", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName(
                                "com.iqoo.secure",
                                "com.iqoo.secure.safeguard.SoftPermissionDetailActivity");
                    }
                })
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.putExtra("packagename", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName(
                                "com.iqoo.secure",
                                "com.iqoo.secure.MainActivity");
                    }
                })
                .start();
    }
}
