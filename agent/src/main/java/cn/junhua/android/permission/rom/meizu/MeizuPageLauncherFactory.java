package cn.junhua.android.permission.rom.meizu;

import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.rom.RomPageLauncherFactory;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * 魅族手机适配工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 10:09
 */
public class MeizuPageLauncherFactory implements RomPageLauncherFactory {
    @Override
    public boolean check() {
        return RomUtils.checkManufacturer("meizu")
                || RomUtils.checkSystemProperty("ro.build.display.id", "flyme")
                || RomUtils.checkSystemProperty("ro.flyme.published");
    }

    @Override
    public PageLauncher createInstallLauncher() {
        return null;
    }

    @Override
    public PageLauncher createNotifyLauncher() {
        return null;
    }

    @Override
    public PageLauncher createOverlayLauncher() {
        return null;
    }

    @Override
    public PageLauncher createWriteSettingsLauncher() {
        return null;
    }

    @Override
    public PageLauncher createAppDetailLauncher() {
        return null;
    }
}
