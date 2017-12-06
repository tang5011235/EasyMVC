package test.myapplication.ui.dialog.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;

import test.myapplication.ui.dialog.BaseDialogFragment;

/**
 * Created by Administrator on 2017/11/30.
 * 创建界面，bind翘秆
 */

public abstract class BaseDialogAdapter {

    private View mParent;
    protected BaseDialogFragment mBaseDialogFragment;
    private int mGravity;
    private int mStyle;
    private AlertDialog mDialog;


    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * 获取dialog的界面View
     *
     * @return
     */
    public BaseDialogAdapter(Builder builder) {
        this.mGravity = builder.mGravity;
        this.mStyle = builder.mStyle;
        this.mDialog = builder.mDialog;
    }

    public View getContentView(Context context) {
        mParent = View.inflate(context, getLayoutId(), null);
        initView((ViewGroup) mParent);
        return mParent;
    }

    public void setDialogFragment(BaseDialogFragment dialogFragment) {
        mBaseDialogFragment = dialogFragment;
    }

    public abstract void initView(ViewGroup viewGroup);


    //配置dialog
    public void configWindow(BaseDialogFragment dialogFragment) {
        dialogFragment.getDialog().getWindow().setGravity(mGravity == 0 ? 0 : mGravity);
    }

    public AlertDialog setAlertDialogBuilder(AlertDialog dialog) {
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mBaseDialogFragment.getActivity(), mStyle);
            builder.setView(getContentView(builder.getContext()));
            return builder.create();
        } else {
            return dialog;
        }
    }

    public AlertDialog setAlertDialogBuilder() {
        return setAlertDialogBuilder(mDialog);
    }

    public static class Builder {
        private int mGravity;
        private int mStyle;
        private AlertDialog mDialog;


        public Builder() {

        }

        public Builder setGravity(int gravity) {
            this.mGravity = gravity;
            return this;
        }


        public Builder setStyle(int style) {
            mStyle = style;
            return this;
        }

        public Builder setDialog(AlertDialog dialog) {
            mDialog = dialog;
            return this;
        }
    }

}
