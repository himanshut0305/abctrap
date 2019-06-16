package com.qedplus.particles.services;

import android.content.Context;

import com.qedplus.particles.dao.ChapterDao;
import com.qedplus.particles.dao.SubjectDao;
import com.qedplus.particles.dao.TopicDao;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.model.ChapterBO;
import com.qedplus.particles.model.TopicBO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SyllabusServices {
    public interface SyllabusUITask {
        void task(HashSet<ChapterBO> chapters);
    }

    private SyllabusUITask syllabusUITask;

    public SyllabusServices(SyllabusUITask syllabusUITask) {
        this.syllabusUITask = syllabusUITask;
    }

    public void getTocForSubject(final Context cxt, final String subjectName, final int schoolYear) {
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            HashSet<ChapterBO> chapterBOS = new HashSet<>();

            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);

                SubjectDao subjectDao = db.subjectDao();
                ChapterDao chapterDao = db.chapterDao();
                TopicDao topicDao = db.topicDao();

                Subject subject = subjectDao.getByNameAndYear(subjectName, schoolYear);
                ArrayList<Chapter> chapters = (ArrayList<Chapter>) chapterDao.findAllBySubject(subject.getSubjectId());

                for(Chapter chapter : chapters) {
                    ChapterBO chapterBO = new ChapterBO(chapter.getName(), chapter.getChapterIndex());

                    List<Topic> topics = topicDao.findAllByChapter(chapter.getChapterId());
                    for (Topic topic : topics) {
                        TopicBO topicBO = new TopicBO(topic.getName(), topic.getDescription(), topic.isDoesPrecedeQPT(), topic.getTopicStatus(), topic.isBookmarked());
                        chapterBO.addRevision(topicBO);
                    }

                    chapterBOS.add(chapterBO);
                }
            }

            @Override
            public void afterTask() {
                syllabusUITask.task(chapterBOS);
            }
        });

        dbService.execute();
    }
}
