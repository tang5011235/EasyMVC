package test.myapplication.app;

import android.app.Application;
import android.widget.Toast;

import timber.log.Timber;

/**
 * Created by Administrator on 2017/11/27.
 */

public class EasyApplication extends Application {
    public static EasyApplication mEasyApplication;

    @Override
    public void onCreate() {

        super.onCreate();
        initTimber();
        mEasyApplication = this;
    }

    private static Toast mToast;

    /**
     * Toast提示
     *
     * @param msg 提示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(EasyApplication.mEasyApplication, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    private void initTimber(){

        if (test.myapplication.BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    Timber.d(tag, message);
                }
            });
        }
    }
}
