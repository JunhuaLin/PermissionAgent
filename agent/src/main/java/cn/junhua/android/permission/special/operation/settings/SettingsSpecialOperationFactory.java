package cn.junhua.android.permission.special.operation.settings;

import android.os.Build;

import cn.junhua.android.permission.special.SpecialOperation;
import cn.junhua.android.permission.special.SpecialOperationFactory;

/**
 * 修改设置权限操作工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/30 12:31
 */
public class SettingsSpecialOperationFactory implements SpecialOperationFactory {
    @Override
    public SpecialOperation create() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return new WriteSettingsSpecialOperation();
        }
        return null;
    }
}
