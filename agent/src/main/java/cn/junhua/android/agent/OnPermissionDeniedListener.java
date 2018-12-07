package cn.junhua.android.agent;

/**
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2018/12/7 14:51
 */
public interface OnPermissionDeniedListener {
    void onDenied(String[] permissions);
}
