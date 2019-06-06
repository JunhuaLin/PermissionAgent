package cn.junhua.android.permission.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 特殊权限19<=api<23检测
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/3 15:47
 */

public interface Const {
    String OP_SYSTEM_ALERT_WINDOW = "OP_SYSTEM_ALERT_WINDOW";
    String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";


    @StringDef({OP_SYSTEM_ALERT_WINDOW, OP_POST_NOTIFICATION})
    @Retention(RetentionPolicy.SOURCE)
    @interface OP_PERMISSION {
    }
}
