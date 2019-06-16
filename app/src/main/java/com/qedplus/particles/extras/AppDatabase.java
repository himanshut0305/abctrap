package com.qedplus.particles.extras;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.qedplus.particles.dao.AnnouncementDao;
import com.qedplus.particles.dao.BadgeDao;
import com.qedplus.particles.dao.BookDao;
import com.qedplus.particles.dao.ChapterDao;
import com.qedplus.particles.dao.ConfirmInteractionDao;
import com.qedplus.particles.dao.DiagramDao;
import com.qedplus.particles.dao.EducationBoardDao;
import com.qedplus.particles.dao.MCQOptionDao;
import com.qedplus.particles.dao.MultipleChoiceQuestionDao;
import com.qedplus.particles.dao.OneWordAnswerQuestionDao;
import com.qedplus.particles.dao.QPTDao;
import com.qedplus.particles.dao.RevealInteractionDao;
import com.qedplus.particles.dao.RevisionPointDao;
import com.qedplus.particles.dao.RevisionSlideDao;
import com.qedplus.particles.dao.SchoolClassDao;
import com.qedplus.particles.dao.SchoolDao;
import com.qedplus.particles.dao.SubjectDao;
import com.qedplus.particles.dao.SubjectGroupDao;
import com.qedplus.particles.dao.TeacherSubjectClassDao;
import com.qedplus.particles.dao.TopicDao;
import com.qedplus.particles.dao.UserDao;
import com.qedplus.particles.db.Announcement;
import com.qedplus.particles.db.Badge;
import com.qedplus.particles.db.Book;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.ConfirmInteraction;
import com.qedplus.particles.db.Diagram;
import com.qedplus.particles.db.EducationBoard;
import com.qedplus.particles.db.MCQOption;
import com.qedplus.particles.db.MultipleChoiceQuestion;
import com.qedplus.particles.db.OneWordAnswerQuestion;
import com.qedplus.particles.db.QPT;
import com.qedplus.particles.db.RevealInteraction;
import com.qedplus.particles.db.RevisionPoint;
import com.qedplus.particles.db.RevisionSlide;
import com.qedplus.particles.db.School;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.db.SubjectGroup;
import com.qedplus.particles.db.TeacherSubjectClass;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.db.User;

@Database(entities = {
            EducationBoard.class,
            School.class,
            SchoolClass.class,
            Subject.class,
            SubjectGroup.class,
            Book.class,
            Chapter.class,
            Topic.class,
            RevisionSlide.class,
            RevisionPoint.class,
            Diagram.class,
            RevealInteraction.class,
            ConfirmInteraction.class,
            QPT.class,
            MultipleChoiceQuestion.class,
            OneWordAnswerQuestion.class,
            MCQOption.class,
            Announcement.class,
            Badge.class,
            User.class,
            TeacherSubjectClass.class
        }, version = 2)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract EducationBoardDao educationBoardDao();
    public abstract SchoolDao schoolDao();
    public abstract SchoolClassDao schoolClassDao();

    public abstract SubjectGroupDao subjectGroupDao();
    public abstract SubjectDao subjectDao();
    public abstract BookDao bookDao();

    public abstract ChapterDao chapterDao();

    public abstract TopicDao topicDao();
    public abstract RevisionPointDao revisionPointDao();
    public abstract RevisionSlideDao revisionSlideDao();

    public abstract DiagramDao diagramDao();
    public abstract RevealInteractionDao revealInteractionDao();
    public abstract ConfirmInteractionDao confirmInteractionDao();

    public abstract QPTDao qptDao();

    public abstract OneWordAnswerQuestionDao oneWordAnswerQuestionDao();
    public abstract MultipleChoiceQuestionDao multipleChoiceQuestionDao();
    public abstract MCQOptionDao mcqOptionDao();

    public abstract AnnouncementDao announcementDao();
    public abstract BadgeDao badgeDao();
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