package com.qedplus.particles.services;

import android.content.Context;
import android.os.AsyncTask;

import com.qedplus.particles.dao.SchoolClassDao;
import com.qedplus.particles.db.Book;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.Utility;

import java.util.List;

public class InitialiseService extends AsyncTask<Object, String, Object> {
    private PrefManager prefManager;
    private SchoolClass schoolClass = null;

    private Context context;
    private InitTask initTask;

    public interface InitTask {
        void afterTask(SchoolClass schoolClass);
        void duringTask(String progress);
    }

    public InitialiseService(Context context, InitTask initTask) {
        this.context = context;
        this.initTask = initTask;

        prefManager = new PrefManager(context);
    }

    @Override
    protected Object doInBackground(Object... objects) {
        List<Book> books = Utility.getBooksForCurrentUser(context, prefManager.getUserDetails());

        for(Book book : books) {
            publishProgress(book.getName());
            List<Chapter> chapters = Utility.getChaptersForBook(context, book.getName());
            if (chapters != null) {
                for (int i = 0; i < chapters.size(); i++) {
                    Chapter chapter = chapters.get(i);
                    List<Topic> topics = Utility.getTopicsForChapter(context, book.getName(), chapter);
                    if (topics != null) {
                        for (Topic topic : topics) {
                            Utility.getSlidesForTopic(context, book.getName(), chapter.getName(), topic);
                            Utility.getQuestionForTopic(context, book.getName(), chapter.getName(), topic);
                        }
                    }

                    if (i == 0) break;
                }
            }
            //break;
        }

        long schoolClassId = prefManager.getUserDetails().getSchoolClassId();

        AppDatabase db = AppDatabase.getAppDatabase(context);
        SchoolClassDao schoolClassDao = db.schoolClassDao();
        schoolClass = schoolClassDao.findById(schoolClassId);

        return null;
    }


    @Override
    protected void onProgressUpdate(String... progress) {
        initTask.duringTask(progress[0]);
    }

    @Override
    protected void onPostExecute(Object o) {
        initTask.afterTask(schoolClass);
    }
}
