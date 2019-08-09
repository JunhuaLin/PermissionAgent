package cn.junhua.android.permission.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author junhua.lin<br />
 * CREATED 2018/12/6 16:46
 */
public class PermissionUtil {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean checkOpNoThrow(Context context, @Const.OP_PERMISSION String opFieldName) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return true;

       /* String opValue = AppOpsManagerCompat.permissionToOp(opFieldName);
        if (opValue == null) return false;

        int result = AppOpsManagerCompat.noteOpNoThrow(
                context,
                opValue,
                context.getApplicationInfo().uid,
                context.getPackageName());

        return result == AppOpsManagerCompat.MODE_ALLOWED;*/

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
