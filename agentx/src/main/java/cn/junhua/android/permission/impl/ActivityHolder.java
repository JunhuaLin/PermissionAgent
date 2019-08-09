package cn.junhua.android.permission.impl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.permission.utils.AgentLog;

/**
 * @author junhua.lin<br />
 * CREATED 2019/5/29 12:38
 */
public class ActivityHolder implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = ActivityHolder.class.getSimpleName();

    private List<Activity> mActivityList = new ArrayList<>();

    public ActivityHolder(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    public Activity getCurrentActivity() {
        if (mActivityList.isEmpty()) {
            throw new IllegalStateException("请在Application的onCreate中初始化");
        }
        AgentLog.d(TAG, "current activity stack " + mActivityList);
        int size = mActivityList.size();
        Activity activity;
        do {
            activity = mActivityList.get(--size);
            if (!activity.isFinishing()) {
                return activity;
            }
        } while (size > 0);

        return mActivityList.get(0);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityList.add(activity);
        AgentLog.d(TAG, "[" + activity + "] is added to ActivityHolder");
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityList.remove(activity);
        AgentLog.d(TAG, "[" + activity + "] is removed by ActivityHolder");
    }


}
