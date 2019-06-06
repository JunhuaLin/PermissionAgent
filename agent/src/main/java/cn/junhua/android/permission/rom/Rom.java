package cn.junhua.android.permission.rom;

import android.text.TextUtils;

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
            if (RomUtils.checkManufacturer("xiaomi")) {
                return true;
            }
            return !TextUtils.isEmpty(RomUtils.getSystemProperty("ro.miui.ui.version.name"));
        }
    }),
    Vivo(new RomChecker() {
        @Override
        public boolean check() {
            return RomUtils.checkManufacturer("vivo");
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
            if (RomUtils.checkManufacturer("meizu")) {
                return true;
            }
            String meizuFlymeOSFlag = RomUtils.getSystemProperty("ro.build.display.id");
            return meizuFlymeOSFlag.toLowerCase().contains("flyme");
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
