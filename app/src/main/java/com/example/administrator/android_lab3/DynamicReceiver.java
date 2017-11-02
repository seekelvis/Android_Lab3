package com.example.administrator.android_lab3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;

import org.greenrobot.eventbus.EventBus;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Created by Administrator on 2017/10/30.
 */

public class DynamicReceiver extends BroadcastReceiver {
    private static final String DYNAMICION = "SHOPPING";
    public static int notiNO = 10;
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
                    .setSmallIcon(R.mipmap.shoplist)
                    .setAutoCancel(true);

            Intent mIntent = new Intent(context, MainActivity.class);
            mIntent.putExtra("view_select", (int ) 1);//传入参数选择跳转到购物车列表
            PendingIntent mPendingIntent = PendingIntent.getActivity(context,0,mIntent,FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);
            Notification notify = builder.build();
            manager.notify(goods.id+10,notify);


           RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.my_widget);
//            Intent i = new Intent(context, InformationActivity.class);
//            i.putExtra("goods",goods);
//            PendingIntent pi = PendingIntent.getActivity(context,0,i, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            i.addCategory(Intent.CATEGORY_LAUNCHER);

            updateViews.setTextViewText(R.id.appwidget_text,goods.name + "已添加到购物车！");
            updateViews.setImageViewResource(R.id.appwidget_image, goods.id);
            updateViews.setOnClickPendingIntent(R.id.widget,mPendingIntent);

            ComponentName me = new ComponentName(context, MyWidget.class);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(me, updateViews);
        }

    }
}
