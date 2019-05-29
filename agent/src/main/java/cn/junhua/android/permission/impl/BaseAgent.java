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
public abstract class BaseAgent implements Agent {
    public static final int REQUEST_CODE = 0x123;
    protected OnGrantedCallback mOnGrantedCallback;
    protected OnDeniedCallback mOnDeniedCallback;
    protected OnRationaleCallback mOnRationaleCallback;
    protected Handler mHandler = new Handler(Looper.getMainLooper());

    protected int mRequestCode = REQUEST_CODE;

    @Override
    public Agent code(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    @Override
    public Agent onGranted(OnGrantedCallback onGrantedCallback) {
        this.mOnGrantedCallback = onGrantedCallback;
        return this;
    }

    @Override
    public Agent onDenied(OnDeniedCallback onDeniedCallback) {
        this.mOnDeniedCallback = onDeniedCallback;
        return this;
    }

    @Override
    public Agent onRationale(OnRationaleCallback rationale) {
        this.mOnRationaleCallback = rationale;
        return this;
    }

    protected void dispatchGranted(String[] permissions) {
        if (this.mOnGrantedCallback != null) {
            this.mOnGrantedCallback.onGranted(permissions);
        }
    }

    protected void dispatchDenied(String[] permissions) {
        if (this.mOnDeniedCallback != null) {
            this.mOnDeniedCallback.onDenied(permissions);
        }
    }

    protected void dispatchRationale(String[] permissions, AgentExecutor executor) {
        if (this.mOnRationaleCallback != null) {
            this.mOnRationaleCallback.onRationale(permissions, executor);
        }
    }

}
