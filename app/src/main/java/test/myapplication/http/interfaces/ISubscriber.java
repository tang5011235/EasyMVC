package test.myapplication.http.interfaces;

import io.reactivex.disposables.Disposable;
import test.myapplication.http.Bean.BaseResponse;

/**
 * Created by Administrator on 2017/11/27.
 */

public interface ISubscriber<T extends BaseResponse> {
    void doOnSubscribe(Disposable d);

    void doOnError(String errorMsg);

    void doOnNext(T t);

    void doOnCompleted();
}
