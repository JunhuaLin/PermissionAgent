/*
 * Copyright © Zhenjie Yan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.junhua.android.permission.dangerous.checker;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;

import java.util.Arrays;
import java.util.List;

import cn.junhua.android.permission.agent.check.PermissionChecker;
import cn.junhua.android.permission.agent.check.PermissionTester;

/**
 * 执行权限操作接口<br/>
 * <p>
 * 参考：
 * https://github.com/yanzhenjie/AndPermission/tree/master/support/src/main/java/com/yanzhenjie/permission/checker
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/6 10:37
 */
public final class StrictPermissionChecker implements PermissionChecker {

    public StrictPermissionChecker() {
    }

    private static boolean checkReadCalendar(Context context) throws Throwable {
        PermissionTester test = new CalendarReadTester(context);
        return test.test();
    }

    private static boolean checkWriteCalendar(Context context) throws Throwable {
        PermissionTester test = new CalendarWriteTester(context);
        return test.test();
    }

    private static boolean checkCamera(Context context) throws Throwable {
        PermissionTester test = new CameraTester(context);
        return test.test();
    }

    private static boolean checkReadContacts(Context context) throws Throwable {
        PermissionTester test = new ContactsReadTester(context);
        return test.test();
    }

    private static boolean checkWriteContacts(Context context) throws Throwable {
        ContentResolver resolver = context.getContentResolver();
        PermissionTester test = new ContactsWriteTester(resolver);
        return test.test();
    }

    private static boolean checkCoarseLocation(Context context) throws Throwable {
        PermissionTester test = new LocationCoarseTester(context);
        return test.test();
    }

    private static boolean checkFineLocation(Context context) throws Throwable {
        PermissionTester test = new LocationFineTester(context);
        return test.test();
    }

    private static boolean checkRecordAudio(Context context) throws Throwable {
        PermissionTester test = new RecordAudioTester(context);
        return test.test();
    }

    private static boolean checkReadPhoneState(Context context) throws Throwable {
        PermissionTester test = new PhoneStateReadTester(context);
        return test.test();
    }

    private static boolean checkReadCallLog(Context context) throws Throwable {
        PermissionTester test = new CallLogReadTester(context);
        return test.test();
    }

    private static boolean checkWriteCallLog(Context context) throws Throwable {
        PermissionTester test = new CallLogWriteTester(context);
        return test.test();
    }

    private static boolean checkAddVoicemail(Context context) throws Throwable {
        PermissionTester test = new AddVoicemailTester(context);
        return test.test();
    }

    private static boolean checkSip(Context context) throws Throwable {
        PermissionTester test = new SipTester(context);
        return test.test();
    }

    private static boolean checkSensors(Context context) throws Throwable {
        PermissionTester test = new SensorsTester(context);
        return test.test();
    }

    private static boolean checkReadSms(Context context) throws Throwable {
        PermissionTester test = new SmsReadTester(context);
        return test.test();
    }

    private static boolean checkReadStorage() throws Throwable {
        PermissionTester test = new StorageReadTester();
        return test.test();
    }

    private static boolean checkWriteStorage() throws Throwable {
        PermissionTester test = new StorageWriteTester();
        return test.test();
    }

    @Override
    public boolean hasPermissions(Context context, String... permissions) {
        return hasPermissions(context, Arrays.asList(permissions));
    }

    @Override
    public boolean hasPermissions(Context context, List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return true;

        for (String permission : permissions) {
            if (!hasPermissions(context, permission)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasPermissions(Context context, String permission) {
        try {
            switch (permission) {
                case Manifest.permission.READ_CALENDAR:
                    return checkReadCalendar(context);
                case Manifest.permission.WRITE_CALENDAR:
                    return checkWriteCalendar(context);
                case Manifest.permission.CAMERA:
                    return checkCamera(context);
                case Manifest.permission.READ_CONTACTS:
                    return checkReadContacts(context);
                case Manifest.permission.WRITE_CONTACTS:
                    return checkWriteContacts(context);
                case Manifest.permission.GET_ACCOUNTS:
                    return true;
                case Manifest.permission.ACCESS_COARSE_LOCATION:
                    return checkCoarseLocation(context);
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    return checkFineLocation(context);
                case Manifest.permission.RECORD_AUDIO:
                    return checkRecordAudio(context);
                case Manifest.permission.READ_PHONE_STATE:
                    return checkReadPhoneState(context);
                case Manifest.permission.CALL_PHONE:
                    return true;
                case Manifest.permission.READ_CALL_LOG:
                    return checkReadCallLog(context);
                case Manifest.permission.WRITE_CALL_LOG:
                    return checkWriteCallLog(context);
                case Manifest.permission.ADD_VOICEMAIL:
                    return checkAddVoicemail(context);
                case Manifest.permission.USE_SIP:
                    return checkSip(context);
                case Manifest.permission.PROCESS_OUTGOING_CALLS:
                    return true;
                case Manifest.permission.BODY_SENSORS:
                    return checkSensors(context);
                case Manifest.permission.SEND_SMS:
                case Manifest.permission.RECEIVE_MMS:
                    return true;
                case Manifest.permission.READ_SMS:
                    return checkReadSms(context);
                case Manifest.permission.RECEIVE_WAP_PUSH:
                case Manifest.permission.RECEIVE_SMS:
                    return true;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                    return checkReadStorage();
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    return checkWriteStorage();
            }
        } catch (Throwable e) {
            return false;
        }
        return true;
    }
}