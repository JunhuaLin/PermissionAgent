package cn.junhua.android.permission.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.permission.agent.PermissionHandler;

/**
 * 扁平化启动目标activity
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/5 13:38
 */
public class ActivitiesFlat {
    private List<OnIntentAction> mIntentActionList;
    private PermissionHandler mPermissionHandler;
    private int mRequestCode;

    private ActivitiesFlat(PermissionHandler permissionHandler, int requestCode) {
        mIntentActionList = new ArrayList<>();
        mPermissionHandler = permissionHandler;
        mRequestCode = requestCode;
    }

    public static ActivitiesFlat create(PermissionHandler permissionHandler, int requestCode) {
        return new ActivitiesFlat(permissionHandler, requestCode);
    }

    public ActivitiesFlat addAction(OnIntentAction onIntentAction) {
        if (!mIntentActionList.contains(onIntentAction)) {
            mIntentActionList.add(onIntentAction);
        }
        return this;
    }

    public boolean start() {
        Context context = mPermissionHandler.getContext();
        Intent intentTemp;
        for (OnIntentAction onIntentAction : mIntentActionList) {
            try {
                intentTemp = new Intent();
                onIntentAction.onIntentAction(context, intentTemp);
                if (!hasActivity(intentTemp)) {
                    continue;
                }
                mPermissionHandler.startActivityForResult(intentTemp, mRequestCode);

                return true; //有成功启动的就退出
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;//没有成功启动
    }


    private boolean hasActivity(Intent intent) {
        PackageManager packageManager = mPermissionHandler.getContext().getPackageManager();
        return packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    public interface OnIntentAction {
        void onIntentAction(Context context, Intent intent);
    }
}
