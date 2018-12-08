package cn.junhua.android.permission.core;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 17:01
 */
public interface AgentCreator {
    AgentHandler request(String... permissions);
}
