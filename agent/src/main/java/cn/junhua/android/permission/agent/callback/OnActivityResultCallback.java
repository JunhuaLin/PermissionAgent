package cn.junhua.android.permission.agent.callback;

import android.content.Intent;

/**
 * 处理特殊权限申请相关回调
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:29
 */
public interface OnActivityResultCallback {
    void onActivityResultCallback(int requestCode, int resultCode, Intent data);
}
