package cn.junhua.android.permission.special.operation;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.Rom;
import cn.junhua.android.permission.special.SpecialOperation;
import cn.junhua.android.permission.utils.Const;
import cn.junhua.android.permission.utils.PermissionUtil;

/**
 * 浮窗权限操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
public class OverlaySpecialOperation implements SpecialOperation {

    @Override
    public void startActivityForResult(final PermissionHandler permissionHandler, final int requestCode) {
        Rom.currentRom().createOverlayLauncher().launch(permissionHandler, requestCode);
    }

    @Override
    public boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context) && tryDisplayDialog(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return PermissionUtil.checkOpNoThrow(context, Const.OP_SYSTEM_ALERT_WINDOW);
        }
        return true;
    }

    private boolean tryDisplayDialog(Context context) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        int windowType;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            windowType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            windowType = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        Window window = dialog.getWindow();
        if (window != null) {
            window.setType(windowType);
        }
        try {
            dialog.show();
        } catch (Exception e) {
            return false;
        } finally {
            if (dialog.isShowing()) dialog.dismiss();
        }
        return true;
    }
}
