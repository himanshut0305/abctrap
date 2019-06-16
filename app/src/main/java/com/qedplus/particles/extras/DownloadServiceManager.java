package com.qedplus.particles.extras;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.qedplus.particles.MainActivity;
import com.qedplus.particles.R;
import com.qedplus.particles.db.Book;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.Topic;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DownloadServiceManager extends Service {
    public DownloadServiceManager() {}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service :", "Download Service Created");
    }

    @Override
    public void onDestroy() { }

    int timing = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service :", "Download Service Started");

        showDownloadingNotification("Downloading", "Downloading rest of the content");
        new ContentDownloader(this).execute();

        return START_STICKY;
    }

    private void stopService() { }

    private static class ContentDownloader extends AsyncTask<Void, Void, Void> {
        private WeakReference<DownloadServiceManager> context;

        ContentDownloader(DownloadServiceManager downloadServiceManager) {
            this.context = new WeakReference<>(downloadServiceManager);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            PrefManager prefManager = new PrefManager(context.get());
            List<Book> books = Utility.getBooksForCurrentUser(context.get(), prefManager.getUserDetails());

            for (Book book : books) {
                List<Chapter> chapters = Utility.getChaptersForBook(context.get(), book.getName());
                if (chapters != null) {
                    for (int i = 0; i < chapters.size(); i++) {
                        Chapter chapter = chapters.get(i);
                        List<Topic> topics = Utility.getTopicsForChapter(context.get(), book.getName(), chapter);
                        if (topics != null) {
                            for (Topic topic : topics) {
                                Utility.getSlidesForTopic(context.get(), book.getName(), chapter.getName(), topic);
                                Utility.getQuestionForTopic(context.get(), book.getName(), chapter.getName(), topic);
                            }
                        }
                    }
                }
            }

            return null;
        }
    }

    private void showDownloadingNotification(String title, String content) {
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
                .setContentTitle(title)
                .setContentText(content)
                .setLargeIcon(myLogo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(contentIntent);

        Notification n = mBuilder.build();
        notificationManager.notify(1, n);
    }
}