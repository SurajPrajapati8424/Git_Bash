package com.dr.psycho.ryuk.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class NonSwipableViewPage extends ViewPager {
    //Ctrl+O
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    public NonSwipableViewPage(@NonNull Context context) {
        super(context);
        setMyScroll();
    }

    private void setMyScroll() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this,new MyScroller(getContext()));
        }
        catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    public NonSwipableViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setMyScroll();
    }

    private class MyScroller extends Scroller {
        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());

        }

        /**
         * Start scrolling by providing a starting point, the distance to travel,
         * and the duration of the scroll.
         *
         * @param startX   Starting horizontal scroll offset in pixels. Positive
         *                 numbers will scroll the content to the left.
         * @param startY   Starting vertical scroll offset in pixels. Positive numbers
         *                 will scroll the content up.
         * @param dx       Horizontal distance to travel. Positive numbers will scroll the
         *                 content to the left.
         * @param dy       Vertical distance to travel. Positive numbers will scroll the
         *                 content up.
         * @param duration Duration of the scroll in milliseconds.
         */
        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 400);
        }
    }
}
