package com.example.qianggedemac.cem.tool;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.qianggedemac.cem.mine.QrCodeActivity;

/**
 * Created by dllo on 2016/12/28.
 */

public class GestureHelper implements GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    private OnFlingListener mListener;



    public static abstract class OnFlingListener {
        public abstract void OnFlingLeft();

        public abstract void OnFlingRight();
    }

    public GestureHelper(Context contexxt) {
        mGestureDetector = new GestureDetector(contexxt,this);
    }

    public void setListener(OnFlingListener listener) {
        mListener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e2.getX() - e1.getX() > 30 && Math.abs(velocityX) > 0 && Math.abs(e2.getY() - e1.getY()) < 150) {
            mListener.OnFlingLeft();
        } else if (e1.getX() - e2.getX() > 30 && Math.abs(velocityX) > 0 && (Math.abs(e2.getY() - e1.getY())) < 150) {
            mListener.OnFlingRight();
        }
        return true;
    }
}
