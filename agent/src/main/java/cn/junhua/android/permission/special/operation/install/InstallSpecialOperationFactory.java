package cn.junhua.android.permission.special.operation.install;

import android.os.Build;

import cn.junhua.android.permission.special.SpecialOperation;
import cn.junhua.android.permission.special.SpecialOperationFactory;

/**
 * 安装未知apk权限操作工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/30 12:31
 */
public class InstallSpecialOperationFactory implements SpecialOperationFactory {
    @Override
    public SpecialOperation create() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new OInstallSpecialOperation();
        }
        return null;
    }
}
