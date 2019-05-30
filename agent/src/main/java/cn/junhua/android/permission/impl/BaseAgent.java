package cn.junhua.android.permission.impl;

import android.os.Handler;
import android.os.Looper;

import cn.junhua.android.permission.agent.Agent;
import cn.junhua.android.permission.agent.AgentExecutor;
import cn.junhua.android.permission.agent.callback.OnDeniedCallback;
import cn.junhua.android.permission.agent.callback.OnGrantedCallback;
import cn.junhua.android.permission.agent.callback.OnRationaleCallback;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/26 19:18
 */
public abstract class BaseAgent<T> implements Agent<T> {
    private static final int REQUEST_CODE = 0x1226;
    protected OnGrantedCallback<T> mOnGrantedCallback;
    protected OnDeniedCallback<T> mOnDeniedCallback;
    protected OnRationaleCallback<T> mOnRationaleCallback;
    protected Handler mHandler = new Handler(Looper.getMainLooper());

    protected int mRequestCode = REQUEST_CODE;

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

    protected void dispatchGranted(T permissions) {
        if (this.mOnGrantedCallback != null) {
            this.mOnGrantedCallback.onGranted(permissions);
        }
    }

    protected void dispatchDenied(T permissions) {
        if (this.mOnDeniedCallback != null) {
            this.mOnDeniedCallback.onDenied(permissions);
        }
    }

    protected void dispatchRationale(T permissions, AgentExecutor executor) {
        if (this.mOnRationaleCallback != null) {
            this.mOnRationaleCallback.onRationale(permissions, executor);
        }
    }

}
