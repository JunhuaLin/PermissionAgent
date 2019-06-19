package cn.junhua.android.permission.rom.xiaomi;

import android.os.Build;

import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.rom.RomPageLauncherFactory;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * 小米手机适配工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 10:09
 */
public class XiaomiPageLauncherFactory implements RomPageLauncherFactory {
    @Override
    public boolean check() {
        return RomUtils.checkManufacturer("xiaomi")
                || RomUtils.checkSystemProperty("ro.miui.ui.version.name")
                || RomUtils.checkSystemProperty("ro.miui.internal.storage")
                || RomUtils.checkSystemProperty("ro.miui.ui.version.code");
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
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return new KXiaomiOverlayPageLauncher();
        }
        return null;
    }

    @Override
    public PageLauncher createWriteSettingsLauncher() {
        return null;
    }

    @Override
    public PageLauncher createAppDetailLauncher() {
        return new XiaomiAppDetailPageLauncher();
    }
}
