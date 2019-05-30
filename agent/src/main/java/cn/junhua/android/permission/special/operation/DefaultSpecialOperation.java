package cn.junhua.android.permission.special.operation;

import android.content.Context;
import android.content.Intent;

import cn.junhua.android.permission.special.SpecialOperation;

/**
 * 默认同意所有特殊权限
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
public class DefaultSpecialOperation implements SpecialOperation {

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public Intent getIntent(Context context) {
        return null;
    }

    @Override
    public boolean checkPermission(Context context) {
        return true;
    }
}
