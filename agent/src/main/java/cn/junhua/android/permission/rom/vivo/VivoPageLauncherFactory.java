package cn.junhua.android.permission.rom.vivo;

import android.os.Build;

import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.rom.RomPageLauncherFactoryWrapper;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * Vivo手机适配工厂
 *
 * @author junhua.lin<br />
 * CREATED 2019/6/19 10:09
 */
public class VivoPageLauncherFactory extends RomPageLauncherFactoryWrapper {
    @Override
    public boolean check() {
        return RomUtils.checkManufacturer("vivo")
                || RomUtils.checkSystemProperty("ro.vivo.os.name")
                || RomUtils.checkSystemProperty("ro.vivo.os.version");
    }

    @Override
    public PageLauncher createOverlayLauncher() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return new KVivoOverlayPageLauncher();
        }
        return null;
    }

    @Override
    public PageLauncher createAppDetailLauncher() {
        return new VivoAppDetailPageLauncher();
    }
}
