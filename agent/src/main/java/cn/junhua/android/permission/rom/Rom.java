package cn.junhua.android.permission.rom;

import cn.junhua.android.permission.agent.check.RomChecker;
import cn.junhua.android.permission.utils.RomUtils;

/**
 * 手机厂商Rom枚举,统一检测入口,便于扩展
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/6 10:32
 */
public enum Rom implements RomChecker {
    Huawei(new RomChecker() {
        @Override
        public boolean check() {
            return RomUtils.checkManufacturer("huawei");
        }
    }),
    Xiaomi(new RomChecker() {
        @Override
        public boolean check() {
            return RomUtils.checkManufacturer("xiaomi")
                    || RomUtils.checkSystemProperty("ro.miui.ui.version.name")
                    || RomUtils.checkSystemProperty("ro.miui.internal.storage")
                    || RomUtils.checkSystemProperty("ro.miui.ui.version.code");
        }
    }),
    Vivo(new RomChecker() {
        @Override
        public boolean check() {
            return RomUtils.checkManufacturer("vivo")
                    || RomUtils.checkSystemProperty("ro.vivo.os.name")
                    || RomUtils.checkSystemProperty("ro.vivo.os.version");
        }
    }),
    Oppo(new RomChecker() {
        @Override
        public boolean check() {
            return RomUtils.checkManufacturer("oppo");
        }
    }),
    Meizu(new RomChecker() {
        @Override
        public boolean check() {
            return RomUtils.checkManufacturer("meizu")
                    || RomUtils.checkSystemProperty("ro.build.display.id", "flyme")
                    || RomUtils.checkSystemProperty("ro.flyme.published");
        }
    }),
    Qihu360(new RomChecker() {
        @Override
        public boolean check() {
            return RomUtils.checkManufacturer("qiku")
                    || RomUtils.checkManufacturer("360");
        }
    }),
    ;

    private RomChecker mRomChecker;

    Rom(RomChecker romChecker) {
        mRomChecker = romChecker;
    }

    @Override
    public boolean check() {
        return mRomChecker.check();
    }

}
