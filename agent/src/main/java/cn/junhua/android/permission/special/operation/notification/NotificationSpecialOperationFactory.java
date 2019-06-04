package cn.junhua.android.permission.special.operation.notification;

import android.os.Build;

import cn.junhua.android.permission.special.SpecialOperation;
import cn.junhua.android.permission.special.SpecialOperationFactory;

/**
 * 推送通知权限操作工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/30 12:31
 */
public class NotificationSpecialOperationFactory implements SpecialOperationFactory {
    @Override
    public SpecialOperation create() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ONotificationSpecialOperation();
        }
        return null;
    }
}
