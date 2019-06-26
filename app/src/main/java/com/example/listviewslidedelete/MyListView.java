package com.example.listviewslidedelete;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MyListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private static final String TAG = "MyListView";
    private GestureDetector gestureDetector;
    private OnDeleteListener listener;
    private boolean isDeleteButtonShown;
    private View deleteButton;
    private ViewGroup itemLayout;
    private int selectedItem;
    
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MyListView: xxxx");
        gestureDetector=new GestureDetector(getContext(),this);
        setOnTouchListener(this);
    }

    /**
     * 设置自定义接口监听
     */
    public void setOnDeleteListener(OnDeleteListener l) {
        Log.d(TAG, "setOnDeleteListener: xxxx");
        listener = l;
    }

    /**
     * View的触摸事件,触摸View后的事件先在此处处理
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d(TAG, "onTouch: xxxx");
        if(isDeleteButtonShown){
            itemLayout.removeView(deleteButton);
            deleteButton=null;
            isDeleteButtonShown=false;
            return false;
        }else {
            //将事件传递给GestureDetector进行处理
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }

    /**
     * GestureDetector当手指按下的时候触发下面的方法
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG, "onDown: xxxx");
        if(!isDeleteButtonShown){
            //获取点击的条目
            selectedItem=pointToPosition((int)motionEvent.getX(),(int)motionEvent.getY());
        }
        return false;
    }

    /**
     * GestureDetector当用户的手指在触摸屏上拖过的时候触发下面的方法,v代表横向上的速度,v1代表纵向上的速度
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG, "onFling: xxxx");
        if(!isDeleteButtonShown && Math.abs(v)>Math.abs(v1)){//左右滑动
            Log.d(TAG, "onFling: xxxxxxxxxxx");
            deleteButton= LayoutInflater.from(getContext()).inflate(R.layout.delete_button,null);//引入删除按钮布局
            deleteButton.setOnClickListener(new OnClickListener() {//设置按钮监听
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onFling onClick: xxxx");
                    itemLayout.removeView(deleteButton);
                    deleteButton=null;
                    isDeleteButtonShown=false;
                    listener.onDelete(selectedItem);
                }

            });
            itemLayout=(ViewGroup)getChildAt(selectedItem-getFirstVisiblePosition());
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            itemLayout.addView(deleteButton,params);//在滑动的行添加删除按钮控件
            isDeleteButtonShown=true;
        }
        return false;
    }

    /**
     * GestureDetector当手指在屏幕上轻轻点击的时候触发下面的方法
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG, "onSingleTapUp: xxxx");
        return false;
    }

    /**
     * GestureDetector当用户手指在屏幕上按下,而且还未移动和松开的时候触发这个方法
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG, "onShowPress: xxxx");
    }

    /**
     * GestureDetector当手指在屏幕上滚动的时候触发这个方法
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG, "onScroll: xxxx");
        return false;
    }

    /**
     * GestureDetector当用户手指在屏幕上长按的时候触发下面的方法
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG, "onLongPress: xxxx");
    }

    /**
     *自定义接口,供覆写
     */
    public interface OnDeleteListener {
        void onDelete(int index);
    }
}
