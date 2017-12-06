package test.myapplication.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import test.myapplication.ui.activity.interfaces.IViewProcess;

/**
 * Created by Administrator on 2017/12/6.
 */

public abstract class BaseActivity extends AppCompatActivity implements IViewProcess {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private int mRootViewId;
    private View mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootViewId = setRootViewId();
        if (mRootViewId == 0) {
            throw new IllegalArgumentException("rootViewId can not be set null");
        }else {
            ViewGroup contentParent = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
            contentParent.removeAllViews();
            mRootView = LayoutInflater.from(this).inflate(mRootViewId, null);
            setContentView(mRootView);
            initView(savedInstanceState);
        }
    }

    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    /**
     * 断流Rxjava上游数据，防止内存泄露
     *
     * @param disposable
     */
    protected void addToDisposableCollectin(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public abstract
    @LayoutRes
    int setRootViewId();
}
