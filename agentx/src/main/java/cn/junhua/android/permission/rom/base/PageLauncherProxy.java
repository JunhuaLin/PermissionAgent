package cn.junhua.android.permission.rom.base;

import cn.junhua.android.permission.agent.PermissionHandler;
import cn.junhua.android.permission.rom.PageLauncher;

/**
 * 代理PageLauncher,组合执行Default0PageLauncher,Default1PageLauncher
 *
 * @author junhua.lin<br />
 * CREATED 2019/6/19 14:01
 */
public class PageLauncherProxy implements PageLauncher {

    private PageLauncher mProxyPageLauncher;
    private PageLauncher mDefault0PageLauncher;
    private PageLauncher mDefault1PageLauncher;

    public PageLauncherProxy(PageLauncher originPageLauncher, PageLauncher default0PageLauncher) {
        mProxyPageLauncher = originPageLauncher;
        mDefault0PageLauncher = default0PageLauncher;
        mDefault1PageLauncher = new DefaultPageLauncher();
    }

    @Override
    public boolean launch(PermissionHandler permissionHandler, int requestCode) {
        //优先使用默认的方式启动，然后使用适配方式启动，最后使用设置页启动
        return (mDefault0PageLauncher != null && mDefault0PageLauncher.launch(permissionHandler, requestCode))
                || (mProxyPageLauncher != null && mProxyPageLauncher.launch(permissionHandler, requestCode))
                || mDefault1PageLauncher.launch(permissionHandler, requestCode);
    }
}
