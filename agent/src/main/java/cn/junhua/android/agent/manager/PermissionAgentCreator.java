package cn.junhua.android.agent.manager;

import cn.junhua.android.agent.AgentManager;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 17:01
 */
public interface PermissionAgentCreator {
    AgentManager request(String... permissions);
}
