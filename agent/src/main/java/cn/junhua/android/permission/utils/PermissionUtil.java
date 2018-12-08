package cn.junhua.android.permission.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:46
 */
public class PermissionUtil {
    /**
     * 判断权限是否授权
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        if (permissions.length == 0) {
            return false;
        }

        for (String permission : permissions) {
            int result = PermissionChecker.checkSelfPermission(context, permission);
            if (result != PermissionChecker.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断权限是否需要向用户解释
     */
    public static boolean shouldShowRationale(@NonNull Activity activity, @NonNull String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }
}
