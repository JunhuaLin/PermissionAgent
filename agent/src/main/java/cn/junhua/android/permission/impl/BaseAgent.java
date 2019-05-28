package cn.junhua.android.permission.impl;

import cn.junhua.android.permission.core.Agent;
import cn.junhua.android.permission.core.callback.OnDeniedCallback;
import cn.junhua.android.permission.core.callback.OnGrantedCallback;
import cn.junhua.android.permission.core.callback.OnRationaleCallback;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/26 19:18
 */
public abstract class BaseAgent implements Agent {
    public static final int REQUEST_CODE = 0x123;
    protected OnGrantedCallback mOnGrantedCallback;
    protected OnDeniedCallback mOnDeniedCallback;
    protected OnRationaleCallback mOnRationaleCallback;

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

}
