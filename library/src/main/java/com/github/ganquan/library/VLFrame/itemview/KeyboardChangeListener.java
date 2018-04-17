package com.github.ganquan.library.VLFrame.itemview;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * @author GanQuan
 * @since 2018/2/26.
 */
public class KeyboardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = "ListenerHandler";
    private View mContentView;
    private KeyBoardListener mKeyBoardListen;

    public interface KeyBoardListener {
        /**
         * call back
         *
         * @param isShow         true is show else hidden
         * @param keyboardHeight keyboard height
         */
        void onKeyboardChange(boolean isShow, int keyboardHeight);
    }

    public void setKeyBoardListener(KeyBoardListener keyBoardListen) {
        this.mKeyBoardListen = keyBoardListen;
    }

    public KeyboardChangeListener(Activity contextObj) {
        mContentView = findContentView(contextObj);
        if (mContentView != null) {
            addContentTreeObserver();
        }
    }

    private View findContentView(Activity contextObj) {
        return contextObj.findViewById(android.R.id.content);
    }

    private void addContentTreeObserver() {
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private int getKeyboardHeight(View rootView) {
        int softKeyboardHeight;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        softKeyboardHeight = rootView.getBottom() - r.bottom;
        return softKeyboardHeight;
    }

    @Override
    public void onGlobalLayout() {
        int height = getKeyboardHeight(mContentView);
        mKeyBoardListen.onKeyboardChange(height > 0, height);
    }

    public void destroy() {
        if (mContentView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }
}