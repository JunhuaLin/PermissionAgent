package cn.junhua.android.permission.special.operation;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.Rom;
import cn.junhua.android.permission.special.SpecialOperation;
import cn.junhua.android.permission.utils.Const;
import cn.junhua.android.permission.utils.PermissionUtil;

/**
 * 推送通知权限操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
public class NotificationSpecialOperation implements SpecialOperation {

    @Override
    public void startActivityForResult(final PermissionHandler permissionHandler, final int requestCode) {
        Rom.currentRom().createNotifyLauncher().launch(permissionHandler, requestCode);
    }

    @Override
    public boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            return notificationManager.areNotificationsEnabled();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return PermissionUtil.checkOpNoThrow(context, Const.OP_POST_NOTIFICATION);
        }
        return true;
    }
}
