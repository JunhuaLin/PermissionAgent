package cn.junhua.android.permission.agent.callback;

import android.content.Context;

import cn.junhua.android.permission.agent.AgentExecutor;

/**
 * 向用户解释说明
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 14:51
 */
public interface OnRationaleCallback<T> {
    /**
     * 当需要向用户解释说明时候回调
     *
     * @param permissions 需要解释的权限列表
     * @param executor    继续申请
     */
    void onRationale(Context context, T permissions, AgentExecutor executor);
}
