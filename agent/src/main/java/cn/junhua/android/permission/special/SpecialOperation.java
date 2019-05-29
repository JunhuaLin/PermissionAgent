package cn.junhua.android.permission.special;

import android.content.Context;
import android.content.Intent;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 10:35
 */
public interface SpecialOperation {
    String getPermission();

    Intent getIntent(Context context);

    boolean checkPermission(Context context);

}
