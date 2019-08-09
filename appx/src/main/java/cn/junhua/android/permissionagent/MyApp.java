package cn.junhua.android.permissionagent;

import android.app.Application;

import cn.junhua.android.permission.PermissionAgent;

/**
 * @author junhua.lin<br />
 * CREATED 2019/5/29 17:09
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PermissionAgent.setDebug(BuildConfig.DEBUG);
        PermissionAgent.getInstance().init(this);
    }
}
