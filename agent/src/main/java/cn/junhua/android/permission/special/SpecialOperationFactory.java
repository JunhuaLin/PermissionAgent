package cn.junhua.android.permission.special;

/**
 * 特殊权限操作工厂，用有创建不同版本的特殊权限操作实例
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/5/29 10:35
 */
public interface SpecialOperationFactory {

    SpecialOperation create();

}
