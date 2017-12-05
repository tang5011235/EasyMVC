package test.myapplication.app;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/27.
 */

public class EasyApplication extends Application {
    public static EasyApplication mEasyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mEasyApplication = this;
    }

    public static EasyApplication getApp() {
        return mEasyApplication;
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
}
