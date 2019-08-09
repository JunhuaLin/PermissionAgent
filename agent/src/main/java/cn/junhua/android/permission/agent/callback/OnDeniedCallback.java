package cn.junhua.android.permission.agent.callback;

import android.content.Context;

/**
 * 权限操作回调处理
 *
 * @author junhua.lin<br />
 * CREATED 2018/12/7 14:51
 */
public interface OnDeniedCallback<T> {
    /**
     * 多个权限同时申请时：<br/>
     * 允许时permissions为接受的权限列表<br/>
     * 拒接时permissions为拒接的权限列表<br/>
     *
     * @param permissions 权限列表
     */
    void onDenied(Context context, T permissions);
}
