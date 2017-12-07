package test.myapplication.ui.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.myapplication.R;
import test.myapplication.http.Bean.FuLiBean;

/**
 * Created by Administrator on 2017/12/7.
 */

public class RvAdapter extends Adapter {
    private List<FuLiBean> mFuLiBeen;

    public RvAdapter(List<FuLiBean> fuLiBeen) {
        mFuLiBeen = fuLiBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_rv, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((MyViewHolder)holder).mTextView.setText(mFuLiBeen.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        if (mFuLiBeen == null) {
            return 0;
        }
        return mFuLiBeen.size();
    }

    public static class MyViewHolder extends ViewHolder{

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
        }

    }
}
