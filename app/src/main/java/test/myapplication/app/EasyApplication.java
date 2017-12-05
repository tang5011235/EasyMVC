package test.myapplication.app;

import android.app.Application;
import android.widget.Toast;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import test.myapplication.http.HttpClient;
import test.myapplication.http.interceoter.RequestInterceptor;
import test.myapplication.http.interfaces.GlobalHttpHandler;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/11/27.
 */

public class EasyApplication extends Application {
    public static EasyApplication mEasyApplication;

    @Override
    public void onCreate() {

        super.onCreate();
        initTimber();
        mEasyApplication = this;
    }


    public void initHtpp() {
        HttpClient.getInstance().getBuilder()
                .addInterceptor(new RequestInterceptor(new GlobalHttpHandler() {
                    @Override
                    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {

                        return response;
                    }

                    @Override
                    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
                        return request;
                    }
                }, RequestInterceptor.Level.ALL))
                .build();
    }


    private static Toast mToast;

    /**
     * Toast提示
     *
     * @param msg 提示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(EasyApplication.mEasyApplication, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }


    //
    private void initTimber() {
        if (test.myapplication.BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    Timber.d(tag, message);
                }
            });
        }
    }
}
