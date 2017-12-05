package test.myapplication.http.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thf on 2017/12/5.
 * 异常类
 */

public class Exceptions {
    private final Map<Integer, String> blackException = new HashMap<>();
    private final Map<Integer, String> whiteException = new HashMap<>();

    private static Exceptions mExceptionConstant = null;

    private Exceptions() {
    }

    public static Exceptions getInstance() {
        synchronized (Exceptions.class) {
            if (mExceptionConstant == null) {
                mExceptionConstant = new Exceptions();
                mExceptionConstant.initBlackException();
                mExceptionConstant.initWhiteExcetption();
            }
        }
        return mExceptionConstant;
    }

    private void initBlackException() {
        addBlackException(500, "服务器繁忙");
        addBlackException(0, "服务器繁忙");
    }

    private void initWhiteExcetption() {
        addWhiteException(202, "123");
    }

    private void addBlackException(int code, String des) {
        blackException.put(code, des);
    }

    private void addWhiteException(int code, String des) {
        whiteException.put(code, des);
    }

    public Map<Integer, String> getBlackException() {
        return blackException;
    }

    public Map<Integer, String> getWhiteException() {
        return whiteException;
    }
}
