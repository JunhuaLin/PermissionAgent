package cn.junhua.android.permission.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程切换工具
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/10 14:28
 */
public class Executor {

    private Handler mHandler;
    private ThreadPoolExecutor mExecutor;

    public Executor() {
        mHandler = new Handler(Looper.getMainLooper());
        mExecutor = new ThreadPoolExecutor(1, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        ;
        mExecutor.allowCoreThreadTimeOut(true);
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public void post(Runnable runnable, long delayMillis) {
        mHandler.postDelayed(runnable, delayMillis);
    }

    public void asyncPost(Runnable runnable) {
        mExecutor.execute(runnable);
    }
}
