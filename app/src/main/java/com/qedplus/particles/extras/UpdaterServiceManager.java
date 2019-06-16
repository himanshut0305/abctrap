package com.qedplus.particles.extras;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.qedplus.particles.MainActivity;
import com.qedplus.particles.R;

import java.util.Timer;
import java.util.TimerTask;

public class UpdaterServiceManager extends Service {
    private final int UPDATE_INTERVAL = 10 * 1000;
    private Timer timer = new Timer();

    public UpdaterServiceManager() {}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service", "Update Service Created");
    }

    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
    }

    int timing = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service :", "Update Service Started");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timing = timing + 10;

            }

        }, 0, UPDATE_INTERVAL);
        return START_STICKY;
    }

    private void stopService() {
        if (timer != null) timer.cancel();
    }
}