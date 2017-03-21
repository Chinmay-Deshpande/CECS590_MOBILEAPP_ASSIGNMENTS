package com.example.chinmaydeshpande.assignment_3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import com.example.chinmaydeshpande.assignment_3.R;

/**
 * Created by Chinmay Deshpande on 3/15/2017.
 */

public class Broadcast extends BroadcastReceiver {


    private NotificationManager nm;
    @Override
    public void onReceive(Context context, Intent intent) {



        NotificationCompat.Builder nb =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.cat1)
                        .setContentTitle("Hello, Draw Pictures  ")
                        .setContentText("Hi there...");


        Vibrator vib= (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);
        nb.setDefaults(Notification.DEFAULT_SOUND);



        Intent my = new Intent(context, MainActivity.class);


        TaskStackBuilder sb = TaskStackBuilder.create(context);
        sb.addParentStack(MainActivity.class);


        sb.addNextIntent(my);


        PendingIntent resultPendingIntent =
                sb.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);

        nb.setFullScreenIntent(resultPendingIntent,false);


        nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);


        nm.notify(0,nb.build());
     //   Toast.makeText(this,"hui",Toast.LENGTH_LONG).show();
    }
}
