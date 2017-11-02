package com.example.administrator.android_lab3;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class MyWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //实例化RemoteView,其对应相应的Widget布局
        RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.my_widget);
        Intent i = new Intent(context,MainActivity.class);
        i.putExtra("view_select",0);
        PendingIntent pi = PendingIntent.getActivity(context,0,i, PendingIntent.FLAG_UPDATE_CURRENT);

        //RemoteView上的Button设置按钮事件
        updateViews.setOnClickPendingIntent(R.id.widget,pi);
        ComponentName me = new ComponentName(context, MyWidget.class);
        appWidgetManager.updateAppWidget(me, updateViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent){
        super.onReceive(context, intent);

        if (intent.getAction().equals("RECOMMEND_GOODS")) {
            Goods goods = (Goods) intent.getSerializableExtra("recommendGood");

            RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.my_widget);
            Intent i = new Intent(context, InformationActivity.class);
            i.putExtra("goods",goods);
            PendingIntent pi = PendingIntent.getActivity(context,0,i, PendingIntent.FLAG_UPDATE_CURRENT);

            i.addCategory(Intent.CATEGORY_LAUNCHER);
            updateViews.setTextViewText(R.id.appwidget_text,goods.name + "仅售" + goods.price + "!");
            updateViews.setImageViewResource(R.id.appwidget_image, goods.id);
            updateViews.setOnClickPendingIntent(R.id.widget,pi);

            ComponentName me = new ComponentName(context, MyWidget.class);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(me, updateViews);
        }
    }
}

