package com.qedplus.particlesTeacher.extras;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.qedplus.particlesTeacher.dao.BookDao;
import com.qedplus.particlesTeacher.dao.ChapterDao;
import com.qedplus.particlesTeacher.dao.EducationBoardDao;
import com.qedplus.particlesTeacher.dao.SchoolClassDao;
import com.qedplus.particlesTeacher.dao.SchoolDao;
import com.qedplus.particlesTeacher.dao.SubjectDao;
import com.qedplus.particlesTeacher.dao.SubjectGroupDao;
import com.qedplus.particlesTeacher.dao.TeacherSubjectClassDao;
import com.qedplus.particlesTeacher.dao.TopicDao;
import com.qedplus.particlesTeacher.dao.UserDao;
import com.qedplus.particlesTeacher.db.Book;
import com.qedplus.particlesTeacher.db.Chapter;
import com.qedplus.particlesTeacher.db.EducationBoard;
import com.qedplus.particlesTeacher.db.School;
import com.qedplus.particlesTeacher.db.SchoolClass;
import com.qedplus.particlesTeacher.db.Subject;
import com.qedplus.particlesTeacher.db.SubjectGroup;
import com.qedplus.particlesTeacher.db.TeacherSubjectClass;
import com.qedplus.particlesTeacher.db.Topic;
import com.qedplus.particlesTeacher.db.User;

@Database(entities = {
            EducationBoard.class,
            School.class,
            SchoolClass.class,
            Subject.class,
            SubjectGroup.class,
            Book.class,
            Chapter.class,
            Topic.class,
            User.class,
            TeacherSubjectClass.class
        }, version = 2)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract SchoolDao schoolDao();
    public abstract SchoolClassDao schoolClassDao();

    public abstract SubjectGroupDao subjectGroupDao();
    public abstract SubjectDao subjectDao();
    public abstract BookDao bookDao();

    public abstract ChapterDao chapterDao();
    public abstract EducationBoardDao educationBoardDao();
    public abstract TopicDao topicDao();


    public abstract UserDao userDao();

    public abstract TeacherSubjectClassDao teacherSubjectClassDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "QEDAppDB.db")
                            .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}