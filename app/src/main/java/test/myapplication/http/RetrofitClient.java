package test.myapplication.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/27.
 */

public class RetrofitClient {
    private static RetrofitClient mRetrofitClient = null;
    private OkHttpClient.Builder mOkHttpBuilder = null;
    private Retrofit.Builder mRetrofitBuilder = null;

    public RetrofitClient() {
        mOkHttpBuilder = HttpClient.getInstance().getBuilder();
        mRetrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpBuilder.build());
    }

    public static RetrofitClient getInstance() {
        synchronized (RetrofitClient.class) {
            if (mRetrofitClient == null) {
                mRetrofitClient = new RetrofitClient();
            }
        }
        return mRetrofitClient;
    }

    public Retrofit.Builder getRetrofitBuilder() {
        return mRetrofitBuilder;
    }

    public Retrofit getRetrofit() {
        return mRetrofitBuilder.build();
    }

}
