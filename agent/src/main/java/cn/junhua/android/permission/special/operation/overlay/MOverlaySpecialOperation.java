package cn.junhua.android.permission.special.operation.overlay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.special.operation.BaseOverlaySpecialOperation;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * 浮窗权限操作 api>=23
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MOverlaySpecialOperation extends BaseOverlaySpecialOperation {

    @Override
    public void startActivityForResult(final PermissionHandler permissionHandler, final int requestCode) {
        ActivitiesFlat.create(permissionHandler, requestCode)
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                })
                .addAction(getAppDetailsIntentAction())
                .start();
    }

    @Override
    public boolean checkPermission(Context context) {
        return Settings.canDrawOverlays(context) && tryDisplayDialog(context);
    }
}
