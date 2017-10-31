package com.example.administrator.android_lab3;

public class MessageEvent{
    Goods goods;
    boolean purchesed;//0 不增加，1 增加
    int view_select;//0 不变， 1 主界面， 2 购物车
    boolean collected_change;//0 不变, 1 变

    MessageEvent(){
        goods = null;
        purchesed = false;//0 不增加，1 增加
        view_select = 0;//0 不变， 1 主界面， 2 购物车
        collected_change = false;
    }
    public void initial(){
        goods = null;
        purchesed = false;//0 不增加，1 增加
        view_select = 0;//0 不变， 1 主界面， 2 购物车
        collected_change = false;
    }

}
