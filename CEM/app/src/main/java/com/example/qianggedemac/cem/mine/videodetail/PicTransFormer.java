package com.example.qianggedemac.cem.mine.videodetail;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by qianggedemac on 17/1/6.
 * 判断图片位置,给相应的大小
 * viewPager的切换动画效果
 */

public class PicTransFormer implements ViewPager.PageTransformer{
    private static final float MAX_SCALE = 1.4f;
    private static final float MIN_SCALE = 0.8f;
    @Override
    public void transformPage(View page, float position) {
        //viewpager预加载机制因为显示的图片是第0,1,2这三个位置,所以这么判断
        if (position >= -1 && position <= 1){
            //abs绝对值
            float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }else{
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
