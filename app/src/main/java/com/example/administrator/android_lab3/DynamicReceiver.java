package com.example.administrator.android_lab3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Created by Administrator on 2017/10/30.
 */

public class DynamicReceiver extends BroadcastReceiver {
    private static final String DYNAMICION = "SHOPPING";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("SHOPPING")){
            Goods goods = (Goods) intent.getSerializableExtra("purchasedGood");
            NotificationManager manager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),goods.id);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentText("马上购买")
                    .setContentText(goods.name + "已添加到购物车！")
                    .setTicker("购买成功")
                    .setLargeIcon(bm)
                    .setSmallIcon(goods.id)
                    .setAutoCancel(true);

            Intent mIntent = new Intent(context, MainActivity.class);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context,0,mIntent,FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);
            Notification notify = builder.build();
            manager.notify(5,notify);
        }

    }
}
