package cn.junhua.android.permission.rom.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * 默认跳转到应用详情页
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 11:20
 */
public class DefaultPageLauncher implements PageLauncher {

    @Override
    public boolean launch(PermissionHandler permissionHandler, int requestCode) {
        return ActivitiesFlat.create(permissionHandler, requestCode)
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                })
                .start();
    }
}
