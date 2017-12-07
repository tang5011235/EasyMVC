package test.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import test.myapplication.R;
import test.myapplication.http.BaseObserver;
import test.myapplication.http.Bean.FuLiBean;
import test.myapplication.http.Bean.GankBaseResponse;
import test.myapplication.http.RetrofitClient;
import test.myapplication.http.interfaces.ApiService;
import test.myapplication.ui.activity.base.BaseActivity;
import test.myapplication.ui.activity.progrecess.RxProcess;
import test.myapplication.ui.adapter.RvAdapter;

public class MainActivity extends BaseActivity {

    private List<FuLiBean> mFuLiBeen = new ArrayList<>();
    private RvAdapter mRvAdapter;

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
                        mFuLiBeen.addAll(gankBaseResponse.getResults());
                        mRvAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this,1));
        mRvAdapter = new RvAdapter(mFuLiBeen);
        rv.setAdapter(mRvAdapter);
        loadData();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
