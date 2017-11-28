package test.myapplication.http;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/11/27.
 */

public class HttpClient {
    private static HttpClient instance = null;
    private OkHttpClient.Builder mBuilder = null;
    public HttpClient() {
        mBuilder = new OkHttpClient.Builder();
    }

    public static HttpClient getInstance() {
        synchronized (HttpClient.class) {
            if (instance == null) {
                instance = new HttpClient();
            }
        }
        return instance;
    }

    public OkHttpClient.Builder getBuilder(){
        return mBuilder;
    }
}
