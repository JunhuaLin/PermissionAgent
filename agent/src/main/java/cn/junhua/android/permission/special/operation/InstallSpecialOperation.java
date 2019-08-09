package cn.junhua.android.permission.special.operation;

import android.content.Context;
import android.os.Build;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.Rom;
import cn.junhua.android.permission.special.SpecialOperation;

/**
 * 安装未知apk权限操作
 *
 * @author junhua.lin<br />
 * CREATED 2019/5/29 14:08
 */
public class InstallSpecialOperation implements SpecialOperation {

    @Override
    public void startActivityForResult(PermissionHandler permissionHandler, int requestCode) {
        Rom.currentRom().createInstallLauncher().launch(permissionHandler, requestCode);
    }

    @Override
    public boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }
}
