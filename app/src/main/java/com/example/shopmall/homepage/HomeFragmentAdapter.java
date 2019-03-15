package com.example.shopmall.homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

public class HomeFragmentAdapter extends RecyclerView {

    //广告幅类型
    public static final int BANNER = 0;

    //频道类型
    public static final int CHANNEL = 1;

    //活动类型
    public static final int ACT = 2;

    //秒杀类型
    public static final int SECKILL = 3;

    //推荐类型
    public static final int RECOMMEND = 4;

    //热卖类型
    public static final int HOT = 5;

    //初始化布局
    private LayoutInflater mLayoutInflater;

    //数据
    //..........................................

    private Context mContext;

    //当前类型
    private  int currentType = BANNER;


    public HomeFragmentAdapter(@NonNull Context context) {
        super(context);
    }
}
