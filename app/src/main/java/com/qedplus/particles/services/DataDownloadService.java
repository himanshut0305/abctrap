package com.qedplus.particles.services;

import android.content.Context;
import android.util.Log;

import com.qedplus.particles.db.Book;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.Utility;

public class DataDownloadService {
    private SchoolClass schoolClass = null;

    public interface UITask{
        void afterTask(SchoolClass schoolClass);
    }

    private DataDownloadService.UITask uiTask;

    public DataDownloadService(DataDownloadService.UITask uiTask) {
        this.uiTask = uiTask;
    }

    public void downloadContentForSubject(final Context context, final String subjectName) {
        PrefManager prefManager = new PrefManager(context);

        final int schoolYear = prefManager.getSchoolYear();
        final String schoolCode = prefManager.getSchoolDetails().getSchoolCode();

        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(context);

                Subject subject = db.subjectDao().findByName(subjectName);
                if(subject != null) {
                    Book book = db.bookDao().findBySubjectAndSchoolYear(subject.getSubjectId(), schoolYear);

                    if(book == null) {
                        Log.e("Book :", "Not Found");
                        book = Utility.getBookForSubject(context, schoolCode, subjectName, schoolYear);
                        if(book != null) {
                            Utility.getChaptersForBook(context, book.getName());
                        }
                    }
                    else {
                        Log.e("Book :", book.getName());
                        Utility.getChaptersForBook(context, book.getName());
                    }
                }
                else {
                    Log.e("Error :", "This Subject doesn't exist for your school");
                }
            }

            @Override
            public void afterTask() {
                uiTask.afterTask(schoolClass);
            }
        });

        dbService.execute();
    }
}