package com.github.ganquan.library.VLFrame.listview;

import java.util.ArrayList;
import java.util.List;

import com.github.ganquan.library.VLFrame.helper.ViewLayoutUtils;
import com.github.ganquan.library.VLFrame.utils.ReflectUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import butterknife.ButterKnife;

/**
 * @author GanQuan
 */
public class ListAdapterImpl<T> extends BaseAdapter {
    /**
     * viewHolder and layout bundle list
     */
    private List<Class<? extends BaseViewHolder<T>>> mViewBundles = new ArrayList<>();

    private Context mContext;

    private BaseListAdapter<T> mAdapter;

    ListAdapterImpl(Context context, BaseListAdapter<T> adapter) {
        mContext = context;
        mAdapter = adapter;

    }

    /**
     * set data for viewHolder by viewType(through by getItemViewType)
     *
     * @param position
     * @param bean
     * @param baseViewHolder
     */
    private void onSetViewHolder(int position, T bean, BaseViewHolder<T> baseViewHolder) {
        baseViewHolder.mAdapter = mAdapter;
        baseViewHolder.bindView(bean, position, mContext);

    }

    private View createView(BaseViewHolder<T> viewHolder, ViewGroup convertView) {

        View view = LayoutInflater.from(mContext)
                .inflate(ViewLayoutUtils.bindLayoutFor(viewHolder), convertView, false);
        ButterKnife.bind(viewHolder, view);
        view.setTag(viewHolder);
        return view;
    }

    private BaseViewHolder<T> onCreateViewHolder(int pos, List<Class<? extends BaseViewHolder<T>>> clazzList) {
        Class clazz = clazzList.get(mAdapter.getItemViewType(pos));
        return (BaseViewHolder<T>) ReflectUtils.reflect(clazz).newInstance().get();
    }

    /**
     * get bundle list contains viewHolder and layoutId
     *
     * @return
     */
    List<Class<? extends BaseViewHolder<T>>> getViewBundles() {
        return getBindViewHolderList(mViewBundles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder<T> viewHolder;
        if (convertView == null) {
            viewHolder = onCreateViewHolder(position, getViewBundles());
            convertView = createView(viewHolder, (ViewGroup) convertView);
        } else {
            viewHolder = (BaseViewHolder<T>) convertView.getTag();
        }
        if (convertView == null || convertView.getTag() == null) {
            throw new NullPointerException("create view fails");
        }
        onSetViewHolder(position, mAdapter.getItem(position), viewHolder
        );
        return convertView;
    }

    private List<Class<? extends BaseViewHolder<T>>> getBindViewHolderList(
            List<Class<? extends BaseViewHolder<T>>> list) {
        if (list.size() > 0) {
            //do nothing
        } else {
            mAdapter.onBindViewHolder(list);
        }
        return list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public T getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

}
