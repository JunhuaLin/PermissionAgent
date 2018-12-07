package cn.junhua.android.agent;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import cn.junhua.android.agent.manager.PermissionAgentCreator;

/**
 * 权限申请代理类
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/6 16:44
 */
public class PermissionAgent {

    private PermissionAgent() {
        throw new IllegalStateException("不能实例化");
    }

    public static PermissionAgentCreator with(@NonNull View view) {
        return with(view.getContext());
    }

    public static PermissionAgentCreator with(@NonNull Context context) {
        if (context instanceof FragmentActivity) {
            return with((FragmentActivity) context);
        } else if (context instanceof Activity) {
            return with((Activity) context);
        } else if (context instanceof ContextWrapper) {
            return with(((ContextWrapper) context).getBaseContext());
        } else {
            throw new IllegalArgumentException("不支持ApplicationContext");
        }
    }

    public static PermissionAgentCreator with(@NonNull Activity activity) {
        return new AgentManager(activity);
    }

    public static PermissionAgentCreator with(@NonNull android.app.Fragment fragment) {
        return new AgentManager(fragment.getActivity());
    }

    public static PermissionAgentCreator with(@NonNull FragmentActivity fragmentActivity) {
        return new AgentManager(fragmentActivity);
    }

    public static PermissionAgentCreator with(@NonNull Fragment fragment) {
        return new AgentManager(fragment.getActivity());
    }

}
