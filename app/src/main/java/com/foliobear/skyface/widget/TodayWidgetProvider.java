package com.foliobear.skyface.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.foliobear.skyface.sync.SunshineSyncAdapter;

/**
 * Created by Joseph Costlow on 17-Apr-17.
 */

public class TodayWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        int weatherArtResourceId = R.drawable.art_clear;
//        String description = "Clear";
//        double maxTemp = 24;
//        String formattedMaxTemperature = Utility.formatTemperature(context, maxTemp);
//
//        for (int appWidgetId : appWidgetIds) {
//            int layoutId = R.layout.widget_today_small;
//            RemoteViews views = new RemoteViews(context.getPackageName(), layoutId);
//
//            // Add the data to the RemoteViews
//            views.setImageViewResource(R.id.widget_icon, weatherArtResourceId);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
////                setRemoteContentDescription(views, description);
//            }
//
//            views.setTextViewText(R.id.widget_high_temperature, formattedMaxTemperature);
//
//            // Create an Intent to launch MainActivity
//            Intent launchIntent = new Intent(context, MainActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
//            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
        context.startService(new Intent(context, TodayWidgetIntentService.class));

            // Tell the AppWidgetManager to perform an update on the current app widget
//            appWidgetManager.updateAppWidget(appWidgetId, views);

    }

//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
//    private void setContentDescription(RemoteViews views, String description) {
//        views.setContentDescription(R.id.widget_icon, description);
//    }


    @Override
    public void onReceive(@NonNull Context context, Intent intent) {
        super.onReceive(context, intent);
        if (SunshineSyncAdapter.ACTION_DATA_UPDATED.equals(intent.getAction())) {
            context.startService(new Intent(context, TodayWidgetIntentService.class));
        }
    }
}
