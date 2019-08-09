package cn.junhua.android.permission.special.operation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.Rom;
import cn.junhua.android.permission.special.SpecialOperation;

/**
 * 修改设置权限操作
 *
 * @author junhua.lin<br />
 * CREATED 2019/5/29 14:08
 */
@TargetApi(Build.VERSION_CODES.M)
public class WriteSettingsSpecialOperation implements SpecialOperation {

    @Override
    public void startActivityForResult(PermissionHandler permissionHandler, int requestCode) {
        Rom.currentRom().createWriteSettingsLauncher().launch(permissionHandler, requestCode);
    }

    @Override
    public boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.System.canWrite(context);
        }
        return true;
    }
}
