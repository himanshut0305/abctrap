package com.qedplus.particles.services;

import android.content.Context;

import com.qedplus.particles.dao.ChapterDao;
import com.qedplus.particles.dao.SchoolClassDao;
import com.qedplus.particles.dao.SubjectDao;
import com.qedplus.particles.dao.TeacherSubjectClassDao;
import com.qedplus.particles.dao.TopicDao;
import com.qedplus.particles.dao.UserDao;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.db.TeacherSubjectClass;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.db.User;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.model.TeacherClassSubjectBO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TeacherServices {
    public interface CSTask {
        void task(HashSet<TeacherClassSubjectBO> teacherClassSubjects);
    }

    public interface TopicTask {
        void task();
    }

    private CSTask csTask;
    private TopicTask topicTask;

    public TeacherServices(CSTask csTask) {
        this.csTask = csTask;
    }
    public TeacherServices(TopicTask topicTask) { this.topicTask = topicTask; }

    public void getAllClasses(final Context cxt) {
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            HashSet<TeacherClassSubjectBO> teacherClassSubjects = new HashSet<>();

            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);

                UserDao userDao = db.userDao();

                SchoolClassDao schoolClassDao = db.schoolClassDao();
                SubjectDao subjectDao = db.subjectDao();
                ChapterDao chapterDao = db.chapterDao();
                TopicDao topicDao = db.topicDao();

                User u = userDao.getUserByUsername("teacher");

                TeacherSubjectClassDao teacherSubjectClassDao = db.teacherSubjectClassDao();
                List<TeacherSubjectClass> teacherSubjectClasses = teacherSubjectClassDao.findAllByTeacher(u.getUserId());

                for (TeacherSubjectClass teacherSubjectClass : teacherSubjectClasses) {
                    SchoolClass sc = schoolClassDao.findById(teacherSubjectClass.getSchoolClassId());
                    String className = sc.getName();
                    String subjectName = subjectDao.findById(teacherSubjectClass.getSubjectId()).getName();
                    String currentChapter = "";
                    String currentTopic = "";

                    int schoolYear = sc.getSchoolYear();

                    ArrayList<Chapter> chapters = (ArrayList<Chapter>) chapterDao.findAllBySubject(teacherSubjectClass.getSubjectId());

                    for(Chapter chapter : chapters) {
                        ArrayList<Topic> topics = (ArrayList<Topic>) topicDao.findAllByChapter(chapter.getChapterId());
                        boolean found = false;
                        for(Topic topic : topics) {
                            if(topic.getTopicStatus() == Topic.TopicUnlocked) {
                                currentTopic = topic.getName();
                                currentChapter = chapter.getName();
                                found = true;
                                break;
                            }
                        }

                        if(found) break;
                    }

                    teacherClassSubjects.add(new TeacherClassSubjectBO(className, subjectName, currentChapter, currentTopic, schoolYear));
                }
            }

            @Override
            public void afterTask() {
                csTask.task(teacherClassSubjects);
            }
        });

        dbService.execute();
    }

    public void getTocForSubject(final Context cxt, String subjectName, String className) {
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);
                SubjectDao subjectDao = db.subjectDao();
                ChapterDao chapterDao = db.chapterDao();

            }

            @Override
            public void afterTask() {
                topicTask.task();
            }
        });

        dbService.execute();
    }
}
