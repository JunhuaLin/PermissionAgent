package cn.junhua.android.permission.impl;

import cn.junhua.android.permission.agent.Agent;
import cn.junhua.android.permission.agent.AgentExecutor;
import cn.junhua.android.permission.agent.callback.OnDeniedCallback;
import cn.junhua.android.permission.agent.callback.OnGrantedCallback;
import cn.junhua.android.permission.agent.callback.OnRationaleCallback;
import cn.junhua.android.permission.utils.Executor;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/26 19:18
 */
public abstract class BaseAgent<T> implements Agent<T>, AgentExecutor {
    private static final int REQUEST_CODE = 0x1226;

    protected int mRequestCode = REQUEST_CODE;
    private Executor mExecutor;

    private OnGrantedCallback<T> mOnGrantedCallback;
    private OnDeniedCallback<T> mOnDeniedCallback;
    //默认直接请求权限
    private OnRationaleCallback<T> mOnRationaleCallback = new OnRationaleCallback<T>() {
        @Override
        public void onRationale(T permissions, AgentExecutor executor) {
            executor.execute();
        }
    };

    public BaseAgent(Executor executor) {
        mExecutor = executor;
    }

    @Override
    public Agent<T> code(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    @Override
    public Agent<T> onGranted(OnGrantedCallback<T> onGrantedCallback) {
        this.mOnGrantedCallback = onGrantedCallback;
        return this;
    }

    @Override
    public Agent<T> onDenied(OnDeniedCallback<T> onDeniedCallback) {
        this.mOnDeniedCallback = onDeniedCallback;
        return this;
    }

    @Override
    public Agent<T> onRationale(OnRationaleCallback<T> rationale) {
        this.mOnRationaleCallback = rationale;
        return this;
    }

    protected void dispatchGranted(final T permissions) {
        if (mOnGrantedCallback != null) {
            post(new Runnable() {
                @Override
                public void run() {
                    mOnGrantedCallback.onGranted(permissions);
                }
            });
        }
    }

    protected void dispatchDenied(final T permissions) {
        if (mOnDeniedCallback != null) {
            post(new Runnable() {
                @Override
                public void run() {
                    mOnDeniedCallback.onDenied(permissions);
                }
            });
        }
    }

    protected void dispatchRationale(final T permissions, final AgentExecutor executor) {
        if (mOnRationaleCallback != null) {
            post(new Runnable() {
                @Override
                public void run() {
                    mOnRationaleCallback.onRationale(permissions, executor);
                }
            });
        }
    }

    protected void post(Runnable runnable) {
        mExecutor.post(runnable);
    }

    protected void asyncPost(Runnable runnable) {
        mExecutor.asyncPost(runnable);
    }

}
