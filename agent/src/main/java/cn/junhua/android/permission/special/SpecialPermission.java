package cn.junhua.android.permission.special;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import cn.junhua.android.permission.special.operation.InstallSpecialOperation;
import cn.junhua.android.permission.special.operation.OverlaySpecialOperation;
import cn.junhua.android.permission.special.operation.WriteSettingsSpecialOperation;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 10:00
 */
@TargetApi(Build.VERSION_CODES.O)
public enum SpecialPermission implements SpecialOperation {
    REQUEST_INSTALL_PACKAGES(new InstallSpecialOperation()),
    WRITE_SETTINGS(new WriteSettingsSpecialOperation()),
    SYSTEM_ALERT_WINDOW(new OverlaySpecialOperation());

    private SpecialOperation mOperation;

    SpecialPermission(SpecialOperation operation) {
        mOperation = operation;
    }

    @Override
    public String getPermission() {
        return mOperation.getPermission();
    }

    @Override
    public Intent getIntent(Context context) {
        return mOperation.getIntent(context);
    }

    @Override
    public boolean checkPermission(Context context) {
        return mOperation.checkPermission(context);
    }}
