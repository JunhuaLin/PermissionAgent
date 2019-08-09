package cn.junhua.android.permission.rom;


import cn.junhua.android.permission.rom.base.DefaultPageLauncher;
import cn.junhua.android.permission.rom.base.PageLauncherProxy;

/**
 * 不同机型不同页面的抽象工厂<br/>
 * 如果对应创建Launcher返回null或者没有成功启动对应页面，<br/>
 * 则使用{@link DefaultPageLauncher}，<br/>
 * 代理逻辑见{@link PageLauncherProxy}<br/>
 *
 * @author junhua.lin<br />
 * CREATED 2019/6/19 10:53
 */
public interface RomPageLauncherFactory {
    /**
     * 检测Rom
     */
    boolean check();

    /**
     * 创建安装未知apk权限操作页面<br/>
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    PageLauncher createInstallLauncher();

    /**
     * 推送通知权限操作页面
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    PageLauncher createNotifyLauncher();

    /**
     * 浮窗权限操作页面
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    PageLauncher createOverlayLauncher();

    /**
     * 创建安装未知apk权限操作页面
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    PageLauncher createWriteSettingsLauncher();

    /**
     * 跳转到应用详情页
     *
     * @return PageLauncher 如果对应创建Launcher返回null，则使用{@link DefaultPageLauncher}
     */
    PageLauncher createAppDetailLauncher();
}
