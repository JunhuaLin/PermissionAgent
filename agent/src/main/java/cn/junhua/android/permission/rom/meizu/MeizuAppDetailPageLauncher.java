package cn.junhua.android.permission.rom.meizu;

import android.content.Context;
import android.content.Intent;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * @author junhua.lin<br />
 * CREATED 2019/6/19 11:05
 */
public class MeizuAppDetailPageLauncher implements PageLauncher {
    @Override
    public boolean launch(PermissionHandler permissionHandler, int requestCode) {
        return ActivitiesFlat.create(permissionHandler, requestCode)
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.setAction("com.meizu.safe.security.SHOW_APPSEC");
                        intent.putExtra("packageName", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
                    }
                })
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.putExtra("packageName", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName("com.meizu.safe", "com.meizu.safe.SecurityMainActivity");
                    }
                })
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.putExtra("packageName", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName("com.meizu.safe", "com.meizu.safe.permission.PermissionMainActivity");
                    }
                })
                .start();
    }
}
