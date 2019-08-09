package cn.junhua.android.permission.utils;

import cn.junhua.android.permission.PermissionAgent;

/**
 * 全局Log类<br/>
 * log标签分类两级：<br/>
 * 一级:TAG1=PermissionAgent可以过滤出本库所有的log日志<br/>
 * 二级:TAG2=xxx.class.getSimpleName()可以过滤出xxx类的log日志<br/>
 * log格式：D/{TAG1}: {TAG2}/{log msg}<br/>
 *
 * @author junhua.lin<br />
 * CREATED 2019/5/29 14:01
 */
public class AgentLog {

    private static final String TAG = PermissionAgent.class.getSimpleName();
    private static boolean DEBUG = false;

    public static void setDebug(boolean debug) {
        AgentLog.DEBUG = debug;
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.d(TAG, String.format("%s/%s", tag, msg));
        }
    }
}
