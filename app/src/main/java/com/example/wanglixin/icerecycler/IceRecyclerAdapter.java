package com.example.wanglixin.icerecycler;

import android.support.v7.widget.RecyclerView;

/**
 * Created by wanglixin on 2017/7/24.
 */

public interface IceRecyclerAdapter {
    void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter);

    RecyclerView.Adapter getAdapter();
}
