package com.example.administrator.android_lab3;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/25.
         */

public class Goods implements Serializable {
    String name;//商品名
    String price;//价格
    String type;//显示附加信息的类型
    String infomation;//显示的具体的附加信息
    int id;//对应的图片id
    boolean haveCollected;//是否被收藏了
    Goods(String _name, String _price, String _type, String _imfomation, int _id)
    {//构造函数
        name = _name;
        price = _price;
        type = _type;
        infomation = _imfomation;
        id = _id;
        haveCollected = false;
    }
}
