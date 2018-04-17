package com.github.ganquan.library.VLFrame.listview;

import android.content.Context;

/**
 * 通过@bindLayout(R.layout.xxx)取xml
 * 通过@bindView
 *
 * @author GanQuan
 * @since 2018/2/8.
 */
public abstract class BaseViewHolder<T> {
    public BaseListAdapter<T> mAdapter;

    /**
     * 默认无参数构造方法，用于反射实例化
     */
    public BaseViewHolder() {

    }

    public Class getClassTag() {
        return this.getClass();
    }

    /**
     * 处理业务逻辑
     *
     * @param bean
     * @param position
     * @param context
     */
    protected abstract void bindView(final T bean, int position,final Context context);

    public BaseListAdapter<T> getAdapter() {
        return mAdapter;
    }
}
