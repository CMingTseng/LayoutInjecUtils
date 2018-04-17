package com.github.ganquan.library.VLFrame.recycleview;

import java.text.ParseException;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

/**
 * @author GanQuan
 * @since 2018/3/23.
 */
public abstract class BaseRecycleViewHolder<T> extends RecyclerView.ViewHolder {
    BaseRecyclerViewAdapter<T> mAdapter;

    public BaseRecyclerViewAdapter<T> getAdapter() {
        return mAdapter;
    }

    public Context getContext() {
        return itemView.getContext();
    }

    public BaseRecycleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    /**
     * 设置该viewHolder上数据
     *
     * @param bean
     * @param position
     * @param context
     */
    protected abstract void bindView(T bean, int position, Context context) throws ParseException;

}
