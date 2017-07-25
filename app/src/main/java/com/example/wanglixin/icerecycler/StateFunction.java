package com.example.wanglixin.icerecycler;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public interface StateFunction {
    boolean isRefresh();
    void setRefresh();
    void setRefreshComplete(boolean isCompleted);
}
