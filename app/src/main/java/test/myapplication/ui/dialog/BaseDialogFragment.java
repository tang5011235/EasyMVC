package test.myapplication.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Window;

import test.myapplication.ui.dialog.adapter.BaseDialogAdapter;

/**
 * Created by Administrator on 2017/11/30.
 */

public class BaseDialogFragment extends DialogFragment {

    private BaseDialogAdapter mBaseDialogAdapter;

    public BaseDialogFragment(BaseDialogAdapter adapter) {
        mBaseDialogAdapter = adapter;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mBaseDialogAdapter.setDialogFragment(this);
        return mBaseDialogAdapter.setAlertDialogBuilder();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        mBaseDialogAdapter.configWindow(this);
    }


}
