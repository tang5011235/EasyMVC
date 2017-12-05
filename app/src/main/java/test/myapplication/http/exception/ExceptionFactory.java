package test.myapplication.http.exception;

import test.myapplication.app.EasyApplication;

/**
 * Created by Administrator on 2017/12/4.
 */

public class ExceptionFactory {

    public static void thorwException(int code) {
        if (Exceptions.getInstance().getWhiteException().containsKey(code)) {
            //如果在白名单里面则自行操作
            handleException();
        } else {
            EasyApplication.showToast(Exceptions.getInstance().getBlackException().get(code));
        }
    }

    private static void handleException() {

    }
}
