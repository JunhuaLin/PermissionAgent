package cn.junhua.android.permission.rom.qihu360;

import android.os.Build;

import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.rom.RomPageLauncherFactory;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * Oppo手机适配工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 10:09
 */
public class Qihu360PageLauncherFactory implements RomPageLauncherFactory {
    @Override
    public boolean check() {
        return RomUtils.checkManufacturer("qiku")
                || RomUtils.checkManufacturer("360");
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
            return new KQihu360OverlayPageLauncher();
        }
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
