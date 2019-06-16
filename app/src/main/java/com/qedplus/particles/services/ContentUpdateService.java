package com.qedplus.particles.services;

import android.content.Context;
import android.util.Log;

import com.qedplus.particles.db.Book;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.Utility;

import java.util.List;

public class ContentUpdateService {
    private boolean foundToUpdate = false;
    private int topicUpdated = 0;

    public interface UITask{
        void afterTask(boolean foundToUpdate, int topicsUpdated);
    }

    private ContentUpdateService.UITask uiTask;

    public ContentUpdateService() { }

    public void updateContentForChapter(final Context context, final String subjectName, final String chapterName, ContentUpdateService.UITask task) {
        this.uiTask = task;
        PrefManager prefManager = new PrefManager(context);

        final int schoolYear = prefManager.getSchoolYear();
        final String schoolCode = prefManager.getSchoolDetails().getSchoolCode();

        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(context);

                Subject subject = db.subjectDao().findByName(subjectName);
                Book book = db.bookDao().findBySubjectAndSchoolYear(subject.getSubjectId(), schoolYear);
                Chapter chapter = db.chapterDao().getByNameAndSubject(chapterName, subject.getSubjectId());
                List<Topic> topics = db.topicDao().findAllByChapter(chapter.getChapterId());

                for (Topic topic : topics) {
                    foundToUpdate = Utility.getSlidesForTopicIfUpdated(context, book.getName(), chapterName, topic);
                    if(foundToUpdate)
                        topicUpdated++;
                }
            }

            @Override
            public void afterTask() {
                uiTask.afterTask(foundToUpdate, topicUpdated);
            }
        });

        dbService.execute();
    }
}