package cn.junhua.android.permission.dangerous.checker;

import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理权限组和权限<p>
 * https://developer.android.com/guide/topics/security/permissions.html?hl=zh-cn#perm-groups
 * </p>
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/10 15:47
 */
public class Permission {

    private static final Map<String, List<String>> GROUP_MAP = new HashMap<>();
    /**
     * 危险权限
     */
    private static final String READ_CALENDAR = "android.permission.READ_CALENDAR";
    private static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";
    private static final String CAMERA = "android.permission.CAMERA";
    private static final String READ_CONTACTS = "android.permission.READ_CONTACTS";
    private static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    private static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
    private static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    private static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    private static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";
    private static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    private static final String CALL_PHONE = "android.permission.CALL_PHONE";
    private static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
    private static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
    private static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";
    private static final String USE_SIP = "android.permission.USE_SIP";
    private static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";
    private static final String READ_PHONE_NUMBERS = "android.permission.READ_PHONE_NUMBERS";
    private static final String ANSWER_PHONE_CALLS = "android.permission.ANSWER_PHONE_CALLS";
    private static final String BODY_SENSORS = "android.permission.BODY_SENSORS";
    private static final String SEND_SMS = "android.permission.SEND_SMS";
    private static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    private static final String READ_SMS = "android.permission.READ_SMS";
    private static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
    private static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";
    private static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    private static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

    static {
        GROUP_MAP.put(Group.CALENDAR, Arrays.asList(Permission.READ_CALENDAR, Permission.WRITE_CALENDAR));
        GROUP_MAP.put(Group.CALL_LOG, Collections.<String>emptyList());//空
        GROUP_MAP.put(Group.CAMERA, Collections.singletonList(Permission.CAMERA));
        GROUP_MAP.put(Group.CONTACTS, Arrays.asList(
                Permission.READ_CONTACTS,
                Permission.WRITE_CONTACTS,
                Permission.GET_ACCOUNTS));
        GROUP_MAP.put(Group.LOCATION, Arrays.asList(Permission.ACCESS_FINE_LOCATION,
                Permission.ACCESS_COARSE_LOCATION));
        GROUP_MAP.put(Group.MICROPHONE, Collections.singletonList(Permission.RECORD_AUDIO));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            GROUP_MAP.put(Group.PHONE, Arrays.asList(Permission.READ_PHONE_STATE, Permission.CALL_PHONE, Permission.READ_CALL_LOG,
                    Permission.WRITE_CALL_LOG, Permission.ADD_VOICEMAIL, Permission.USE_SIP,
                    Permission.PROCESS_OUTGOING_CALLS, Permission.READ_PHONE_NUMBERS, Permission.ANSWER_PHONE_CALLS));
        } else {
            GROUP_MAP.put(Group.PHONE, Arrays.asList(Permission.READ_PHONE_STATE, Permission.CALL_PHONE, Permission.READ_CALL_LOG,
                    Permission.WRITE_CALL_LOG, Permission.ADD_VOICEMAIL, Permission.USE_SIP,
                    Permission.PROCESS_OUTGOING_CALLS));
        }


        GROUP_MAP.put(Group.SENSORS, Collections.singletonList(Permission.BODY_SENSORS));
        GROUP_MAP.put(Group.SMS, Arrays.asList(Permission.SEND_SMS, Permission.RECEIVE_SMS,
                Permission.READ_SMS, Permission.RECEIVE_WAP_PUSH, Permission.RECEIVE_MMS));
        GROUP_MAP.put(Group.STORAGE, Arrays.asList(Permission.READ_EXTERNAL_STORAGE,
                Permission.WRITE_EXTERNAL_STORAGE));
    }

    /**
     * 处理权限组
     */
    public static List<String> handleGroup(String... permissions) {
        List<String> permissionList = new ArrayList<>();
        List<String> permissionTemp;
        for (String permission : permissions) {
            if (GROUP_MAP.containsKey(permission)) {
                permissionTemp = GROUP_MAP.get(permission);
                if (permissionTemp != null && !permissionTemp.isEmpty()) {
                    permissionList.addAll(permissionTemp);
                }
            } else {
                permissionList.add(permission);
            }
        }
        return permissionList;
    }

    /**
     * 危险权限组
     */
    private static final class Group {
        private static final String CALENDAR = "android.permission-group.CALENDAR";
        private static final String CALL_LOG = "android.permission-group.CALL_LOG";
        private static final String CAMERA = "android.permission-group.CAMERA";
        private static final String CONTACTS = "android.permission-group.CONTACTS";
        private static final String LOCATION = "android.permission-group.LOCATION";
        private static final String MICROPHONE = "android.permission-group.MICROPHONE";
        private static final String PHONE = "android.permission-group.PHONE";
        private static final String SENSORS = "android.permission-group.SENSORS";
        private static final String SMS = "android.permission-group.SMS";
        private static final String STORAGE = "android.permission-group.STORAGE";
    }

}
