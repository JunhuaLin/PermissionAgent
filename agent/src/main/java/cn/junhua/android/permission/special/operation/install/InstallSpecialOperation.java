package cn.junhua.android.permission.special.operation.install;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import cn.junhua.android.permission.special.SpecialOperation;

/**
 * 安装未知apk权限操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class InstallSpecialOperation implements SpecialOperation {

    @Override
    public String getPermission() {
        return Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES;
    }

    @Override
    public Intent getIntent(Context context) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public boolean checkPermission(Context context) {
        return context.getPackageManager().canRequestPackageInstalls();
    }
}
