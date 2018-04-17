package com.github.ganquan.library.VLFrame.itemview;

import com.github.ganquan.library.VLFrame.helper.ViewLayoutUtils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * view包裹类，主要用于快速实例化局部视图，通过@BindLayout去获取res-id
 * <p>
 * 可以直接通过@BindView绑定id
 *
 * @author GanQuan
 */
public abstract class BaseItemView extends FrameLayout implements LifecycleObserver {
    private View mView;

    public BaseItemView(Context context) {
        super(context);
        onCreate(context);
    }

    public BaseItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate(context);
    }

    public BaseItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate(context);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    Unbinder unbinder;

    protected void onCreate(Context context) {
        if (context instanceof LifecycleOwner) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) context;
            lifecycleOwner.getLifecycle().addObserver(this);
        }
        int layoutId = ViewLayoutUtils.bindLayoutFor(this);
        if (layoutId == 0) {
            layoutId = onBindLayoutId();
        }
        mView = LayoutInflater.from(this.getContext()).inflate(layoutId, this, true);
        unbinder = ButterKnife.bind(this, mView);
        onViewCreated(mView);
    }

    public View getView() {
        return this.mView;
    }

    /**
     * 初始化视图完成，此时可以获取contentView的实例
     * <p>
     * 已经ButterKnife.bind(this, view);
     *
     * @param view 当前BaseControlView的View
     */
    protected abstract void onViewCreated(View view);

    /**
     * 处理view销毁的业务逻辑
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void release() {
        onDestroy();
        if (this.getChildCount() > 0) {
            this.removeAllViews();
        }

    }

    protected int onBindLayoutId() {
        return 0;
    }

    /**
     * provide user a chance to release resource
     */
    public void onDestroy() {

    }

}
