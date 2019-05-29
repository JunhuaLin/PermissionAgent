package cn.junhua.android.permission.agent.callback;

/**
 * 权限操作回调处理
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 14:51
 */
public interface OnDeniedCallback {
    /**
     * 多个权限同时申请时：<br/>
     * 允许时permissions为接受的权限列表<br/>
     * 拒接时permissions为拒接的权限列表<br/>
     *
     * @param permissions 权限列表
     */
    void onDenied(String[] permissions);
}
