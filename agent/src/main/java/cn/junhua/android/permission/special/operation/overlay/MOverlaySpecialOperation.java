package cn.junhua.android.permission.special.operation.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import cn.junhua.android.permission.special.SpecialOperation;

/**
 * 浮窗权限操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@TargetApi(Build.VERSION_CODES.M)
public class MOverlaySpecialOperation implements SpecialOperation {

    @Override
    public String getPermission() {
        return Settings.ACTION_MANAGE_OVERLAY_PERMISSION;
    }

    @Override
    public Intent getIntent(Context context) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public boolean checkPermission(Context context) {
        return Settings.canDrawOverlays(context);
    }
}
