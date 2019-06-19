package cn.junhua.android.permission.rom.default0;

import android.os.Build;

import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.rom.RomPageLauncherFactory;

/**
 * 存放所有ROM都通用的操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 10:09
 */
public class Default0PageLauncherFactory implements RomPageLauncherFactory {
    @Override
    public boolean check() {
        return true;
    }

    @Override
    public PageLauncher createInstallLauncher() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new OInstallPageLauncher();
        }
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
