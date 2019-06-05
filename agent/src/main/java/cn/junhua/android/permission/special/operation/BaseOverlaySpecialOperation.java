package cn.junhua.android.permission.special.operation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;

import cn.junhua.android.permission.special.SpecialOperation;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * 权限操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
public abstract class BaseOverlaySpecialOperation implements SpecialOperation {

    private ActivitiesFlat.OnIntentAction mAppDetailsIntentAction = new ActivitiesFlat.OnIntentAction() {
        @Override
        public void onIntentAction(Context context, Intent intent) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
    };

    /**
     * app详情页面
     */
    public static Intent appDetailsIntent(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * app详情页面
     */
    public ActivitiesFlat.OnIntentAction getAppDetailsIntentAction() {
        return mAppDetailsIntentAction;
    }

    public boolean tryDisplayDialog(Context context) {
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
