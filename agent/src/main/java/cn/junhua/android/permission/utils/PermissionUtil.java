package cn.junhua.android.permission.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.PermissionChecker;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:46
 */
public class PermissionUtil {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean checkOpNoThrow(Context context, String opFieldName) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return true;
        try {
            Class<AppOpsManager> appOpsClass = AppOpsManager.class;
            Method method = appOpsClass.getMethod(CHECK_OP_NO_THROW, int.class, int.class, String.class);
            Field opField = appOpsClass.getDeclaredField(opFieldName);
            int opValue = (int) opField.get(Integer.class);
            int result = (int) method.invoke(
                    context.getSystemService(Context.APP_OPS_SERVICE),
                    opValue,
                    context.getApplicationInfo().uid,
                    context.getPackageName());
            return result == AppOpsManager.MODE_ALLOWED || result == 4;
        } catch (Throwable e) {
            return true;
        }
    }

}
