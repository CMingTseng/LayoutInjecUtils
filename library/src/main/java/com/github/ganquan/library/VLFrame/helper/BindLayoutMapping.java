package com.github.ganquan.library.VLFrame.helper;

/**
 * @author GanQuan
 * @date 16/3/26
 */
public class BindLayoutMapping {
    public static int getLayoutId(Class<?> clazz) {
        while (clazz != null) {
            BindLayout m = clazz.getAnnotation(BindLayout.class);
            if (m != null) {
                return m.value();
            }
            clazz = clazz.getSuperclass();
        }

        return 0;

    }

}
