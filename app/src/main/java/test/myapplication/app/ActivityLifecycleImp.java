package test.myapplication.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ActivityLifecycleImp implements Application.ActivityLifecycleCallbacks {

    private Unbinder mUnbinder;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        /**
         * 初始化ButterKnife
         */
        mUnbinder = ButterKnife.bind(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mUnbinder.unbind();
    }


}
