package cn.junhua.android.permission.rom.oppo;

import android.content.Context;
import android.content.Intent;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * @author junhua.lin<br />
 * CREATED 2019/6/19 11:05
 */
public class OppoAppDetailPageLauncher implements PageLauncher {
    @Override
    public boolean launch(PermissionHandler permissionHandler, int requestCode) {
        return ActivitiesFlat.create(permissionHandler, requestCode)
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.putExtra("packageName", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName(
                                "com.color.safecenter",
                                "com.color.safecenter.permission.PermissionManagerActivity");
                    }
                })
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.putExtra("packageName", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName(
                                "com.oppo.safe",
                                "com.oppo.safe.permission.PermissionAppListActivity");
                    }
                })
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.putExtra("packageName", context.getPackageName());
                        intent.putExtra("package", context.getPackageName());
                        intent.setClassName(
                                "com.oppo.safe",
                                "com.coloros.safecenter.permission.singlepage.PermissionSinglePageActivity");
                    }
                })
                .start();
    }
}
