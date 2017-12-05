package test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import test.myapplication.http.BaseObserver;
import test.myapplication.http.Bean.FuLiBean;
import test.myapplication.http.Bean.GankBaseResponse;
import test.myapplication.http.HttpClient;
import test.myapplication.http.RetrofitClient;
import test.myapplication.http.interceoter.RequestInterceptor;
import test.myapplication.http.interfaces.ApiService;
import test.myapplication.http.interfaces.GlobalHttpHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                .addInterceptor()
                .addNetworkInterceptor()
                .connectTimeout()
                .writeTimeout()
                .readTimeout()
                .retryOnConnectionFailure();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("123")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okBuilder.build());

        builder.build().create(ApiService.class)
                .getFuLi()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //执行相关的ui操作
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<String>>() {
                    @Override
                    public void accept(BaseResponse<String> stringBaseResponse) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });*/

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
        RetrofitClient.getInstance().create("http://gank.io/api/", ApiService.class)
                .getFuLi()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<GankBaseResponse<List<FuLiBean>>>() {
                    @Override
                    protected void doOnNext(GankBaseResponse<List<FuLiBean>> gankBaseResponse) {
                        if (gankBaseResponse.isOk()) {
                            for (FuLiBean fuLiBean : gankBaseResponse.getResults()) {
                                System.out.println(fuLiBean);
                            }
                        }
                    }
                });
    }
}
