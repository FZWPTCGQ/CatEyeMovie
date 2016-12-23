package com.example.qianggedemac.cem.film.hot;

import android.content.Context;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by qianggedemac on 16/12/21.
 */

public class MyListView extends PullToRefreshListView {
    public MyListView(Context context) {
        super(context);

    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, Mode mode) {
        super(context, mode);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int hight = MeasureSpec.makeMeasureSpec(1000000000,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
