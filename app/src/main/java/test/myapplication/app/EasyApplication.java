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

public class EasyApplication extends Application implements GlobalHttpHandler {
    public static EasyApplication mEasyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mEasyApplication = this;
        initTimber();
        initHtpp();
    }

    /**
     * 初始化Okttp网络配置
     *
     */
    public void initHtpp() {
        HttpClient.getInstance().getBuilder()
                .addInterceptor(new RequestInterceptor(this, RequestInterceptor.Level.ALL))
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


    //初始化日志打印Timber
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

    /**
     * Okttp网络响应 过滤
     * @param httpResult
     * @param chain
     * @param response
     * @return
     */
    @Override
    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
        //可以执行一些网络请求操作，比如cookie的存储
        return response;
    }

    /**
     * okttp网络请求过滤
     * @param chain
     * @param request
     * @return
     */
    @Override
    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
        //可以执行一些网络请求操作，比如请求前添加coockie
        return request;
    }
}
