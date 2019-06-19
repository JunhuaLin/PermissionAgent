package cn.junhua.android.permission.rom.huawei;

import android.os.Build;

import cn.junhua.android.permission.rom.PageLauncher;
import cn.junhua.android.permission.rom.RomPageLauncherFactory;
import cn.junhua.android.permission.rom.default0.ONotifyPageLauncher;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * 华为手机适配工厂
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/19 10:09
 */
public class HuaweiPageLauncherFactory implements RomPageLauncherFactory {
    @Override
    public boolean check() {
        return RomUtils.checkManufacturer("huawei");
    }

    @Override
    public PageLauncher createInstallLauncher() {
        return null;
    }

    @Override
    public PageLauncher createNotifyLauncher() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ONotifyPageLauncher();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return new KHuaweiNotifyPageLauncher();
        }
        return null;
    }

    @Override
    public PageLauncher createOverlayLauncher() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return new KHuaweiOverlayPageLauncher();
        }
        return null;
    }

    @Override
    public PageLauncher createWriteSettingsLauncher() {
        return null;
    }

    @Override
    public PageLauncher createAppDetailLauncher() {
        return new HuaweiAppDetailPageLauncher();
    }
}
