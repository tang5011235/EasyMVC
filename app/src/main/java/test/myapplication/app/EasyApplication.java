package test.myapplication.app;

import android.app.Application;

/**
 * Created by Administrator on 2017/11/27.
 */

public class EasyApplication extends Application {
    public static EasyApplication mEasyApplication = getInstance();

    public static EasyApplication getInstance() {
        synchronized (EasyApplication.class) {
            if (mEasyApplication == null) {
                mEasyApplication = new EasyApplication();
            }
        }
        return mEasyApplication;
    }


}
