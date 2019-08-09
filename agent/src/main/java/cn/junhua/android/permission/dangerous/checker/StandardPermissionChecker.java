package cn.junhua.android.permission.dangerous.checker;

import android.content.Context;
import android.os.Build;

import java.util.Arrays;
import java.util.List;

import cn.junhua.android.permission.agent.check.PermissionChecker;

/**
 * 正常检测
 *
 * @author junhua.lin<br />
 * CREATED 2019/6/10 10:45
 */
public class StandardPermissionChecker implements PermissionChecker {

    @Override
    public boolean hasPermissions(Context context, String... permissions) {
        return hasPermissions(context, Arrays.asList(permissions));
    }

    @Override
    public boolean hasPermissions(Context context, List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;

        if (permissions.size() == 0) {
            return false;
        }

        for (String permission : permissions) {
            int result = android.support.v4.content.PermissionChecker.checkSelfPermission(context, permission);
            if (result != android.support.v4.content.PermissionChecker.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }
}
