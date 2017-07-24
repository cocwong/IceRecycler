package com.example.wanglixin.icerecycler;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by wanglixin on 2017/7/21.
 */

public class IceRecyclerView extends RelativeLayout implements IceRecyclerAdapter {
    private RecyclerView recyclerView;
    private int state;
    private int slapHeight = 80;

    public IceRecyclerView(Context context) {
        this(context, null);
    }

    public IceRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IceRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        recyclerView = new RecyclerView(getContext());
        recyclerView.setBackgroundColor(Color.parseColor("#FFD4D7B0"));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        recyclerView.setLayoutParams(params);
        addView(recyclerView);
    }

    float lastY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int childCount = recyclerView.getLayoutManager().getChildCount();
                if (childCount == 0) {
                    float dy = ev.getY() - lastY;
                    if (dy <= 0) {
                        break;
                    }
                    actionScroll(dy);
                    lastY = ev.getY();
                    return true;
                } else {
                    if (canScroll()) {
                        float dy = ev.getY() - lastY;
                        if (getScrollY() == 0 && dy <= 0) {
                            break;
                        }
                        if (getScrollY() < 0 && dy < 0 && getScrollY() + Math.abs(dy) > 0) {
                            dy = getScrollY();
                        }
                        actionScroll(dy);
                        lastY = ev.getY();
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollY() < 0) {
                    reverseScroll();
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void actionScroll(float dy) {
        state = IceRecyclerState.STATE_DRAGGING;
        int y = (int) -dy;
        Log.e("y", y + "");
        scrollBy(0, y);
    }

    private void reverseScroll() {
        scrollTo(0,0);//用scroller滑动
    }

    @Override
    public void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return recyclerView.getAdapter();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return recyclerView.getLayoutManager();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    private boolean canScroll() {
        if (getScrollY() <= 0 && isFirstViewVisible() && isFirstViewTopZero()) {//向下为负
            return true;
        }
        return false;
    }

    public boolean isRefreshing() {
        return state == IceRecyclerState.STATE_REFRESHING;
    }

    private boolean isFirstViewVisible() {
        View view = recyclerView.getLayoutManager().findViewByPosition(0);
        return view != null;
    }

    private boolean isFirstViewTopZero() {
        return recyclerView.getLayoutManager().findViewByPosition(0).getTop() == 0;
    }
}