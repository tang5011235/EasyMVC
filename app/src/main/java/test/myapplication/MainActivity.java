package test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.myapplication.http.BaseObserver;
import test.myapplication.http.Bean.FuLiBean;
import test.myapplication.http.Bean.GankBaseResponse;
import test.myapplication.http.RetrofitClient;
import test.myapplication.http.interfaces.ApiService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
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
       /* if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    Timber.d(tag, message);
                }
            });
        }*/

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
