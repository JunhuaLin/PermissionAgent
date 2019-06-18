package cn.junhua.android.permission.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.OnRomAction;
import cn.junhua.android.permission.rom.Rom;
import cn.junhua.android.permission.utils.ActivitiesFlat;

/**
 * 去应用设置页面
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/18 15:21
 */
public class SettingPage {

    private Map<Rom, OnRomAction> mRomRunnableMap = new HashMap<>();
    private ActivitiesFlat.OnIntentAction mAppDetailsIntentAction = new ActivitiesFlat.OnIntentAction() {
        @Override
        public void onIntentAction(Context context, Intent intent) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
    };

    public SettingPage() {
        mRomRunnableMap.put(Rom.Huawei, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setClassName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
                            }
                        })
                        .addAction(mAppDetailsIntentAction)
                        .start();
            }
        });

        mRomRunnableMap.put(Rom.Xiaomi, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                                intent.putExtra("extra_pkgname", context.getPackageName());
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                                intent.putExtra("extra_pkgname", context.getPackageName());
                                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                                intent.putExtra("extra_pkgname", context.getPackageName());
                                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                            }
                        })
                        .addAction(mAppDetailsIntentAction)
                        .start();
            }
        });

        mRomRunnableMap.put(Rom.Vivo, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.putExtra("packagename", context.getPackageName());
                                intent.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.putExtra("packagename", context.getPackageName());
                                intent.setClassName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity");
                            }
                        })
                        .addAction(mAppDetailsIntentAction)
                        .start();
            }
        });

        mRomRunnableMap.put(Rom.Oppo, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.putExtra("packageName", context.getPackageName());
                                intent.setClassName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
                            }
                        })
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.putExtra("packageName", context.getPackageName());
                                intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.PermissionAppListActivity");
                            }
                        })
                        .addAction(mAppDetailsIntentAction)
                        .start();
            }
        });

        mRomRunnableMap.put(Rom.Meizu, new OnRomAction() {
            @Override
            public void onAction(PermissionHandler permissionHandler, int requestCode) {
                ActivitiesFlat.create(permissionHandler, requestCode)
                        .addAction(new ActivitiesFlat.OnIntentAction() {
                            @Override
                            public void onIntentAction(Context context, Intent intent) {
                                intent.setAction("com.meizu.safe.security.SHOW_APPSEC");
                                intent.putExtra("packageName", context.getPackageName());
                                intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
                            }
                        })
                        .addAction(mAppDetailsIntentAction)
                        .start();
            }
        });
    }

    public void start(PermissionHandler permissionHandler, int requestCode) {
        Set<Rom> romSet = mRomRunnableMap.keySet();
        for (Rom rom : romSet) {
            if (rom.check()) {
                OnRomAction onRomAction = mRomRunnableMap.get(rom);
                if (onRomAction != null) {
                    onRomAction.onAction(permissionHandler, requestCode);
                    return;
                }
            }
        }

        //默认操作
        ActivitiesFlat.create(permissionHandler, requestCode)
                .addAction(mAppDetailsIntentAction)
                .start();
    }

}
