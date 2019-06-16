package com.qedplus.particlesTeacher.extras;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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