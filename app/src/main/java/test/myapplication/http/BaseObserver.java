package test.myapplication.http;

import android.support.annotation.NonNull;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import test.myapplication.http.Bean.BaseResponse;
import test.myapplication.http.exception.ApiException;
import test.myapplication.http.exception.ExceptionFactory;

import static test.myapplication.app.EasyApplication.showToast;

public abstract class BaseObserver<T extends BaseResponse> implements Observer<T> {

    private Toast mToast;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(@NonNull T t) {
        //这部分因为不同的后台处理逻辑不一样就没直接处理
        //使用者可以根据自己的需求进行定制即可
        if (t.isOk()) {
            doOnNext(t);
        }else {
            //自行处理常见异常  异常分为两类，一类是可以自行处理，另一类不做处理。
            ExceptionFactory.thorwException(t.getCode());
        }
    }

    protected abstract void doOnNext(T t);

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof SocketTimeoutException) {
            setError(ApiException.errorMsg_SocketTimeoutException);
        } else if (e instanceof ConnectException) {
            setError(ApiException.errorMsg_ConnectException);
        } else if (e instanceof UnknownHostException) {
            setError(ApiException.errorMsg_UnknownHostException);
        } else {
            String error = e.getMessage();
            showToast(error);
        }
    }



    @Override
    public void onComplete() {
    }


    private void setError(String errorMsg) {
        showToast(errorMsg);
    }
}