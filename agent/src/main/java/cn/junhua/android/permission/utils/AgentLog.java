package cn.junhua.android.permission.utils;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:01
 */
public class AgentLog {

    private static boolean DEBUG = false;

    public static void setDebug(boolean debug) {
        AgentLog.DEBUG = debug;
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.d(tag, msg);
        }
    }
}
