package com.qedplus.particles.services;

import android.content.Context;

import com.qedplus.particles.dao.ChapterDao;
import com.qedplus.particles.dao.QPTDao;
import com.qedplus.particles.dao.SchoolClassDao;
import com.qedplus.particles.dao.SubjectDao;
import com.qedplus.particles.dao.TopicDao;

import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.QPT;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.db.User;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.model.ChapterBO;
import com.qedplus.particles.model.QptBO;
import com.qedplus.particles.model.TopicBO;

import java.util.ArrayList;
import java.util.List;

public class TopicServices {
    public interface UITask{
        void task(List<ChapterBO> chapters);
    }

    private UITask uiTask;

    public TopicServices(UITask uiTask) {
        this.uiTask = uiTask;
    }

    public void displayTOC(final Context cxt, final String subjectName, final int schoolYear) {
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            List<ChapterBO> chapterList = new ArrayList<>();

            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);

                SubjectDao subjectDao = db.subjectDao();
                ChapterDao chapterDao = db.chapterDao();
                TopicDao topicDao = db.topicDao();
                QPTDao qptDao = db.qptDao();

                Subject subject = subjectDao.getByNameAndYear(subjectName, schoolYear);

                if(subject != null) {
                    List<Chapter> chapters = chapterDao.findAllBySubject(subject.getSubjectId());

                    for (Chapter chapter : chapters) {
                        ChapterBO chapterBO = new ChapterBO(chapter.getName(), chapter.getChapterIndex());

                        List<Topic> topics = topicDao.findAllByChapter(chapter.getChapterId());
                        for (Topic topic : topics) {
                            TopicBO topicBO = new TopicBO(topic.getName(), topic.getDescription(), topic.isDoesPrecedeQPT(), topic.getTopicStatus(), topic.isBookmarked());
                            chapterBO.addRevision(topicBO);
                        }

                        List<QPT> qpts = qptDao.findAllByChapter(chapter.getChapterId());
                        for(QPT qpt : qpts) {
                            QptBO qptBO = new QptBO(qpt.getQptIndex(), qpt.getChapterId(), qpt.getNumOfQues(), qpt.getQptStatus());
                            chapterBO.addQPT(qptBO);
                        }

                        chapterList.add(chapterBO);
                    }
                }
            }

            @Override
            public void afterTask() {
                uiTask.task(chapterList);
            }
        });

        dbService.execute();
    }
}
