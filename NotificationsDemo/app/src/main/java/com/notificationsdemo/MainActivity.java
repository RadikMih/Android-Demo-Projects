package com.notificationsdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(), 1, intent, 0);

        Notification notification = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(getApplicationContext(), "myChannel")
                    .setContentTitle("Lunch is ready")
                    .setContentText("It's getting cold...")
                    .setContentIntent(pendingIntent)
                    //.addAction(android.R.drawable.sym_action_chat, "Chat", pendingIntent)
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("myChannel",
                    "Channel human readable title",
                    manager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            manager.notify(1, notification);
        }

    }
}
