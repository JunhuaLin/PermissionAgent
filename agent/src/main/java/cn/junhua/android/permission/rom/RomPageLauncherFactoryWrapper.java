package cn.junhua.android.permission.rom;


import cn.junhua.android.permission.rom.base.DefaultPageLauncher;

/**
 * 包装类，简化实现
 *
 * @author junhua.lin<br />
 * CREATED 2019/6/19 10:53
 */
public abstract class RomPageLauncherFactoryWrapper implements RomPageLauncherFactory {
    /**
     * 检测Rom
     */
    public abstract boolean check();

    /**
     * 创建安装未知apk权限操作页面<br/>
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    public PageLauncher createInstallLauncher() {
        return null;
    }

    /**
     * 推送通知权限操作页面
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    public PageLauncher createNotifyLauncher() {
        return null;
    }

    /**
     * 浮窗权限操作页面
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    public PageLauncher createOverlayLauncher() {
        return null;
    }

    /**
     * 创建安装未知apk权限操作页面
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    public PageLauncher createWriteSettingsLauncher() {
        return null;
    }

    /**
     * 跳转到应用详情页
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    public PageLauncher createAppDetailLauncher() {
        return null;
    }
}
