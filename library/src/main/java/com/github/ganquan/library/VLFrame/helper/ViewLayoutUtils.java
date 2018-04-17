package com.github.ganquan.library.VLFrame.helper;


import com.github.ganquan.library.VLFrame.itemview.BaseItemView;
import com.github.ganquan.library.VLFrame.listview.BaseViewHolder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * @author GanQuan
 * @since 2018/3/18.
 */

public class ViewLayoutUtils {

    public static int bindLayoutFor(FragmentActivity activity) {
        return bindLayoutFor(activity.getClass());
    }

    public static int bindLayoutFor(Fragment fragment) {
        return bindLayoutFor(fragment.getClass());
    }

    public static int bindLayoutFor(BaseItemView view) {
        return bindLayoutFor(view.getClass());
    }

    public static <T> int bindLayoutFor(BaseViewHolder<T> viewHolder) {
        return bindLayoutFor(viewHolder.getClass());
    }

    public static int bindLayoutFor(Class clazz) {
        return BindLayoutMapping.getLayoutId(clazz);
    }
}
