package cn.junhua.android.permission.dangerous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.dangerous.checker.Permission;
import cn.junhua.android.permission.utils.Executor;

/**
 * 串行申请危险权限
 *
 * @author junhua.lin<br />
 * CREATED 2018/12/19 13:39
 */
public class EachDangerousPermissionAgent extends DangerousPermissionAgent {

    private List<String> mPermissionListTemp = new ArrayList<>(1);

    public EachDangerousPermissionAgent(Executor executor, PermissionHandler permissionHandler, String[] permissions) {
        super(executor, permissionHandler, permissions);
    }

    @Override
    public List<String> onInitPermissions(String[] permissions) {
        return new ArrayList<>(Arrays.asList(permissions));
    }

    @Override
    public List<String> getPermissions() {
        return mPermissionListTemp;
    }

    @Override
    public void apply() {
        List<String> permissions = super.getPermissions();
        if (permissions.isEmpty()) return;

        mPermissionListTemp.clear();
        mPermissionListTemp.addAll(Permission.handleGroup(permissions.remove(0)));
        super.apply();
    }

    @Override
    protected void dispatchGranted(List<String> permissions) {
        super.dispatchGranted(permissions);
        apply();
    }

    @Override
    protected void dispatchDenied(List<String> permissions) {
        super.dispatchDenied(permissions);
        apply();
    }
}
