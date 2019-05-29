package cn.junhua.android.permission.impl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 12:38
 */
public class ActivityHolder implements Application.ActivityLifecycleCallbacks {

    private List<Activity> mActivityList = new ArrayList<>();

    ActivityHolder(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    Activity getActivity() {
        for (int i = mActivityList.size() - 1; i >= 0; --i) {
            return mActivityList.get(i);
        }

        throw new IllegalStateException();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityList.add(activity);
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
    }


}
