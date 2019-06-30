package cn.junhua.android.permission.rom;

import cn.junhua.android.permission.rom.base.WrapperPagerLauncher;
import cn.junhua.android.permission.rom.default0.Default0PageLauncherFactory;
import cn.junhua.android.permission.rom.huawei.HuaweiPageLauncherFactory;
import cn.junhua.android.permission.rom.meizu.MeizuPageLauncherFactory;
import cn.junhua.android.permission.rom.oppo.OppoPageLauncherFactory;
import cn.junhua.android.permission.rom.qihu360.Qihu360PageLauncherFactory;
import cn.junhua.android.permission.rom.vivo.VivoPageLauncherFactory;
import cn.junhua.android.permission.rom.xiaomi.XiaomiPageLauncherFactory;

/**
 * 手机厂商Rom枚举,统一检测入口,便于扩展
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/6 10:32
 */
public enum Rom implements RomPageLauncherFactory {
    Huawei(new HuaweiPageLauncherFactory()),
    Xiaomi(new XiaomiPageLauncherFactory()),
    Vivo(new VivoPageLauncherFactory()),
    Oppo(new OppoPageLauncherFactory()),
    Meizu(new MeizuPageLauncherFactory()),
    Qihu360(new Qihu360PageLauncherFactory()),
    Default(new Default0PageLauncherFactory()),
    ;


    /**
     * 应用期间缓存ROM
     */
    private static Rom CURRENT_ROM;

    private RomPageLauncherFactory mRomPageLauncherFactory;
    private RomPageLauncherFactory mDefault0RomPageLauncherFactory;

    Rom(RomPageLauncherFactory romFactory) {
        mRomPageLauncherFactory = romFactory;
        mDefault0RomPageLauncherFactory = new Default0PageLauncherFactory();
    }

    /**
     * 获取当前平台ROM
     *
     * @return Rom
     */
    public static Rom currentRom() {
        if (CURRENT_ROM == null) {
            for (Rom rom : Rom.values()) {
                if (rom.check()) {
                    CURRENT_ROM = rom;
                    break;
                }
            }
        }
        return CURRENT_ROM;
    }

    @Override
    public boolean check() {
        return mRomPageLauncherFactory.check();
    }

    @Override
    public PageLauncher createInstallLauncher() {
        return new WrapperPagerLauncher(
                mRomPageLauncherFactory.createInstallLauncher(),
                mDefault0RomPageLauncherFactory.createInstallLauncher()
        );
    }

    @Override
    public PageLauncher createNotifyLauncher() {
        return new WrapperPagerLauncher(
                mRomPageLauncherFactory.createNotifyLauncher(),
                mDefault0RomPageLauncherFactory.createNotifyLauncher()
        );
    }

    @Override
    public PageLauncher createOverlayLauncher() {
        return new WrapperPagerLauncher(
                mRomPageLauncherFactory.createOverlayLauncher(),
                mDefault0RomPageLauncherFactory.createOverlayLauncher()
        );
    }

    @Override
    public PageLauncher createWriteSettingsLauncher() {
        return new WrapperPagerLauncher(
                mRomPageLauncherFactory.createWriteSettingsLauncher(),
                mDefault0RomPageLauncherFactory.createWriteSettingsLauncher()
        );
    }

    @Override
    public PageLauncher createAppDetailLauncher() {
        return new WrapperPagerLauncher(
                mRomPageLauncherFactory.createAppDetailLauncher(),
                mDefault0RomPageLauncherFactory.createAppDetailLauncher()
        );
    }
}
