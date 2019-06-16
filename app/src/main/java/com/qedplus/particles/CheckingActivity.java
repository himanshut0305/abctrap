package com.qedplus.particles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.qedplus.particles.services.DBService;

public class CheckingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
//                AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
//
//                EducationBoardDao educationBoardDao = db.educationBoardDao();
//
//                EducationBoard cbse = new EducationBoard("CBSE");
//                EducationBoard icse = new EducationBoard("ICSE");
//                EducationBoard mp = new EducationBoard("MP");
//
//                educationBoardDao.insertAll(cbse, icse, mp);
//
//                int cbseBoardId = educationBoardDao.findByName(cbse.getName()).getEducationBoardId();
//
//                SubjectGroupDao subjectGroupDao = db.subjectGroupDao();
//
//                SubjectGroup science = new SubjectGroup("Science");
//                SubjectGroup socialScience = new SubjectGroup("Social Science");
//                SubjectGroup mathematics = new SubjectGroup("Mathematics");
//
//                subjectGroupDao.insertAll(science, socialScience, mathematics);
//
//                int scienceGroupId = subjectGroupDao.findByName(science.getName()).getSubjectGroupId();
//                int socialScienceGroupId = subjectGroupDao.findByName(socialScience.getName()).getSubjectGroupId();
//                int mathematicsGroupId = subjectGroupDao.findByName(mathematics.getName()).getSubjectGroupId();
//
//                SchoolDao schoolDao = db.schoolDao();
//
//                SchoolBO qpsSchool = new SchoolBO("QED Public SchoolBO", "QPSI01", cbseBoardId, "");
//                schoolDao.insertAll(qpsSchool);
//                int qpsSchoolId = schoolDao.findByName(qpsSchool.getName()).getSchoolId();
//
//                SchoolClassDao schoolClassDao = db.schoolClassDao();
//
//                SchoolClassResponse class9AQPSI = new SchoolClassResponse("9A", 9, qpsSchoolId);
//                schoolClassDao.insertAll(class9AQPSI);
//                int class9AQPSIId = schoolClassDao.findByNameAndSchool(class9AQPSI.getName(), qpsSchoolId).getSchoolClassId();
//
//                SubjectDao subjectDao = db.subjectDao();
//
//                Subject physics9th = new Subject("Physics", scienceGroupId, 9);
//                Subject chemistry9th = new Subject("Chemistry", scienceGroupId, 9);
//                Subject biology9th = new Subject("Biology", scienceGroupId, 9);
//                Subject evs9th = new Subject("Environmental Science", scienceGroupId, 9);
//
//                subjectDao.insertAll(physics9th, chemistry9th, biology9th, evs9th);
//
//                int physics9thId = subjectDao.getByNameAndYear(physics9th.getName(), physics9th.getSchoolYear()).getSubjectId();
//                int chemistry9thId = subjectDao.getByNameAndYear(chemistry9th.getName(), chemistry9th.getSchoolYear()).getSubjectId();
//                int biology9thId = subjectDao.getByNameAndYear(biology9th.getName(), biology9th.getSchoolYear()).getSubjectId();
//                int evs9thId = subjectDao.getByNameAndYear(evs9th.getName(), evs9th.getSchoolYear()).getSubjectId();
//
//                ChapterDao chapterDao = db.chapterDao();
//
//                Chapter phy9thCh1 = new Chapter("Motion", 1, physics9thId);
//                Chapter phy9thCh2 = new Chapter("Force and Laws of Motion", 2, physics9thId);
//                Chapter phy9thCh3 = new Chapter("Gravitation", 3, physics9thId);
//                Chapter phy9thCh4 = new Chapter("Work and Energy", 4, physics9thId);
//                Chapter phy9thCh5 = new Chapter("Sound", 5, physics9thId);
//
//                chapterDao.insertAll(phy9thCh1, phy9thCh2, phy9thCh3, phy9thCh4, phy9thCh5);
//
//                Chapter bio9thCh1 = new Chapter("The Fundamental Unit of Life", 1, biology9thId);
//                Chapter bio9thCh2 = new Chapter("Tissues ", 2, biology9thId);
//                Chapter bio9thCh3 = new Chapter("Diversity in Living Organisms", 3, biology9thId);
//
//                chapterDao.insertAll(bio9thCh1, bio9thCh2, bio9thCh3);
//
//                Chapter chem9thCh1 = new Chapter("Matter in Our Surroundings", 1, chemistry9thId);
//                Chapter chem9thCh2 = new Chapter("Is Matter Around Us Pure", 2, chemistry9thId);
//                Chapter chem9thCh3 = new Chapter("Atoms and Molecules", 3, chemistry9thId);
//                Chapter chem9thCh4 = new Chapter("Structure of the Atom", 4, chemistry9thId);
//
//                chapterDao.insertAll(chem9thCh1, chem9thCh2, chem9thCh3, chem9thCh4);
//
//                int phy9ch1Id = chapterDao.getByIndexAndSubject(phy9thCh1.getChapterIndex(), physics9thId).getChapterId();
//
//                Topic phyTopic1 = new Topic("Describing Motion", 1, "Exploring Motion along a Straight Line, Uniform Motion and Non-Uniform Motion", phy9ch1Id);
//                Topic phyTopic2 = new Topic("Measuring the Rate of Motion", 2, "Understanding Speed and Direction", phy9ch1Id);
//                Topic phyTopic3 = new Topic("Rate of Change of Velocity", 3, "Evaluating effect of force on moving object", phy9ch1Id);
//
//                Topic phyTopic4 = new Topic("Graphical Representation of Motion", 4, "Understanding Distance-Time Graphs and Velocity-Time Graphs", phy9ch1Id);
//                Topic phyTopic5 = new Topic("Equations of Motion by Graphical Method",5, "Equations for Velocity-Time and Position-Time Relations", phy9ch1Id);
//                Topic phyTopic6 = new Topic("Uniform Circular Motion", 6, "Exploring Motion of an Object in a Circle", phy9ch1Id);
//
//                TopicDao topicDao = db.topicDao();
//
//                topicDao.insertAll(phyTopic1, phyTopic2, phyTopic3, phyTopic4, phyTopic5, phyTopic6);
//
//                List<Topic> phy9thCh1Topics = topicDao.findAllByChapter(physics9thId);
//
//                String logString = "";
//                for (Topic topic : phy9thCh1Topics) {
//                    logString += topic.toString() + "\n";
//                }
//
//                Log.e("Log String", logString);
            }

            @Override
            public void afterTask() {

            }
        });
        dbService.execute();

        TextView textView = (TextView) findViewById(R.id.wuss_wuss);
        textView.setText("Whatevr");

    }
}
