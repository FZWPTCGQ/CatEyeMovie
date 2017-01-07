package com.example.qianggedemac.cem.film.city;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by qianggedemac on 17/1/6.
 */

public class SideBar extends View {
    //右边那个提示悬浮框
    private static final String[] CHARACTERS = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String lastHint = "";
    //字母的区域,Rect画矩形的参数
    private Rect mTextRect;
    //字母的画笔,用来画矩形的画笔
    private Paint mCharacterPaint;
    //sideBar的高度
    private int height;
    //sideBar的宽度
    private int width;
    //cellHeight 每个字母的高度
    private float cellHeight;

    //手指触摸屏幕的x,y,因为是上下滑动,所以只定义Y就行了(当前手指触摸的位置和最开始#的距离)
    private float touchY;
    //定义接口
    private OnMoveSideBar mOnMoveSideBar;

    public void setOnMoveSideBar(OnMoveSideBar onMoveSideBar) {
        mOnMoveSideBar = onMoveSideBar;
    }

    /**
     * 这个方法是在java代码创建视图的时候被调用,如果是在XML填充的视图,就不会调用这个
     * @param context
     */
    public SideBar(Context context) {
        super(context);
        initView();
    }

    /**
     * 这个方法是在XML创建的,但是没有制定style时调用的
     * @param context
     * @param attrs
     */
    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 这个方法是在XML文件创建的,并且指定了style
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView(){
        mTextRect = new Rect();
        mCharacterPaint = new Paint();
        //设置抗锯齿
        mCharacterPaint.setAntiAlias(true);
        //设置画笔颜色
        mCharacterPaint.setColor(0xff3c3f41);
    }

    /**
     * onLayout方法是ViewGroup中子View的布局方法,用于放置子View的位置
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed){
            //在这里测量sideBar的高度和宽度
            width = getMeasuredWidth();
            height = getMeasuredHeight();
            //sideBar 的高度除以需要显示字母的个数,就是每个字母显示的高度
            //*1.0f是要让其变成浮点型
            cellHeight = height * 1.0f/CHARACTERS.length;
            //根据SideBar的宽度和高度显示每个字母的高度,确定绘制字母的大小
            // 处理的好处是,对于不同的分辨率,文字大小随之改变
            //sideBar的宽度与字母的宽度做对比,选小的
            int textSize = (int) ((width > cellHeight ? cellHeight : width) * (3 * 1.0f / 4));
            mCharacterPaint.setTextSize(textSize);

        }
    }

    public interface OnMoveSideBar{
        /**
         * 参数分别是
         * @param cellHeight:字母高度
         * @param character:当前字母
         * @param index:当前行数
         */
        //正在移动时
        void onMove(float cellHeight, String character, int index);
        //当手指按下时
        void onKeyDown(float cellHeight, String character, int index);
        //当手指离开时
        void onKeyUp(float cellHeight, String character, int index);
    }


    //绘制sideBar 上边的字母
    private void drawCharacter(Canvas canvas){
        for (int i = 0; i < CHARACTERS.length; i++) {
            //获取CHARACTERS的每一个字符串
            String s = CHARACTERS[i];
            /**
             * 参数1:绘制的字符串
             * 参数2:从起始位置
             * 参数3:到结束位置
             * 参数4:绘制矩形
             */
            mCharacterPaint.getTextBounds(s,0,s.length(),mTextRect);
            //将字母的宽和高的值放在mTextRect中
            canvas.drawText(s,(width - mTextRect.width()) / 2f,cellHeight * i + (cellHeight + mTextRect.height()) / 2f,mCharacterPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 在绘制方法中调用
         */
        drawCharacter(canvas);
    }

    //根据手指触摸的坐标,获取当前选择的字母
    private String getHint(){
        int index = (int) ((int)touchY/cellHeight);
        if (index >= 0 && index < CHARACTERS.length){
            return CHARACTERS[index];
        }
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchY = event.getY();
        int action = event.getAction();
        int index = (int) ((int)touchY /cellHeight);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(0xffd1d1d1);
                //判断当前按下是否按到字了getHint方法就是获取的字
                if (getHint() == null){
                    break;
                }
                mOnMoveSideBar.onKeyDown(cellHeight,getHint(),index);
                break;
            case MotionEvent.ACTION_MOVE:
                String hint = getHint();
                if (hint == null){
                    break;
                }
                /**
                 * 做判断,如果获取到当前的字段不为空的话
                 */
                if (!hint.equals(lastHint)){
                    lastHint = hint;
                    mOnMoveSideBar.onMove(cellHeight,hint,index);
                }
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(0xfffafafa);
                mOnMoveSideBar.onKeyUp(cellHeight,getHint(),index);
                break;
        }
        return true;

    }
}
