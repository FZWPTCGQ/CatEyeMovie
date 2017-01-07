package com.example.qianggedemac.cem.film.city;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * Created by qianggedemac on 17/1/6.
 */

public class ScrollSpeedLinearLayoutManger extends LinearLayoutManager {
    private float MILLISECONDS_PER_INCH = 0.03f;
    private Context mContext;
    public ScrollSpeedLinearLayoutManger(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()){
            @Nullable
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return ScrollSpeedLinearLayoutManger.this.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                //返回滑动一个pixel需要多少毫秒
                return MILLISECONDS_PER_INCH / displayMetrics.density;
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
    public void setSpeedSlow(){
        /**
         * 自己在这里用density去乘,希望不同分辨率设备上的滑动速度相同
         * 0.3是自己估摸的一个值,可以根据不同需求自己修改
         */
        MILLISECONDS_PER_INCH = mContext.getResources().getDisplayMetrics().density * 0.3f;
    }
    public void setSpeedFast(){
        MILLISECONDS_PER_INCH = mContext.getResources().getDisplayMetrics().density * 0.003f;
    }
}
