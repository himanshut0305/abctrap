//package com.qedplus.particlesTeacher.services;
//
//import android.content.Context;
//
//import com.qedplus.particlesTeacher.dao.ChapterDao;
//import com.qedplus.particlesTeacher.dao.SubjectDao;
//import com.qedplus.particlesTeacher.dao.TopicDao;
//
//import com.qedplus.particlesTeacher.db.Chapter;
//import com.qedplus.particlesTeacher.db.Subject;
//import com.qedplus.particlesTeacher.db.Topic;
//import com.qedplus.particlesTeacher.extras.AppDatabase;
//import com.qedplus.particlesTeacher.model.ChapterBO;
//import com.qedplus.particlesTeacher.model.TopicBO;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TopicServices {
//    public interface UITask{
//        void task(List<ChapterBO> chapters);
//    }
//
//    private UITask uiTask;
//
//    public TopicServices(UITask uiTask) {
//        this.uiTask = uiTask;
//    }
//
//    public void displayTOC(final Context cxt, final String subjectName, final int schoolYear) {
//        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
//            List<ChapterBO> chapterList = new ArrayList<>();
//
//            @Override
//            public void task() {
//                AppDatabase db = AppDatabase.getAppDatabase(cxt);
//
//                SubjectDao subjectDao = db.subjectDao();
//                ChapterDao chapterDao = db.chapterDao();
//                TopicDao topicDao = db.topicDao();
//
//
//                Subject subject = subjectDao.getByNameAndYear(subjectName, schoolYear);
//
//                if(subject != null) {
//                    List<Chapter> chapters = chapterDao.findAllBySubject(subject.getSubjectId());
//
//                    for (Chapter chapter : chapters) {
//                        ChapterBO chapterBO = new ChapterBO(chapter.getName(), chapter.getChapterIndex());
//
//                        List<Topic> topics = topicDao.findAllByChapter(chapter.getChapterId());
//                        for (Topic topic : topics) {
//                            TopicBO topicBO = new TopicBO(topic.getName(), topic.getDescription(), topic.isDoesPrecedeQPT(), topic.getTopicStatus(), topic.isBookmarked());
//                            chapterBO.addRevision(topicBO);
//                        }
//
//
//                        chapterList.add(chapterBO);
//                    }
//                }
//            }
//
//            @Override
//            public void afterTask() {
//                uiTask.task(chapterList);
//            }
//        });
//
//        dbService.execute();
//    }
//}
