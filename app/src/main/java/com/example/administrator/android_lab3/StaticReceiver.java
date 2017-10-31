package com.example.administrator.android_lab3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Created by Administrator on 2017/10/30.
 */

public class StaticReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("RECOMMEND_GOODS")){
            Goods goods = (Goods) intent.getSerializableExtra("recommendGood");//取出推荐的Goods
            NotificationManager manager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);//获取通知栏管理
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),goods.id);//把图片转化为bitmap
            Notification.Builder builder = new Notification.Builder(context);//实例化通知栏构造器
            //配置builder
            builder.setContentText("新商品热卖")
                    .setContentText(goods.name + "仅售" + goods.price + "!")
                    .setTicker("双十一：商品推荐")
                    .setLargeIcon(bm)
                    .setSmallIcon(R.mipmap.shoplist)
                    .setAutoCancel(true);
            //绑定intent，点击通知能够进入商品详情界面
            Intent mIntent = new Intent(context, InformationActivity.class);
            mIntent.putExtra("goods",goods);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context,0,mIntent,FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);
            ///绑定Notification，发送通知请求
            Notification notify = builder.build();
            manager.notify(0,notify);
        }

    }
}
