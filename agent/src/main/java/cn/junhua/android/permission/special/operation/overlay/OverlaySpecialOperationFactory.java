package cn.junhua.android.permission.special.operation.overlay;

import android.os.Build;

import cn.junhua.android.permission.special.SpecialOperation;
import cn.junhua.android.permission.special.SpecialOperationFactory;
import cn.junhua.android.permission.special.operation.DefaultSpecialOperation;

/**
 * 浮窗权限操作工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/30 12:31
 */
public class OverlaySpecialOperationFactory implements SpecialOperationFactory {
    @Override
    public SpecialOperation create() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return new OverlaySpecialOperation();
        }
        return new DefaultSpecialOperation();
    }
}
