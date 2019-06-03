package cn.junhua.android.permission.special.operation.overlay;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;

import cn.junhua.android.permission.special.SpecialOperation;

/**
 * 浮窗权限操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
abstract class BaseOverlaySpecialOperation implements SpecialOperation {

    /**
     * app详情页面
     */
    static Intent appDetailsIntent(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    boolean tryDisplayDialog(Context context) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        int windowType;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            windowType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            windowType = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        dialog.getWindow().setType(windowType);
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
