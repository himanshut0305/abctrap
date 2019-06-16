package com.qedplus.particles.services;

import android.os.AsyncTask;

public class DataRetrievalService extends AsyncTask<Object, String, Object> {
    public interface BackgroundTask {
        void task();
        void duringTask(String progress);
        void afterTask();
    }

    BackgroundTask backgroundTask;

    public DataRetrievalService(BackgroundTask backgroundTask){
        this.backgroundTask = backgroundTask;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        backgroundTask.task();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        backgroundTask.afterTask();
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        backgroundTask.duringTask(progress[0]);
    }
}
