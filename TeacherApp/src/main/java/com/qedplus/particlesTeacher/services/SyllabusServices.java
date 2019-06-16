package com.qedplus.particlesTeacher.services;

import android.content.Context;

import com.qedplus.particlesTeacher.dao.ChapterDao;
import com.qedplus.particlesTeacher.dao.SubjectDao;
import com.qedplus.particlesTeacher.dao.TopicDao;
import com.qedplus.particlesTeacher.db.Chapter;
import com.qedplus.particlesTeacher.db.Subject;
import com.qedplus.particlesTeacher.db.Topic;
import com.qedplus.particlesTeacher.extras.AppDatabase;
import com.qedplus.particlesTeacher.model.ChapterBO;
import com.qedplus.particlesTeacher.model.TopicBO;

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
                            TopicBO topicBO = new TopicBO(topic.getName(), topic.getDescription(), true, topic.getTopicStatus(), true);
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


