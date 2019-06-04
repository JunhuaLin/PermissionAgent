package cn.junhua.android.permission.utils;

/**
 * 扁平化异常捕获操作
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/4 9:39
 */
public class ExceptionFlat {

    private ExceptionFlat() {

    }

    public static ExceptionFlat create() {
        return new ExceptionFlat();
    }

    public ExceptionFlat onCatch(Action action) {
        try {
            action.onAction();
        } catch (Exception e) {
            e.printStackTrace();
            return new ExceptionFlat();
        }
        return new NullExceptionFlat();
    }

    public interface Action {
        void onAction();
    }

    class NullExceptionFlat extends ExceptionFlat {
        @Override
        public ExceptionFlat onCatch(Action action) {
            return new NullExceptionFlat();
        }
    }
}
