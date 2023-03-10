package com.digua.core.utils;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by yujia
 * on 2017/12/6.
 *
 * 快速回到RecyclerView 的顶部,不会出现卡顿
 */

public class FastScrollManger extends LinearLayoutManager {

    public FastScrollManger(Context context) {
        super(context);
    }

    public FastScrollManger(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return FastScrollManger.this.computeScrollVectorForPosition(targetPosition);
            }

            //控制速度。
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return super.calculateSpeedPerPixel(displayMetrics);
            }

            @Override
            protected int calculateTimeForScrolling(int dx) {
                if (dx > 3000) {
                    dx = 3000;
                }

                int time = super.calculateTimeForScrolling(dx);
                return time;
            }
        };

        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    /**
     * RecyclerView内部BUG
     *
     * 需要加入try catch
     *
     * java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid item position
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
