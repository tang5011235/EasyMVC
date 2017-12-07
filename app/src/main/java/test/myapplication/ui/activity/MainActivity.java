package test.myapplication.ui.activity;

import android.os.Bundle;

import java.util.List;

import test.myapplication.R;
import test.myapplication.http.BaseObserver;
import test.myapplication.http.Bean.FuLiBean;
import test.myapplication.http.Bean.GankBaseResponse;
import test.myapplication.http.RetrofitClient;
import test.myapplication.http.interfaces.ApiService;
import test.myapplication.ui.activity.base.BaseActivity;
import test.myapplication.ui.activity.progrecess.RxProcess;

public class MainActivity extends BaseActivity {

    @Override
    public int setRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void loadData() {
        RetrofitClient.getInstance()
                .create("http://gank.io/api/", ApiService.class)
                .getFuLi()
                .compose(RxProcess.CommonProcess(this))
                .subscribe(new BaseObserver<GankBaseResponse<List<FuLiBean>>>() {
                    @Override
                    protected void doOnNext(GankBaseResponse<List<FuLiBean>> gankBaseResponse) {

                    }
                });

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        loadData();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
