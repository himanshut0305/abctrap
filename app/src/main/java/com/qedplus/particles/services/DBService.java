package com.qedplus.particles.services;

import android.os.AsyncTask;

public class DBService extends AsyncTask<Object, String, Object> {
    public interface BackgroundDBTask{
        void task();
        void afterTask();
    }

    BackgroundDBTask backgroundDBTask;

    public DBService(BackgroundDBTask backgroundDBTask) {
        this.backgroundDBTask = backgroundDBTask;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        backgroundDBTask.task();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        backgroundDBTask.afterTask();
    }
}
