package cn.junhua.android.permission.utils;

import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomUtils {
    private static final String TAG = RomUtils.class.getSimpleName();

    private static final String MANUFACTURER = Build.MANUFACTURER.toLowerCase();

    private static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            AgentLog.d(TAG, "Unable to read sysprop " + propName);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    AgentLog.d(TAG, "Exception while closing InputStream");
                }
            }
        }
        return line;
    }

    public static boolean checkIsHuaweiRom() {
        return MANUFACTURER.contains("huawei");
    }

    /**
     * check if is miui ROM
     */
    public static boolean checkIsMiuiRom() {
        if (MANUFACTURER.contains("xiaomi")) {
            return true;
        }
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
    }

    public static boolean checkIsMeizuRom() {
        if (MANUFACTURER.contains("meizu")) {
            return true;
        }
        String meizuFlymeOSFlag = getSystemProperty("ro.build.display.id");
        return !TextUtils.isEmpty(meizuFlymeOSFlag) && meizuFlymeOSFlag.toLowerCase().contains("flyme");

    }

    public static boolean checkIs360Rom() {
        return MANUFACTURER.contains("qiku")
                || MANUFACTURER.contains("360");
    }

    public static boolean checkIsOppoRom() {
        return MANUFACTURER.contains("oppo");
    }

    public static boolean checkIsVivoRom() {
        return Build.MANUFACTURER.contains("vivo");
    }
}
