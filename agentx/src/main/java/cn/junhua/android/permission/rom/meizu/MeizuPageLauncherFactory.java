package cn.junhua.android.permission.rom.meizu;

import android.os.Build;

import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.rom.RomPageLauncherFactoryWrapper;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * 魅族手机适配工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 10:09
 */
public class MeizuPageLauncherFactory extends RomPageLauncherFactoryWrapper {
    @Override
    public boolean check() {
        return RomUtils.checkManufacturer("meizu")
                || RomUtils.checkSystemProperty("ro.build.display.id", "flyme")
                || RomUtils.checkSystemProperty("ro.flyme.published");
    }

    @Override
    public PageLauncher createOverlayLauncher() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return new KMeizuOverlayPageLauncher();
        }
        return null;
    }

    @Override
    public PageLauncher createAppDetailLauncher() {
        return new MeizuAppDetailPageLauncher();
    }
}
