package cn.junhua.android.permission.special.operation;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.utils.ActivitiesFlat;
import cn.junhua.android.permission.utils.Const;
import cn.junhua.android.permission.utils.PermissionUtil;
import cn.junhua.android.permission.rom.Rom;
import cn.junhua.android.permission.rom.OnRomAction;

/**
 * 兼容rom权限操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 14:08
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public abstract class RomOverlaySpecialOperation extends BaseOverlaySpecialOperation {

    private Map<Rom, OnRomAction> mRomRunnableMap = new HashMap<>();
    private OnRomAction mDefaultRomAction = new OnRomAction() {
        @Override
        public void onAction(PermissionHandler permissionHandler, int requestCode) {
            ActivitiesFlat.create(permissionHandler, requestCode)
                    .addAction(getAppDetailsIntentAction())
                    .start();
        }
    };

    @Const.OP_PERMISSION
    private String mOpPermission;

    public RomOverlaySpecialOperation(@Const.OP_PERMISSION String mOpPermission) {
        this.mOpPermission = mOpPermission;
    }

    @Override
    public void startActivityForResult(PermissionHandler permissionHandler, int requestCode) {
        Set<Rom> romSet = mRomRunnableMap.keySet();
        for (Rom rom : romSet) {
            if (rom.check()) {
                OnRomAction romAction = mRomRunnableMap.get(rom);
                if (romAction != null) {
                    romAction.onAction(permissionHandler, requestCode);
                    return;
                }
            }
        }
        //默认跳去应用详情页
        mDefaultRomAction.onAction(permissionHandler, requestCode);
    }

    public void addDefaultMatchRom(OnRomAction defaultRomAction) {
        mDefaultRomAction = defaultRomAction;
    }

    public void addMatchRom(Rom rom, OnRomAction action) {
        mRomRunnableMap.put(rom, action);
    }

    @Override
    public boolean checkPermission(Context context) {
        return PermissionUtil.checkOpNoThrow(context, mOpPermission);
    }

}
