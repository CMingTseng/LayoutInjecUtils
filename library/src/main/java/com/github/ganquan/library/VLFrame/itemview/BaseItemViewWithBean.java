package com.github.ganquan.library.VLFrame.itemview;

import android.content.Context;
import android.view.View;

/**
 * @author GanQuan
 * @since 2018/2/28.
 */

public abstract class BaseItemViewWithBean<T> extends BaseItemView {
    T mBean;

    public BaseItemViewWithBean(Context context, T data) {
        super(context);
        this.mBean = data;
        onViewCreated(getView(), mBean);
    }

    protected abstract void onViewCreated(View view, T bean);

    @Override
    protected final void onViewCreated(View view) {

    }
}
