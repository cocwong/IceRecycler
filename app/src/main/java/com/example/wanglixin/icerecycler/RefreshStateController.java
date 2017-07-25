package com.example.wanglixin.icerecycler;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class RefreshStateController implements StateFunction {
    private int state;
    private boolean isRefresh;
    private static RefreshStateController instance;

    private RefreshStateController() {
    }

    public static RefreshStateController getInstance() {
        if (instance == null) {
            synchronized (RefreshStateController.class) {
                if (instance == null) {
                    instance = new RefreshStateController();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean isRefresh() {
        return isRefresh;
    }

    @Override
    public void setRefresh() {
        isRefresh = true;
    }

    @Override
    public void setRefreshComplete(boolean isCompleted) {
        isRefresh = false;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
