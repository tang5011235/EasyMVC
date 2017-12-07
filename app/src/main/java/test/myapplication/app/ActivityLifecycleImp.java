package test.myapplication.app;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import test.myapplication.R;

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
        if (activity.findViewById(R.id.toolbar) != null) {
            if (activity instanceof AppCompatActivity) {
                ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
                    activity.getActionBar().setDisplayShowTitleEnabled(false);
                }
            }
        }
        if (activity.findViewById(R.id.toolbar_title) != null) {
            ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
        }
        if (activity.findViewById(R.id.toolbar_back) != null) {
            activity.findViewById(R.id.toolbar_back).setOnClickListener(v -> {
                activity.onBackPressed();
                Toast.makeText(activity, "点击回退", Toast.LENGTH_SHORT).show();
            });
        }

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
