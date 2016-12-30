package com.example.qianggedemac.cem.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qianggedemac.cem.R;

/**
 * Created by dllo on 2016/12/29.
 */

public class ToolbarAlphaBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    private static final String TAG = "ToolbarAlphaBehavior";
    private int offset = 0;
    private int startOffset = 0;
    private int endOffset = 0;
    private Context context;
    private ImageView mReturn;
    private ImageView mCollectIv;
    private TextView titleTv;


    public ToolbarAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar toolbar, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        mCollectIv = (ImageView) coordinatorLayout.findViewById(R.id.collect_iv);

        mReturn = (ImageView) coordinatorLayout.findViewById(R.id.return_iv);
        titleTv = (TextView) coordinatorLayout.findViewById(R.id.return_tv);
        startOffset = 0;
        endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) - toolbar.getHeight();
        offset += dyConsumed;
        if (offset <= startOffset) {  //alpha为0
            toolbar.getBackground().setAlpha(0);
            //移动位置 大于设置的刚开始量 小于设置的刚结束的量
        } else if (offset > startOffset && offset < endOffset) { //alpha为0到255
            float precent = (float) (offset - startOffset) / endOffset;
            int alpha = Math.round(precent * 255);
            toolbar.getBackground().setAlpha(alpha);
//            mReturn.setAlpha(0);


        } else if (offset >= endOffset) {  //alpha为255
            //移动位置 大于设置的量
            toolbar.getBackground().setAlpha(255);

            float precent = (float) (endOffset - startOffset) / endOffset;
            int alpha = Math.round(precent * 255);
            mCollectIv.setAlpha(alpha);
            mReturn.setAlpha(alpha);
            titleTv.setAlpha(alpha);
            titleTv.setText("资讯");
            titleTv.setScaleX(1f);
            titleTv.setPivotX(1f);
        }

    }

}

