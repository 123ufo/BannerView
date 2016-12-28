package com.ufo.libs.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/12/28.
 *
 * 作者：xudiwei
 *
 * 描述：可以点击和监听按下与松下事件的viewpager
 */

public class CanClickViewPager extends ViewPager {

    int down_x;
    int down_y;
    private OnCanClickViewPagerListener mOnCanClickViewPagerListener;
    private OnPressAndUpListener mPressAndUpListener;

    public CanClickViewPager(Context context) {
        super(context);
    }

    public CanClickViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_x = (int) ev.getX();
                down_y = (int) ev.getY();
                if (null != mPressAndUpListener) {
                    mPressAndUpListener.onPress();
                }
                return true;
            case MotionEvent.ACTION_UP:
                int up_x = (int) ev.getX();
                int up_y = (int) ev.getY();
                if (null != mPressAndUpListener) {
                    mPressAndUpListener.onUP();
                }
                if (down_x == up_x && down_y == up_y) {
                    if (null != mOnCanClickViewPagerListener) {
                        mOnCanClickViewPagerListener.onClick(getChildAt(getCurrentItem()), getCurrentItem());
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnCanClickViewPagerListener(OnCanClickViewPagerListener listener) {
        this.mOnCanClickViewPagerListener = listener;
    }

    /**
     * 设置触模按和松事件
     *
     * @param pressAndUpListener
     */
    public void setPressAndUpListener(OnPressAndUpListener pressAndUpListener) {
        mPressAndUpListener = pressAndUpListener;
    }

    public interface OnCanClickViewPagerListener {
        void onClick(View view, int position);
    }

    public interface OnPressAndUpListener {
        void onPress();

        void onUP();
    }
}
