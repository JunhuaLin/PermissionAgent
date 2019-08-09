package cn.junhua.android.permission.rom.oppo;

import android.os.Build;

import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.rom.RomPageLauncherFactoryWrapper;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * Oppo手机适配工厂
 *
 * @author junhua.lin<br />
 * CREATED 2019/6/19 10:09
 */
public class OppoPageLauncherFactory extends RomPageLauncherFactoryWrapper {
    @Override
    public boolean check() {
        return RomUtils.checkManufacturer("oppo");
    }

    @Override
    public PageLauncher createOverlayLauncher() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return new KOppoOverlayPageLauncher();
        }
        return null;
    }

    @Override
    public PageLauncher createAppDetailLauncher() {
        return new OppoAppDetailPageLauncher();
    }
}
