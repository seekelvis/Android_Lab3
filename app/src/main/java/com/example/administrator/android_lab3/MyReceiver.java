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

public class MyReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("RECOMMEND_GOODS")){
            Goods goods = (Goods) intent.getSerializableExtra("recommendGood");
            NotificationManager manager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),goods.id);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentText("新商品热卖")
                    .setContentText(goods.name + "仅售" + goods.price + "!")
                    .setTicker("双十一：商品推荐")
                    .setLargeIcon(bm)
                    .setSmallIcon(goods.id)
                    .setAutoCancel(true);

            Intent mIntent = new Intent(context, InformationActivity.class);
            mIntent.putExtra("goods",goods);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context,0,mIntent,0);
            builder.setContentIntent(mPendingIntent);
            Notification notify = builder.build();
            manager.notify(0,notify);
        }

    }
}
