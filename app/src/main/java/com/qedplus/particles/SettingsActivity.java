package com.qedplus.particles;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.Utility;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utility.Theme t = Utility.getTheme(getApplicationContext());

        setTheme(t.theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final PrefManager prefManager = new PrefManager(getApplicationContext());

        Switch spaceBadgeSwitch = findViewById(R.id.space_badge_switch);
        Switch everestBadgeSwitch = findViewById(R.id.everest_badge_switch);

        spaceBadgeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    prefManager.setSpaceBadge(true);
                    showBadgeNotification();
                }
                else
                    prefManager.setSpaceBadge(false);
            }
        });

        everestBadgeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showDailyReportNotification();
            }
        });
    }

    private void showBadgeNotification() {
        String name = "QED_BADGE_NOTIFICATION";
        String description = "Notification Channel Description";
        String channelId = "QED_BADGE_NOTIFICATION_CHANNEL_ID";

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = null;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            notificationManager.createNotificationChannel(channel);
        }

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap myLogo = ((BitmapDrawable)getResources().getDrawable(R.drawable.badge_planet)).getBitmap();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_quality_badge)
                .setContentTitle("Achievement Unlocked!")
                .setContentText("You have acquired a new badge")
                .setLargeIcon(myLogo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(contentIntent);

        Notification n = mBuilder.build();
        notificationManager.notify(1, n);

        Log.e("Notification", n.toString());
    }

    private void showDailyReportNotification() {
        String name = "QED_DAILY_NOTIFICATION";
        String description = "Notification Channel Description";
        String channelId = "QED_DAILY_NOTIFICATION_CHANNEL_ID";

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = null;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            notificationManager.createNotificationChannel(channel);
        }

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, DailyReportActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        String dailyReport = "Physics - Motion +\nChemistry - Atom\nBiology - Cell Structure+\nMathematics - Polynomials+\nEnglish : Julius Ceaser";
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_main_tab_2)
                .setContentTitle("Daily Report")
                .setContentText("New Topics are available for revision")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(dailyReport))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(contentIntent);

        Notification n = mBuilder.build();
        notificationManager.notify(1, n);

        Log.e("Notification", n.toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
