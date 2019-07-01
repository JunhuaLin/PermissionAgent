package cn.junhua.android.permission.rom.vivo;

import android.content.Context;
import android.content.Intent;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 11:05
 */
public class KVivoOverlayPageLauncher implements PageLauncher {
    @Override
    public boolean launch(PermissionHandler permissionHandler, int requestCode) {
        return ActivitiesFlat.create(permissionHandler, requestCode)
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.setClassName("com.iqoo.secure",
                                "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager");
                        intent.putExtra("packagename", context.getPackageName());
                    }
                })
                .addAction(new ActivitiesFlat.OnIntentAction() {
                    @Override
                    public void onIntentAction(Context context, Intent intent) {
                        intent.setClassName("com.iqoo.secure",
                                "com.iqoo.secure.safeguard.SoftPermissionDetailActivity");
                        intent.putExtra("packagename", context.getPackageName());
                    }
                })
                .start();
    }
}
