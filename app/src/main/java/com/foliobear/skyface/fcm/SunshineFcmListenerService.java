package com.foliobear.skyface.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.foliobear.skyface.MainActivity;
import com.foliobear.skyface.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by Joseph Costlow on 10-Apr-17.
 */

public class SunshineFcmListenerService extends FirebaseMessagingService {

    private final String TAG = SunshineFcmListenerService.class.getSimpleName();

    private static final String EXTRA_WEATHER = "weather";
    private static final String EXTRA_LOCATION = "location";

    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onMessageReceived(RemoteMessage message) {
        if (message != null) {
            String from = message.getFrom();

            String senderId = getString(R.string.gcm_defaultSenderId);
            if (senderId.length() == 0) {
                Toast.makeText(this, "SenderID string needs to be set", Toast.LENGTH_SHORT).show();
            }

            if (senderId.equals(from)) {
                Map data = message.getData();
                String weather = (String) data.get(EXTRA_WEATHER);
                String location = (String) data.get(EXTRA_LOCATION);
                String alert = String.format(getString(R.string.fcm_weather_alert), weather, location);
                sendNotification(alert);
            }
        }
    }

    private void sendNotification(String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        Bitmap largeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.art_storm);
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.art_clear)
                .setLargeIcon(largeIcon)
                .setContentTitle(getString(R.string.weather_alert_title))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setContentIntent(contentIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
