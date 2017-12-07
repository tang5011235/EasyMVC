package test.myapplication.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;

/**
 * Created by Administrator on 2017/11/29.
 */

public class CacheIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        if (chain.request().method().equals("GET")) {
            response = ((RealInterceptorChain) chain).proceed(request);
            return response;
        }
        return null;
    }
}
