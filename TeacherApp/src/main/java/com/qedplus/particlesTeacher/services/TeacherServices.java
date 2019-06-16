package com.qedplus.particlesTeacher.services;

import android.content.Context;
import android.util.Log;

import com.qedplus.particlesTeacher.dao.ChapterDao;
import com.qedplus.particlesTeacher.dao.SchoolClassDao;
import com.qedplus.particlesTeacher.dao.SubjectDao;
import com.qedplus.particlesTeacher.dao.TeacherSubjectClassDao;
import com.qedplus.particlesTeacher.dao.TopicDao;
import com.qedplus.particlesTeacher.dao.UserDao;
import com.qedplus.particlesTeacher.db.Chapter;
import com.qedplus.particlesTeacher.db.SchoolClass;
import com.qedplus.particlesTeacher.db.TeacherSubjectClass;
import com.qedplus.particlesTeacher.db.Topic;
import com.qedplus.particlesTeacher.db.User;
import com.qedplus.particlesTeacher.extras.AppDatabase;
import com.qedplus.particlesTeacher.extras.PrefManager;
import com.qedplus.particlesTeacher.model.TeacherClassSubjectBO;

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

    private static CSTask csTask;
    private TopicTask topicTask;

    public TeacherServices(CSTask csTask) {
        this.csTask = csTask;
    }
    public TeacherServices(TopicTask topicTask) { this.topicTask = topicTask; }

    public static void getAllClasses(final Context cxt) {
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            HashSet<TeacherClassSubjectBO> teacherClassSubjects = new HashSet<>();

            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);

                SchoolClassDao schoolClassDao = db.schoolClassDao();
                SubjectDao subjectDao = db.subjectDao();
                ChapterDao chapterDao = db.chapterDao();
                TopicDao topicDao = db.topicDao();
                PrefManager pf = new PrefManager(cxt);

                User u = pf.getUserDetails();

                TeacherSubjectClassDao teacherSubjectClassDao = db.teacherSubjectClassDao();
                List<TeacherSubjectClass> teacherSubjectClasses = teacherSubjectClassDao.findAllByTeacher(u.getUserId());

                Log.e("TSC :", teacherSubjectClasses.toString());

                for (TeacherSubjectClass teacherSubjectClass : teacherSubjectClasses) {
                    SchoolClass sc = schoolClassDao.findById(teacherSubjectClass.getSchoolClassId());
                    String className = sc.getName();
                    String subjectName = subjectDao.findById(teacherSubjectClass.getSubjectId()).getName();
                    String currentChapter = "";
                    String currentTopic = "";

                    int schoolYear = sc.getSchoolYear();

                    ArrayList<Chapter> chapters = (ArrayList<Chapter>) chapterDao.findAllBySubject(teacherSubjectClass.getSubjectId());

                    Log.e("Chapters : ", chapters.toString());
                    for(Chapter chapter : chapters) {
                        ArrayList<Topic> topics = (ArrayList<Topic>) topicDao.findAllByChapter(chapter.getChapterId());

                        Log.e("Topics :", topics.toString());
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
