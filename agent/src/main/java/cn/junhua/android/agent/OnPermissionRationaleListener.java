package cn.junhua.android.agent;

import cn.junhua.android.agent.manager.PermissionAgentRepeater;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 14:51
 */
public interface OnPermissionRationaleListener {
    void onShowRationale(String[] permissions, PermissionAgentRepeater repeater);
}
