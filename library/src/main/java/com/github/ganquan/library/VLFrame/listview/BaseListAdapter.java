package com.github.ganquan.library.VLFrame.listview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @param <T> list<T>
 *
 * @author GanQuan
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    private Context mContext;
    /**
     * mlist
     */
    private List<T> list = new ArrayList<>();

    public Context getContext() {
        return mContext;
    }

    /**
     * inner delegate adapter
     */
    private ListAdapterImpl<T> adapter;

    public BaseListAdapter(Context context) {
        this.mContext = context;
        adapter = new ListAdapterImpl<>(context, this);
    }

    /**
     * 初始化list
     *
     * @param list
     */
    public void initList(List<T> list) {
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            this.list.clear();
            notifyDataSetChanged();
            return;
        }
        if (this.list.size() > 0) {
            this.list.clear();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * add the list ,but no clear
     *
     * @param list
     */
    public void addList(List<T> list) {
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            return;
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * clear all list
     */
    public void clearList() {
        this.list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return adapter.getViewBundles().size();
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        return adapter.getView(position, convertView, parent);
    }

    /**
     * adding viewBundle to list,index as viewTypeId,
     *
     * @param viewHolderList the list has been instantiated,so it is no need to instantiate
     *                       list in the function
     */
    protected abstract void onBindViewHolder(List<Class<? extends BaseViewHolder<T>>> viewHolderList);

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
