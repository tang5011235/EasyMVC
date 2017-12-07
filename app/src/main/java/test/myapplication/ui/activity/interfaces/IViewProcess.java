package test.myapplication.ui.activity.interfaces;

import android.os.Bundle;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface IViewProcess {
    void loadData();
    void initView(Bundle savedInstanceState);
    void showProgress();
    void hideProgress();
}
