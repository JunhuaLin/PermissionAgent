package cn.junhua.android.permission.utils;

import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomUtils {
    private static final String TAG = RomUtils.class.getSimpleName();

    private static final String MANUFACTURER = Build.MANUFACTURER.toLowerCase();

    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            AgentLog.d(TAG, "Unable to read sysprop " + propName);
            return "";
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

    public static boolean checkManufacturer(String rom) {
        return rom != null && MANUFACTURER.contains(rom.toLowerCase());
    }

}
