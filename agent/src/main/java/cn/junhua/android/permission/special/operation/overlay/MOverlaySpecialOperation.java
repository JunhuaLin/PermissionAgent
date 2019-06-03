package cn.junhua.android.permission.special.operation.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.utils.ExceptionFlat;

/**
 * 浮窗权限操作 api>=23
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@TargetApi(Build.VERSION_CODES.M)
public class MOverlaySpecialOperation extends BaseOverlaySpecialOperation {

    @Override
    public void startActivityForResult(final PermissionHandler permissionHandler, final int requestCode) {
        final Context context = permissionHandler.getContext();
        ExceptionFlat.create()
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() throws Exception {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        permissionHandler.startActivityForResult(intent, requestCode);
                    }
                })
                .onCatch(new ExceptionFlat.Action() {
                    @Override
                    public void onAction() throws Exception {
                        permissionHandler.startActivityForResult(appDetailsIntent(context), requestCode);
                    }
                });
    }

    @Override
    public boolean checkPermission(Context context) {
        return Settings.canDrawOverlays(context) && tryDisplayDialog(context);
    }
}
