package com.qedplus.particlesTeacher.extras;

public abstract class DBFeeder {
//    public static void feedContent(AppDatabase db) {
//        SubjectGroupDao subjectGroupDao = db.subjectGroupDao();
//
//        SubjectGroup science = new SubjectGroup("Science");
//        SubjectGroup socialScience = new SubjectGroup("Social Science");
//        SubjectGroup mathematics = new SubjectGroup("Mathematics");
//
//        long[] subjectGroupIds = subjectGroupDao.insertAll(science, socialScience, mathematics);
//
//        SubjectDao subjectDao = db.subjectDao();
//
//        Subject physics9th = new Subject("Physics", subjectGroupIds[0], 9);
//        Subject chemistry9th = new Subject("Chemistry", subjectGroupIds[0], 9);
//        Subject biology9th = new Subject("Biology", subjectGroupIds[0], 9);
//        Subject evs9th = new Subject("Environmental Science", subjectGroupIds[0], 9);
//
//        long[] subjectIds = subjectDao.insertAll(physics9th, chemistry9th, biology9th, evs9th);
//
//        ChapterDao chapterDao = db.chapterDao();
//
//        Chapter phy9thCh1 = new Chapter("Motion", 1, subjectIds[0]);
//        Chapter phy9thCh2 = new Chapter("Force and Laws of Motion", 2, subjectIds[0]);
//        Chapter phy9thCh3 = new Chapter("Gravitation", 3, subjectIds[0]);
//        Chapter phy9thCh4 = new Chapter("Work and Energy", 4, subjectIds[0]);
//        Chapter phy9thCh5 = new Chapter("Sound", 5, subjectIds[0]);
//
//        chapterDao.insertAll(phy9thCh1, phy9thCh2, phy9thCh3, phy9thCh4, phy9thCh5);
//
//        Chapter bio9thCh1 = new Chapter("The Fundamental Unit of Life", 1, subjectIds[2]);
//        Chapter bio9thCh2 = new Chapter("Tissues ", 2, subjectIds[2]);
//        Chapter bio9thCh3 = new Chapter("Diversity in Living Organisms", 3, subjectIds[2]);
//
//        chapterDao.insertAll(bio9thCh1, bio9thCh2, bio9thCh3);
//
//        Chapter chem9thCh1 = new Chapter("Matter in Our Surroundings", 1, subjectIds[1]);
//        Chapter chem9thCh2 = new Chapter("Is Matter Around Us Pure", 2, subjectIds[1]);
//        Chapter chem9thCh3 = new Chapter("Atoms and Molecules", 3, subjectIds[1]);
//        Chapter chem9thCh4 = new Chapter("Structure of the Atom", 4, subjectIds[1]);
//
//        chapterDao.insertAll(chem9thCh1, chem9thCh2, chem9thCh3, chem9thCh4);
//
//        int phy9ch1Id = chapterDao.getByIndexAndSubject(phy9thCh1.getChapterIndex(), subjectIds[0]).getChapterId();
//
//        QPT phyCh1qpt1 = new QPT(1, phy9ch1Id, 9);
//        QPT phyCh1qpt2 = new QPT(2, phy9ch1Id, 10);
//        QPT phyCh1qpt3 = new QPT(3, phy9ch1Id, 9);
//
//        phyCh1qpt1.setQptStatus(QPT.QPTUnlocked);
//
//        QPTDao qptDao = db.qptDao();
//
//        long[] phyCh1qptIds = qptDao.insertAll(phyCh1qpt1, phyCh1qpt2, phyCh1qpt3);
//
//        Topic phyTopic1 = new Topic("Introduction", 1, "Exploring Motion along a Straight Line, Uniform Motion and Non-Uniform Motion", phy9ch1Id, false);
//        Topic phyTopic2 = new Topic("Speed and Velocity", 2, "Understanding Speed and Direction", phy9ch1Id, false);
//        Topic phyTopic3 = new Topic("Acceleration", 3, "Evaluating effect of force on moving object", phy9ch1Id, true);
//
//        Topic phyTopic4 = new Topic("Equation of Motion", 4, "Understanding Distance-Time Graphs and Velocity-Time Graphs", phy9ch1Id, false);
//        Topic phyTopic5 = new Topic("Graphical Representation of Motion",5, "Equations for Velocity-Time and Position-Time Relations", phy9ch1Id, true);
//        Topic phyTopic6 = new Topic("Uniform Circular Motion", 6, "Exploring Motion of an Object in a Circle", phy9ch1Id, true);
//
//        phyTopic1.setTopicStatus(Topic.TopicRevised);
//        phyTopic2.setTopicStatus(Topic.TopicRevised);
//        phyTopic3.setTopicStatus(Topic.TopicActive);
//
//        phyTopic4.setTopicStatus(Topic.TopicUnlocked);
//
//        phyTopic1.setQptId(phyCh1qptIds[0]);
//        phyTopic2.setQptId(phyCh1qptIds[0]);
//        phyTopic3.setQptId(phyCh1qptIds[0]);
//
//        phyTopic4.setQptId(phyCh1qptIds[1]);
//        phyTopic5.setQptId(phyCh1qptIds[1]);
//        phyTopic6.setQptId(phyCh1qptIds[2]);
//
//        TopicDao topicDao = db.topicDao();
//
//        long[] phyTopicIds = topicDao.insertAll(phyTopic1, phyTopic2, phyTopic3, phyTopic4, phyTopic5, phyTopic6);
//
//        MultipleChoiceQuestionDao mcqDao = db.multipleChoiceQuestionDao();
//        OneWordAnswerQuestionDao owaqDao = db.oneWordAnswerQuestionDao();
//        MCQOptionDao mcqOptionDao = db.mcqOptionDao();
//
//        OneWordAnswerQuestion owaq1 = new OneWordAnswerQuestion("The average speed of a vehicle is 4.5 m/s. It runs for 10 minutes. How far does it travel?", "Answer in One Word or small phrase", "2700 m", QuestionLevel.EASY, QuestionCategory.NUMERICAL, "Average velocity = total distance / total time , therefore </br>Total distance = velocity x time = 4.5 x 600  = 2700 m </br>[As avg speed is in m/s so time is taken in sec  -10 min = 600 sec]", phyTopicIds[0]);
//
//        owaqDao.insertAll(owaq1);
//
//        MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion("Which of the following is true with respect to Distance?", "Choose one correct answer", null, MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, phyTopicIds[0]);
//        long[] mcq1Id = mcqDao.insertAll(mcq1);
//
//        MCQOption op11 = new MCQOption("Distance is always +ve", true, "As distance is a scalar quantity so it’s always +ve, whereas displacement is vector so it may be +ve or –ve", mcq1Id[0]);
//        MCQOption op12 = new MCQOption("Distance is always -ve", false, "As distance is a scalar quantity so it’s always +ve, whereas displacement is vector so it may be +ve or –ve", mcq1Id[0]);
//        MCQOption op13 = new MCQOption("Distance may be +ve or -ve", false, "As distance is a scalar quantity so it’s always +ve, whereas displacement is vector so it may be +ve or –ve", mcq1Id[0]);
//
//        mcqOptionDao.insertAll(op11, op12, op13);
//
//        MultipleChoiceQuestion mcq2 = new MultipleChoiceQuestion("Which of the following is true in case of a moving body?", "Choose one correct answer?", null, MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, phyTopicIds[0]);
//        long[] mcq2Id = mcqDao.insertAll(mcq2);
//
//        MCQOption op21 = new MCQOption("Displacement = distance", false, "Displacement is the shortest distance", mcq2Id[0]);
//        MCQOption op22 = new MCQOption("Displacement >= distance", false, "Displacement is the shortest distance", mcq2Id[0]);
//        MCQOption op23 = new MCQOption("Displacement <= distance", true, "Displacement is the shortest distance", mcq2Id[0]);
//        MCQOption op24 = new MCQOption("Displacement < distance", false, "Displacement is the shortest distance", mcq2Id[0]);
//
//        mcqOptionDao.insertAll(op21, op22, op23, op24);
//
//        MultipleChoiceQuestion mcq3 = new MultipleChoiceQuestion("A body covers half the distance with a speed of 20 m/s and the other half with a speed of 30 m/s . What is its average speed for the whole journey?", "Choose one correct answer?", null, MCQType.SINGLE_ANSWER, QuestionCategory.NUMERICAL, QuestionLevel.MEDIUM, phyTopicIds[0]);
//        long[] mcq3Id = mcqDao.insertAll(mcq3);
//
//        MCQOption op31 = new MCQOption("25 m/s", false, "avg speed = total distance /total time\n" +
//                "Let’s say the total distance is 2X.\n" +
//                "Time for first half is t1=x/20\n" +
//                "Time for second half is t1=x/30\n" +
//                "Total time=t1+t2= x/20+x/30 = (3x+2x)/60\n" +
//                "X(5/60)=x/12\n" +
//                "Avg speed= 2X / (X/12)=24 m/s\n", mcq3Id[0]);
//        MCQOption op32 = new MCQOption("24 m/s", true, "avg speed = total distance /total time\n" +
//                "Let’s say the total distance is 2X.\n" +
//                "Time for first half is t1=x/20\n" +
//                "Time for second half is t1=x/30\n" +
//                "Total time=t1+t2= x/20+x/30 = (3x+2x)/60\n" +
//                "X(5/60)=x/12\n" +
//                "Avg speed= 2X / (X/12)=24 m/s\n", mcq3Id[0]);
//        MCQOption op33 = new MCQOption("0 m/s", false, "avg speed = total distance /total time\n" +
//                "Let’s say the total distance is 2X.\n" +
//                "Time for first half is t1=x/20\n" +
//                "Time for second half is t1=x/30\n" +
//                "Total time=t1+t2= x/20+x/30 = (3x+2x)/60\n" +
//                "X(5/60)=x/12\n" +
//                "Avg speed= 2X / (X/12)=24 m/s\n", mcq3Id[0]);
//        MCQOption op34 = new MCQOption("2.4 m/s", false, "avg speed = total distance /total time\n" +
//                "Let’s say the total distance is 2X.\n" +
//                "Time for first half is t1=x/20\n" +
//                "Time for second half is t1=x/30\n" +
//                "Total time=t1+t2= x/20+x/30 = (3x+2x)/60\n" +
//                "X(5/60)=x/12\n" +
//                "Avg speed= 2X / (X/12)=24 m/s\n", mcq3Id[0]);
//
//        mcqOptionDao.insertAll(op31, op32, op33, op34);
//
//        MultipleChoiceQuestion mcq4 = new MultipleChoiceQuestion("Which of the following statements is true with respect to the magnitude of average velocity?", "Choose one correct answer", null, MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.MEDIUM, phyTopicIds[0]);
//        long[] mcq4Id = mcqDao.insertAll(mcq4);
//        String exp4 = "If the object is moving in the straight line than velocity and speed is same otherwise velocity is lesser than speed.";
//
//        MCQOption op41 = new MCQOption("Is always equal to the average speed", true, exp4, mcq4Id[0]);
//        MCQOption op42 = new MCQOption("Is not equal to the average speed", false, exp4, mcq4Id[0]);
//        MCQOption op43 = new MCQOption("May or may not be equal to the average speed", false, exp4, mcq4Id[0]);
//
//        mcqOptionDao.insertAll(op41, op42, op43);
//
//        MultipleChoiceQuestion mcq5 = new MultipleChoiceQuestion("On a square track of Edge length 100 m, an athlete starts from one corner and reaches the diagonally opposite corner.  What is the distance and the displacement of the athlete?", "Choose one correct answer", null, MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.MEDIUM, phyTopicIds[0]);
//        long[] mcq5Id = mcqDao.insertAll(mcq5);
//        String exp5 = "On a square track the distance is 100+100 m which is travelled and the displacement is the shortest distance between the starting point and endpoint.  so the displacement is the length of the diagonal after square. Which we can  find out using Pythagoras theorem.";
//
//        MCQOption op51 = new MCQOption("100 m, 100 sqrt 2", true, exp5, mcq5Id[0]);
//        MCQOption op52 = new MCQOption("200 m, 150 m", false, exp5, mcq5Id[0]);
//        MCQOption op53 = new MCQOption("200 m, 100 m", false, exp5, mcq5Id[0]);
//        MCQOption op54 = new MCQOption("200 m, 100 sqrt 2", true, exp5, mcq5Id[0]);
//
//        mcqOptionDao.insertAll(op51, op52, op53, op54);
//
//        MultipleChoiceQuestion mcq6 = new MultipleChoiceQuestion("A cricket ball is thrown upwards. It reaches a height of 10 m from the players hand and then reaches back to the players hand. What is the distance and displacement of the ball?", "Choose one correct answer", null, MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.MEDIUM, phyTopicIds[0]);
//        long[] mcq6Id = mcqDao.insertAll(mcq6);
//        String exp6 = "If the ball comes back to the players hand so though the distance travelled is 20 m, the displacement is zero";
//
//        MCQOption op61 = new MCQOption("100 m, 100 sqrt 2", true, exp6, mcq6Id[0]);
//        MCQOption op62 = new MCQOption("200 m, 150 m", false, exp6, mcq6Id[0]);
//        MCQOption op63 = new MCQOption("200 m, 100 m", false, exp6, mcq6Id[0]);
//        MCQOption op64 = new MCQOption("200 m, 100 sqrt 2", false, exp6, mcq6Id[0]);
//
//        mcqOptionDao.insertAll(op61, op62, op63, op64);
//
//        String q7 = "Deepak travelled from Delhi to Mumbai by car.  The odometer of the car reads 12758 km at Delhi  and 14058 km in Mumbai. On the return journey Deepak travelled by aeroplane. Let us suppose the aeroplane travels by the shortest path which is 800 km.  What is the distance and displacement travelled by the Deepak?";
//        MultipleChoiceQuestion mcq7 = new MultipleChoiceQuestion(q7, "Choose one correct answer", null, MCQType.SINGLE_ANSWER, QuestionCategory.NUMERICAL, QuestionLevel.MEDIUM, phyTopicIds[0]);
//        long[] mcq7Id = mcqDao.insertAll(mcq7);
//        String exp7 = "Distance travelled is the final reading of odometer - the initial reading of odometer.\n" +
//                "Displacement is the shortest distance between two points which is travelled by the aeroplane\n";
//
//        MCQOption op71 = new MCQOption("-1300km, 800km", false, exp7, mcq7Id[0]);
//        MCQOption op72 = new MCQOption("1300 km, 800 km", true, exp7, mcq7Id[0]);
//        MCQOption op73 = new MCQOption("800 km, 1300 km", false, exp7, mcq7Id[0]);
//        MCQOption op74 = new MCQOption("-800km, -1300 km", false, exp7, mcq7Id[0]);
//
//        mcqOptionDao.insertAll(op71, op72, op73, op74);
//
//        String q8 = "What does the odometer of an automobile measure?";
//        MultipleChoiceQuestion mcq8 = new MultipleChoiceQuestion(q8, "Choose one correct answer", null, MCQType.SINGLE_ANSWER, QuestionCategory.MEMORY_BASED, QuestionLevel.EASY, phyTopicIds[0]);
//        long[] mcq8Id = mcqDao.insertAll(mcq8);
//        String exp8 = "Self Explanatory";
//
//        MCQOption op81 = new MCQOption("Speed", false, exp8, mcq8Id[0]);
//        MCQOption op82 = new MCQOption("Velocity", false, exp8, mcq8Id[0]);
//        MCQOption op83 = new MCQOption("Distance", true, exp8, mcq8Id[0]);
//        MCQOption op84 = new MCQOption("Displacement", false, exp8, mcq8Id[0]);
//
//        mcqOptionDao.insertAll(op81, op82, op83, op84);
//
//        String q9 = "Which of the following quantities can never be negative?";
//        MultipleChoiceQuestion mcq9 = new MultipleChoiceQuestion(q9, "Choose ALL the Correct answers", null, MCQType.MULTIPLE_ANSWER, QuestionCategory.MEMORY_BASED, QuestionLevel.EASY, phyTopicIds[0]);
//        long[] mcq9Id = mcqDao.insertAll(mcq9);
//        String exp9 = "Self Explanatory";
//
//        MCQOption op91 = new MCQOption("Speed", true, exp9, mcq9Id[0]);
//        MCQOption op92 = new MCQOption("Velocity", false, exp9, mcq9Id[0]);
//        MCQOption op93 = new MCQOption("Distance", true, exp9, mcq9Id[0]);
//        MCQOption op94 = new MCQOption("Displacement", false, exp9, mcq9Id[0]);
//
//        mcqOptionDao.insertAll(op91, op92, op93, op94);
//
//        feedPhysicsSlidesChapter1Topic1(db, phyTopicIds[0]);
//        feedPhysicsSlidesChapter1Topic2(db, phyTopicIds[1]);
//        feedPhysicsSlidesChapter1Topic3(db, phyTopicIds[2]);
//
//        feedMath9thTOC(db, subjectGroupIds[2]);
//
//
//        //    =================================================================================
//
//
//
//
//
//
//        EducationBoardDao educationBoardDao = db.educationBoardDao();
//
//        EducationBoard cbse = new EducationBoard("Central Board of Secondary Education", "CBSE");
//
//        educationBoardDao.insertAll(cbse);
//
//        int cbseBoardId = educationBoardDao.findByName(cbse.getName()).getEducationBoardId();
//
//        SchoolDao schoolDao = db.schoolDao();
//
//        School qpsiSchool = new School("QED Public SchoolResponse", "qpsi01", cbseBoardId, "qps", "ORANGE");
//        School ccsrSchool = new School("Carmel Convent SchoolResponse", "ccsr01", cbseBoardId, "ccs", "RED");
//        School bbpsSchool = new School("Bal Bharti Public SchoolResponse", "bbps01", cbseBoardId, "bbps", "BLUE");
//
//        School icsdSchool = new School("The Indian Cambridge SchoolResponse", "icsd01", cbseBoardId, "icsd", "INDIGO");
//        School spsrSchool = new School("Sanskar Public SchoolResponse", "spsr01", cbseBoardId, "spsr", "ORANGE");
//        School apsmSchool = new School("Aditya Public SchoolResponse", "apsm01", cbseBoardId, "apsm", "BLUE");
//
//        School dpsdSchool = new School("Delhi Public SchoolResponse", "dpsm01", cbseBoardId, "dps", "GREEN");
//        School dvmSchool = new School("Dashpur Vidyalay Mandsaur", "dvm001", cbseBoardId, "dvm", "INDIGO");
//        School esmSchool = new School("Edify SchoolResponse", "esm001", cbseBoardId, "esm", "RED");
//
//        School kismSchool = new School("Karni International SchoolResponse", "kism01", cbseBoardId, "kism", "INDIGO");
//        School lvsmSchool = new School("Lotus Valley SchoolResponse", "lvsm01", cbseBoardId, "lvsm", "BLUE");
//        School sdpsdSchool = new School("Shree Dalauda Public SchoolResponse", "sdpsd1", cbseBoardId, "sdpsd", "ORANGE");
//
//        School spssSchool = new School("Sitamau Public SchoolResponse", "spss01", cbseBoardId, "spss", "GREEN");
//        School ssmmSchool = new School("Saraswati Sishu Mandir", "ssmm01", cbseBoardId, "ssmm", "RED");
//        School sspsdSchool = new School("Shri Sai Public SchoolResponse", "sspsd1", cbseBoardId,"sspsd", "GREEN");
//
//        School generic = new School("My SchoolResponse", "000000", cbseBoardId, "msi", "BLUE");
//
//        long[] schoolIds = schoolDao.insertAll(sspsdSchool, qpsiSchool, ccsrSchool, bbpsSchool, icsdSchool, spsrSchool);
//
//        long[] newSchoolIds = schoolDao.insertAll(generic, apsmSchool, dpsdSchool, dvmSchool, esmSchool, kismSchool, lvsmSchool, sdpsdSchool, spssSchool, ssmmSchool);
//        SchoolClassDao schoolClassDao = db.schoolClassDao();
//        ArrayList<long[]> classIds = new ArrayList<>();
//
//        for(long id : schoolIds) {
//            String[] sections = {"A", "B", "C", "D", "E"};
//
//            for(String section : sections) {
//                SchoolClass A6 = new SchoolClass(SchoolClass.SIX, section, id);
//                SchoolClass A7 = new SchoolClass(SchoolClass.SEVEN, section, id);
//                SchoolClass A8 = new SchoolClass(SchoolClass.EIGHT, section, id);
//                SchoolClass A9 = new SchoolClass(SchoolClass.NINE, section, id);
//                SchoolClass A10 = new SchoolClass(SchoolClass.TEN, section, id);
//
//                long[] cids = schoolClassDao.insertAll(A6, A7, A8, A9, A10);
//                classIds.add(cids);
//            }
//        }
//
//        UserDao userDao = db.userDao();
////
////        User u1 = new User("01.9a@ccsr", "Aakansha Agrawal", "01", "0109AQPSI", classIds.get(6)[3], schoolIds[1]);
////        User u2 = new User("02.9a@ccsr", "Aditi Agrawal", "02", "0110AQPSI", classIds.get(6)[3], schoolIds[1]);
////        User u3 = new User("03.9a@ccsr", "Adarsh Agrawal", "03", "0110ACCSR", classIds.get(6)[3], schoolIds[1]);
////        User u4 = new User("1", "Shashank Goyal", "01", "01", classIds.get(6)[3], schoolIds[1]);
////
////        User teacher = new User("teacher", schoolIds[1], "");
////
////        long[] userIds = userDao.insertAll(teacher);
////
////        TeacherSubjectClassDao teacherSubjectClassDao = db.teacherSubjectClassDao();
////
////        TeacherSubjectClass teacherSubjectClass1 = new TeacherSubjectClass(userIds[0], subjectIds[0], classIds.get(6)[3]);
////        TeacherSubjectClass teacherSubjectClass2 = new TeacherSubjectClass(userIds[0], subjectIds[0], classIds.get(7)[3]);
////        TeacherSubjectClass teacherSubjectClass3 = new TeacherSubjectClass(userIds[0], subjectIds[0], classIds.get(8)[3]);
////        TeacherSubjectClass teacherSubjectClass4 = new TeacherSubjectClass(userIds[0], subjectIds[0], classIds.get(9)[3]);
////        TeacherSubjectClass teacherSubjectClass5 = new TeacherSubjectClass(userIds[0], subjectIds[0], classIds.get(10)[3]);
////
////        teacherSubjectClassDao.insertAll(teacherSubjectClass1, teacherSubjectClass2, teacherSubjectClass3, teacherSubjectClass4, teacherSubjectClass5);
//    }
//
//    public static void feedUsers(AppDatabase db) {
//
//    }
//
//    public static void feedAnnouncements(AppDatabase db) {
//        AnnouncementDao announcementDao = db.announcementDao();
//
//        Announcement announcement1 = new Announcement("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", new Date(2018, 8, 30), "Mr. CP Dewangan");
//        Announcement announcement2 = new Announcement("Ut pulvinar cursus dolor varius sodales. Nulla luctus augue nec tortor consectetur venenatis.", new Date(2018, 9, 1), "Mrs. Beena Shahji");
//        Announcement announcement3 = new Announcement("Nulla porttitor a ligula vel aliquet. Integer tempor dolor ac sagittis suscipit.", new Date(2018, 9, 2), "Mrs. Jagriti Prabhakar");
//
//        Announcement announcement4 = new Announcement("Donec gravida augue non semper suscipit. Duis gravida mollis hendrerit.", new Date(2018, 9, 3), "Ms. Nisha George");
//        Announcement announcement5 = new Announcement("Duis mollis pharetra risus, vel auctor nunc finibus at. Donec semper imperdiet tellus, id pellentesque dolor tristique vel.", new Date(2018, 9, 4), "Mr. A Das");
//        Announcement announcement6 = new Announcement("Suspendisse sed volutpat lorem, vitae scelerisque nulla. Aenean non massa ut tortor gravida efficitur id id lacus.", new Date(2018, 9, 5), "Mr. Satish Malakar");
//
//        Announcement announcement7 = new Announcement("Ut pulvinar cursus dolor varius sodales. Nulla luctus augue nec tortor venenatis.", new Date(2018, 9, 6), "Mrs. Beena Shahji");
//        Announcement announcement8 = new Announcement("Nulla porttitor a ligula vel aliquet. Integer tempor dolor ac sagittis consectetur suscipit ut tortor gravida.", new Date(2018, 9, 6), "Mrs. Jagriti Prabhakar");
//        Announcement announcement9 = new Announcement("Suspendisse sed volutpat lorem, vitae scelerisque nulla. Aenean non massa efficitur id id lacus.", new Date(2018, 9, 7), "Mr. Satish Malakar");
//
//        announcementDao.insertAll(announcement1, announcement2, announcement3, announcement4, announcement5, announcement6, announcement7, announcement8, announcement9);
//    }
//
//    public static void feedBadges(AppDatabase db) {
//        BadgeDao badgeDao = db.badgeDao();
//
//        Badge badge1 = new Badge("Space Badge", "Consistency", "badge_space", "This badge is awarded for consistent above par performance in Physics for 6 weeks in a row.", null, true);
//        Badge badge2 = new Badge("Rice Badge", "Consistency", "badge_rice", "This badge is awarded for consistent above par performance in Geography for 6 weeks in a row.", null, true);
//        Badge badge3 = new Badge("Machine Badge", "Excellency", "badge_machine", "This badge is awarded for excellent performance in Physics in a particular month.", null, true);
//
//        Badge badge4 = new Badge("Coffee Badge", "Consistency", "badge_coffee", "This badge is awarded for consistent above par performance in Physics for 6 weeks in a row.", null, false);
//        Badge badge5 = new Badge("Planet Badge", "Consistency", "badge_planet", "This badge is awarded for consistent above par performance in Geography for 6 weeks in a row.", null, false);
//        Badge badge6 = new Badge("Tent Badge", "Excellency", "badge_tent", "This badge is awarded for excellent performance in Physics in a particular month.", null, false);
//
//        badgeDao.insertAll(badge1, badge2, badge3, badge4, badge5, badge6);
//    }
//
//    public static void feedPhysicsSlidesChapter1Topic1(AppDatabase db, long topicId) {
//        RevisionSlideDao revisionSlideDao = db.revisionSlideDao();
//
//        RevisionPointDao revisionPointDao = db.revisionPointDao();
//        DiagramDao diagramDao = db.diagramDao();
//        RevealInteractionDao revealInteractionDao = db.revealInteractionDao();
//        ConfirmInteractionDao confirmInteractionDao = db.confirmInteractionDao();
//
//        MultipleChoiceQuestionDao multipleChoiceQuestionDao = db.multipleChoiceQuestionDao();
//        OneWordAnswerQuestionDao oneWordAnswerQuestionDao = db.oneWordAnswerQuestionDao();
//        MCQOptionDao mcqOptionDao = db.mcqOptionDao();
//
//        RevisionPoint rp1s1 = new RevisionPoint("In this section we will have a look at the elementary concepts of </b>motion</b>, <b>scalar and vector</b> quantities, <b>point objects</b> and <b>distance & displacement</b>.", null, null);
//
//        long[] rps1Ids = revisionPointDao.insertAll(rp1s1);
//        RevisionSlide s1 = new RevisionSlide(1, topicId, SlideType.REVISION_POINT);
//        s1.setRevisionPoint1Id(rps1Ids[0]);
//
//        RevisionPoint rp1s2 = new RevisionPoint("I think you have a fair idea about motion. Isn't it?", "Anything moving is in <b>motion</b> otherwise it's in <b>rest</b>", null);
//        RevisionPoint rp2s2 = new RevisionPoint("Simple enough? Actually... Not so much. Let's take an example", null, null);
//
//        long[] rps2Ids = revisionPointDao.insertAll(rp1s2, rp2s2);
//        RevisionSlide s2 = new RevisionSlide(2, topicId, SlideType.REVISION_POINT);
//        s2.setRevisionPoint1Id(rps2Ids[0]);
//        s2.setRevisionPoint2Id(rps2Ids[1]);
//
//        RevisionPoint rp1s3 = new RevisionPoint(null, null, null);
//        RevealInteraction ris3 = new RevealInteraction("We all know that the earth is rotating, so if I am sitting in my room then I am also moving with the earth. Now can you tell me whether I am in <b>motion</b> or in <b>rest</b>?", "<font color='#ffffff'>Actually... It depends. If you are looking at me from the moon then I am in motion but if you are sitting next to me in my room then with respect to you I am in rest.</font>");
//
//        long[] rp1s3Ids = revisionPointDao.insertAll(rp1s3);
//        long[] ris3Ids = revealInteractionDao.insertAll(ris3);
//
//        RevisionSlide s3 = new RevisionSlide(3, topicId, SlideType.REVISION_POINT);
//        s3.setRevisionPoint1Id(rp1s3Ids[0]);
//        s3.setRevealInteractionId(ris3Ids[0]);
//
//        RevisionPoint rp1s4 = new RevisionPoint("So, in order to understand the concepts of motion, we must first understand the concept of <b>Relativity</b>", "<b>Relative</b> means <font color='#ee0000'>with respect to each other</font>. Indeed the fact is that motion and rest are relative terms, and we always look a the moving things with reference to someone or something.", null);
//        RevisionPoint rp2s4 = new RevisionPoint("Let's take another example to understand better", null, null);
//
//        long[] rps4Ids = revisionPointDao.insertAll(rp1s4, rp2s4);
//        RevisionSlide s4 = new RevisionSlide(4, topicId, SlideType.REVISION_POINT);
//        s4.setRevisionPoint1Id(rps4Ids[0]);
//        s4.setRevisionPoint2Id(rps4Ids[1]);
//
//        RevisionPoint rp1s5 = new RevisionPoint(null, null, null);
//        RevealInteraction ris5 = new RevealInteraction("Suppose you are sleeping on a birth in the moving train -  do you think - you are in the state of <b>motion</b> or in <b>rest</b>?", "<font color='#ffffff'>You actually are in motion wih respect to outer world but in the state of rest with respect to the passengers in the train. And this exactly is the principle of relativity.</font>");
//
//        long[] rp1s5Ids = revisionPointDao.insertAll(rp1s5);
//        long[] ris5Ids = revealInteractionDao.insertAll(ris5);
//
//        RevisionSlide s5 = new RevisionSlide(5, topicId, SlideType.REVISION_POINT);
//        s5.setRevisionPoint1Id(rp1s5Ids[0]);
//        s5.setRevealInteractionId(ris5Ids[0]);
//
//        RevisionPoint rps6 = new RevisionPoint("You know that when an object moves it has certain <b>speed</b> or <b>velocity</b> and it covers some <b>distance</b> or <b>displacement</b>. In order to understand all these things you must have a clear concept of <b>Vector</b>.", null, null);
//        ConfirmInteraction cis6 = new ConfirmInteraction("Do you know what is a <b>scalar</b> and <b>vector</b> quantity?");
//
//        long[] rps6Ids = revisionPointDao.insertAll(rps6);
//        long[] cis6Ids = confirmInteractionDao.insertAll(cis6);
//
//        RevisionSlide s6 = new RevisionSlide(6, topicId, SlideType.REVISION_POINT);
//        s6.setRevisionPoint1Id(rps6Ids[0]);
//        s6.setConfirmInteractionId(cis6Ids[0]);
//
//        RevisionPoint rp1s7 = new RevisionPoint("OK let's learn what is scalar and vector", "<b>Scalar</b> means  <font color='#ee0000'>only magnitude</font> and <b>vector</b> means  <font color='#ee0000'>magnitude with direction</font>.", null);
//        RevisionPoint rp2s7 = new RevisionPoint(null, "Thus <b>vector</b> is any physical quantity that  <font color='#ee0000'>has both magnitude and direction</font>, where as <b>scalar</b>  <font color='#ee0000'>has only magnitude - no direction</font>.", null);
//
//        long[] rps7Ids = revisionPointDao.insertAll(rp1s7, rp2s7);
//        RevisionSlide s7 = new RevisionSlide(7, topicId, SlideType.REVISION_POINT);
//        s7.setRevisionPoint1Id(rps7Ids[0]);
//        s7.setRevisionPoint2Id(rps7Ids[1]);
//
//        MultipleChoiceQuestion mcqs8 = new MultipleChoiceQuestion("Which of the following is a vector quantity?", "Choose the correct answer", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs8Ids = multipleChoiceQuestionDao.insertAll(mcqs8);
//
//        MCQOption mcqs8op1 = new MCQOption("Mass", false, "NOPE. It is FORCE. Force is always applied in a particular direction.", mcqs8Ids[0]);
//        MCQOption mcqs8op2 = new MCQOption("Distance", false, "NOPE. It is FORCE. Force is always applied in a particular direction.",  mcqs8Ids[0]);
//        MCQOption mcqs8op3 = new MCQOption("Force", true, "Exactly! Because force is always applied in a particular direction.",  mcqs8Ids[0]);
//        MCQOption mcqs8op4 = new MCQOption("Speed", false, "NOPE. It is FORCE. Force is always applied in a particular direction.",  mcqs8Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs8op1, mcqs8op2, mcqs8op3, mcqs8op4);
//
//        RevisionSlide s8 = new RevisionSlide(8, topicId, SlideType.REVISION_QUESTION);
//        s8.setMcqId(mcqs8Ids[0]);
//
//        RevisionPoint rp1s9 = new RevisionPoint("Now as you have understood scalar and vector, let's see how can we measure them.", null, null);
//        RevealInteraction ris9 = new RevealInteraction("Let us assume that a car is running at 50 km per hour towards North, then tell me what 50 represents, what km/h represents, and what does North represent?", "<font color='#ffffff'>50 is the magnitude, km/hour is the unit of measurement and North is the direction.</font>");
//
//        long[] rp1s9Ids = revisionPointDao.insertAll(rp1s9);
//        long[] ris9Ids = revealInteractionDao.insertAll(ris9);
//
//        RevisionSlide s9 = new RevisionSlide(9, topicId, SlideType.REVISION_POINT);
//        s9.setRevisionPoint1Id(rp1s9Ids[0]);
//        s9.setRevealInteractionId(ris9Ids[0]);
//
//        RevisionPoint rp1s10 = new RevisionPoint("So,  <font color='#ee0000'>magnitude means value</font> and values can be converted from one unit of measurement to another unit of measurement. So, Let's solve the next question", null, null);
//
//        long[] rps10Ids = revisionPointDao.insertAll(rp1s10);
//        RevisionSlide s10 = new RevisionSlide(10, topicId, SlideType.REVISION_POINT);
//        s10.setRevisionPoint1Id(rps10Ids[0]);
//
//        OneWordAnswerQuestion owaqs11 = new OneWordAnswerQuestion("Convert 36 km/hr to m/s", "Enter only the numeric value", "10", QuestionLevel.EASY, QuestionCategory.CONCEPTUAL, " 1km = 1000m and 1 hour = 60 min = 60*60 sec. Thus <br> \\( \\frac{36 km}{hr} = \\frac{36 \\times 1000 m}{1 \\times 60 \\times 60 sec}  \\) <br> \\( = \\frac{360}{36} \\) m/s <br> \\( = 10 \\) m/s", topicId);
//        long[] owaqs11Ids = oneWordAnswerQuestionDao.insertAll(owaqs11);
//
//        RevisionSlide s11 = new RevisionSlide(11, topicId, SlideType.REVISION_QUESTION);
//        s11.setOwaqId(owaqs11Ids[0]);
//
//        RevisionPoint rp1s12 = new RevisionPoint("Now we have everything which is required to describe <b>Distance and Displacement</b>.", "<b>Distance</b> is length or  <font color='#ee0000'>distance traveled</font> and is a <b>scalar</b> quantity where as <b>Displacement</b> is the  <font color='#ee0000'>shortest distance between two points along with its direction</font>.", null);
//        RevisionPoint rp2s12 = new RevisionPoint("Let us now look at the next question", null, null);
//
//        long[] rps12Ids = revisionPointDao.insertAll(rp1s12, rp2s12);
//        RevisionSlide s12 = new RevisionSlide(12, topicId, SlideType.REVISION_POINT);
//        s12.setRevisionPoint1Id(rps12Ids[0]);
//        s12.setRevisionPoint2Id(rps12Ids[1]);
//
//        MultipleChoiceQuestion mcqs13 = new MultipleChoiceQuestion("A ball is thrown vertically upward. It goes up to the height of 5 m and comes back to the thrower. Can you tell in this case which statement is correct?", "Check all the answers", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs13Ids = multipleChoiceQuestionDao.insertAll(mcqs13);
//
//        MCQOption mcqs13op1 = new MCQOption("Total distance covered by the ball is zero", false, "WRONG. Since the ball is going up 5 m and coming back thus total distance covered is 10 m.", mcqs13Ids[0]);
//        MCQOption mcqs13op2 = new MCQOption("Total displacement is 10 m", false, "WRONG. Since the ball is going up 5 m and coming back to the same point thus total distance between initial and final location is zero and so the displacement is 0 m.",  mcqs13Ids[0]);
//        MCQOption mcqs13op3 = new MCQOption("Total displacement of the ball is zero", true, "That is correct",  mcqs13Ids[0]);
//        MCQOption mcqs13op4 = new MCQOption("Total displacement of the ball is 5 m", false, "WRONG. Since the ball is going up 5 m and coming back to the same point thus total distance between initial and final location is zero and so the displacement is 0 m.",  mcqs13Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs13op1, mcqs13op2, mcqs13op3, mcqs13op4);
//
//        RevisionSlide s13 = new RevisionSlide(13, topicId, SlideType.REVISION_QUESTION);
//        s13.setInteractive(true);
//        s13.setMcqId(mcqs13Ids[0]);
//
//        RevisionPoint rps14 = new RevisionPoint("Let's understand the difference between distance and displacement with a figure.", "A person travels from A to B to C to D and comes back to A again", null);
//        Diagram ds14 = new Diagram(null, "distance_vs_displacement", "In this case, though the distance traveled is 24m, but the displacement is 0.");
//
//        long[] rps14Ids = revisionPointDao.insertAll(rps14);
//        long[] ds14Ids = diagramDao.insertAll(ds14);
//
//        RevisionSlide s14 = new RevisionSlide(14, topicId, SlideType.REVISION_POINT);
//        s14.setRevisionPoint1Id(rps14Ids[0]);
//        s14.setDiagramId(ds14Ids[0]);
//
//        MultipleChoiceQuestion mcqs15 = new MultipleChoiceQuestion("As you know that the circumference of the circle is 2&#960;r. If a person travels a distance &#960;R along the circumference of the circle, tell me the displacement of the person", "Choose the ONE correct answer", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs15Ids = multipleChoiceQuestionDao.insertAll(mcqs15);
//
//        MCQOption mcqs15op1 = new MCQOption("r", false, "WRONG. Person starts from a point and reaches just opposite and the shortest distance between start and end point is the diameter of the circle 2r.", mcqs15Ids[0]);
//        MCQOption mcqs15op2 = new MCQOption("2r", true, "That is correct",  mcqs15Ids[0]);
//        MCQOption mcqs15op3 = new MCQOption("2&#960;r", false, "WRONG. Person starts from a point and reaches just opposite. If he travels 2&#960;r then he will come back to the starting point and displacement will be 0.",  mcqs15Ids[0]);
//        MCQOption mcqs15op4 = new MCQOption("Zero", false, "NO. Person starts from a point and reaches just opposite. Displacement will be zero only if he comes back to the starting point",  mcqs15Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs15op1, mcqs15op2, mcqs15op3, mcqs15op4);
//
//        RevisionSlide s15 = new RevisionSlide(15, topicId, SlideType.REVISION_QUESTION);
//        s15.setInteractive(true);
//        s15.setMcqId(mcqs15Ids[0]);
//
//        revisionSlideDao.insertAll(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15);
//    }
//
//    public static void feedPhysicsSlidesChapter1Topic2(AppDatabase db, long topicId) {
//        RevisionSlideDao revisionSlideDao = db.revisionSlideDao();
//
//        RevisionPointDao revisionPointDao = db.revisionPointDao();
//        DiagramDao diagramDao = db.diagramDao();
//        RevealInteractionDao revealInteractionDao = db.revealInteractionDao();
//        ConfirmInteractionDao confirmInteractionDao = db.confirmInteractionDao();
//
//        MultipleChoiceQuestionDao multipleChoiceQuestionDao = db.multipleChoiceQuestionDao();
//        OneWordAnswerQuestionDao oneWordAnswerQuestionDao = db.oneWordAnswerQuestionDao();
//        MCQOptionDao mcqOptionDao = db.mcqOptionDao();
//
//        RevisionPoint rp1s1 = new RevisionPoint("This section explains the concepts of <b>Speed</b> and <b>Velocity</b> and their relationship with Time, Distance and Displacement", null, null);
//        long[] rps1Ids = revisionPointDao.insertAll(rp1s1);
//
//        RevisionSlide s1 = new RevisionSlide(1, topicId, SlideType.REVISION_POINT);
//        s1.setRevisionPoint1Id(rps1Ids[0]);
//
//        RevisionPoint rp1s2 = new RevisionPoint("You probably understand what speed is but do you know what is velocity?", "<b>Speed</b> says <font color='#ee0000'>how fast an object is moving</font> where as <b>velocity</b> says how fast an object is moving <font color='#ee0000'>in a particular direction</font>.", null);
//        RevisionPoint rp2s2 = new RevisionPoint(null, "Thus <b>Velocity</b> is <font color='#ee0000'>Speed with direction</font> and so velocity is a <font color='#ee0000'>Vector</font> quantity where as <b>speed</b> is a <font color='#ee0000'>scalar</font> quantity.", null);
//        long[] rps2Ids = revisionPointDao.insertAll(rp1s2, rp2s2);
//        RevisionSlide s2 = new RevisionSlide(2, topicId, SlideType.REVISION_POINT);
//
//        s2.setRevisionPoint1Id(rps2Ids[0]);
//        s2.setRevisionPoint2Id(rps2Ids[1]);
//
//        RevisionPoint rps3 = new RevisionPoint(null, null, null);
//        RevealInteraction ris3 = new RevealInteraction("Can you tell me what does the speedometer of the bike tells you - speed of the bike or velocity of the bike?", "<font color='#ffffff'>SPEED</font>");
//
//        long[] rps3Ids = revisionPointDao.insertAll(rps3);
//        long[] ris3Ids = revealInteractionDao.insertAll(ris3);
//
//        RevisionSlide s3 = new RevisionSlide(3, topicId, SlideType.REVISION_POINT);
//        s3.setRevisionPoint1Id(rps3Ids[0]);
//        s3.setRevealInteractionId(ris3Ids[0]);
//
//        RevisionPoint rp1s4 = new RevisionPoint("You probably know how to measure the speed but do you also know how to measure velocity?", "<b>Speed</b> is measured in terms of <font color='#ee0000'>distance travelled in unit time</font> where as <b>velocity</b> is measured as <font color='#ee0000'>displacement covered in unit time</font>.", null);
//        RevisionPoint rp2s4 = new RevisionPoint(null, "Obviously <b>speed</b> is related to <font color='#ee0000'>distance</font> where as <b>velocity</b> is related to <font color='#ee0000'>displacement</font>.", null);
//
//        long[] rps4Ids = revisionPointDao.insertAll(rp1s4, rp2s4);
//        RevisionSlide s4 = new RevisionSlide(4, topicId, SlideType.REVISION_POINT);
//        s4.setRevisionPoint1Id(rps4Ids[0]);
//        s4.setRevisionPoint2Id(rps4Ids[1]);
//
//        RevisionPoint rp1s5 = new RevisionPoint("We can measure Speed and Velocity as below", "\\( Speed = \\frac{Distance}{Time} \\) <br><br> \\( Velocity = \\frac{Displacement}{Time} \\) <br><br>Unit of both is m/s but then velocity has a direction associated with it", null);
//        long[] rps5Ids = revisionPointDao.insertAll(rp1s5);
//
//        RevisionSlide s5 = new RevisionSlide(5, topicId, SlideType.REVISION_POINT);
//        s5.setRevisionPoint1Id(rps5Ids[0]);
//
//        MultipleChoiceQuestion mcqs6 = new MultipleChoiceQuestion("How can we change the velocity of a car moving with an speed of 30 km/h?", "Check all the answers", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs6Ids = multipleChoiceQuestionDao.insertAll(mcqs6);
//
//        MCQOption mcqs6op1 = new MCQOption("By changing the speed in the same direction", false, "OK! But this is just one of the way to change velocity", mcqs6Ids[0]);
//        MCQOption mcqs6op2 = new MCQOption("By changing the direction and keeping speed same", false, "OK! But this is just one of the way to change velocity",  mcqs6Ids[0]);
//        MCQOption mcqs6op3 = new MCQOption("By changing the speed or direction or both", true, "Correct. Velocity changes if speed changes or direction changes or both change simultaneously",  mcqs6Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs6op1, mcqs6op2, mcqs6op3);
//
//        RevisionSlide s6 = new RevisionSlide(6, topicId, SlideType.REVISION_QUESTION);
//        s6.setInteractive(true);
//        s6.setMcqId(mcqs6Ids[0]);
//
//        RevisionPoint rps7 = new RevisionPoint("Let us explore it further with an example", null, null);
//        RevealInteraction ris7 = new RevealInteraction("If a car is moving on a straight road with 40 km/h and suddenly drivers applies break and the speed comes down to 20 km/h. Under this condition what gets changed. Speed, Velocity or Both?", "<font color='#ffffff'>Both. Obviously the speed is changing and as speed changes velocity also changes simultaneously.</font>");
//
//        long[] rps7Ids = revisionPointDao.insertAll(rps7);
//        long[] ris7Ids = revealInteractionDao.insertAll(ris7);
//
//        RevisionSlide s7 = new RevisionSlide(7, topicId, SlideType.REVISION_POINT);
//        s7.setRevisionPoint1Id(rps7Ids[0]);
//        s7.setRevealInteractionId(ris7Ids[0]);
//
//        RevisionPoint rps8 = new RevisionPoint(null, null, null);
//        Diagram ds8 = new Diagram(null, "polynomial", "");
//
//        long[] rps8Ids = revisionPointDao.insertAll(rps8);
//        long[] ds8Ids = diagramDao.insertAll(ds8);
//
//        RevisionSlide s8 = new RevisionSlide(8, topicId, SlideType.REVISION_POINT);
//        s8.setRevisionPoint1Id(rps8Ids[0]);
//        s8.setDiagramId(ds8Ids[0]);
//
//        MultipleChoiceQuestion mcqs9 = new MultipleChoiceQuestion("If a car runs with a speed of 120 kmph then in 15 minutes what distance it will travel", "Choose the ONE correct answer", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs9Ids = multipleChoiceQuestionDao.insertAll(mcqs9);
//        String exps9 = "120 km/h = 120 km / 60 min <br> = 2 km/min.<br><br> As it covers 2 km in one min so in 15 min it will cover 15*2=30km";
//
//        MCQOption mcqs9op1 = new MCQOption("15 km", false, exps9, mcqs9Ids[0]);
//        MCQOption mcqs9op2 = new MCQOption("30 km", true, exps9,  mcqs9Ids[0]);
//        MCQOption mcqs9op3 = new MCQOption("40 km", false, exps9,  mcqs9Ids[0]);
//        MCQOption mcqs9op4 = new MCQOption("20 km", false, exps9,  mcqs9Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs9op1, mcqs9op2, mcqs9op3, mcqs9op4);
//
//        RevisionSlide s9 = new RevisionSlide(9, topicId, SlideType.REVISION_QUESTION);
//        s9.setMcqId(mcqs9Ids[0]);
//
//        RevisionPoint rp1s10 = new RevisionPoint("Let's see what do we mean by uniform and non uniform speed and velocity", "<b>Uniform</b> means <font color='#ee0000'>same or unchanged</font>. If a car travels 10 km in 10 min, another 10 km in another 10 min and so on then it's uniform speed", null);
//        RevisionPoint rp2s10 = new RevisionPoint(null, "If an object travels <font color='#ee0000'>equal distance in equal intervals of time</font> then it is moving with <b>uniform (constant) speed</b>. Otherwise it is called <b>non-uniform speed</b>", null);
//        long[] rps10Ids = revisionPointDao.insertAll(rp1s10, rp2s10);
//
//        RevisionSlide s10 = new RevisionSlide(10, topicId, SlideType.REVISION_POINT);
//        s10.setRevisionPoint1Id(rps10Ids[0]);
//        s10.setRevisionPoint2Id(rps10Ids[1]);
//
//        RevisionPoint rp1s11 = new RevisionPoint("Uniform speed is ok but what about Uniform velocity?", "Obviously, it is the  velocity that remains uniform (constant) over the concerned period of time", null);
//        RevisionPoint rp2s11 = new RevisionPoint("But, Under what conditions does the velocity remain uniform?", "If an object moves <font color='#ee0000'>equal distances in equal intervals of time in a specified direction</font> then it moves with uniform velocity.", null);
//        long[] rps11Ids = revisionPointDao.insertAll(rp1s11, rp2s11);
//
//        RevisionSlide s11 = new RevisionSlide(11, topicId, SlideType.REVISION_POINT);
//        s11.setRevisionPoint1Id(rps11Ids[0]);
//        s11.setRevisionPoint2Id(rps11Ids[1]);
//
//        RevisionPoint rp1s12 = new RevisionPoint("Now let's have a look at average speed and average velocity", "\\[ Avg. Speed = \\frac{Total Distance Travelled}{Total Time Taken} \\]", null);
//        RevisionPoint rp2s12 = new RevisionPoint("Similarly", "\\( {Avg.Velocity} = \\frac{Total Displacement}{Total Time} \\) <br><br>but in this case you have to mention the <font color='#ee0000'>direction too</font>", null);
//        long[] rps12Ids = revisionPointDao.insertAll(rp1s12, rp2s12);
//
//        RevisionSlide s12 = new RevisionSlide(12, topicId, SlideType.REVISION_POINT);
//        s12.setRevisionPoint1Id(rps12Ids[0]);
//        s12.setRevisionPoint2Id(rps12Ids[1]);
//
//        OneWordAnswerQuestion owaqs13 = new OneWordAnswerQuestion("If a car travels 40 km in first hour, another 20 km in next hour and another 30 km in next hour then what is its average speed in km/hr?", "Enter only the numeric value", "30", QuestionLevel.EASY, QuestionCategory.CONCEPTUAL, "\\( Avg.Speed = \\frac{Total Distance}{Total Time} \\) <br> \\( = \\frac{40+20+30}{3} \\) <br> \\( = \\frac{90}{3} \\) <br> \\( = 30 km/h \\)", topicId);
//        long[] owaqs13Ids = oneWordAnswerQuestionDao.insertAll(owaqs13);
//
//        RevisionSlide s13 = new RevisionSlide(13, topicId, SlideType.REVISION_QUESTION);
//        s13.setOwaqId(owaqs13Ids[0]);
//
//        MultipleChoiceQuestion mcqs14 = new MultipleChoiceQuestion("You walk from home to shop (100 m) in 40 seconds but you realized that you forgot to take your cellphone. So, you turn around and reach home in 60 more seconds: What is your round-trip speed and velocity?", "Choose the ONE correct answer", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs14Ids = multipleChoiceQuestionDao.insertAll(mcqs14);
//        String exp14 = "\\( Avg.Speed = \\frac{TotalDistance}{TotalTime} \\) <br> \\( = \\frac{200m}{100s} \\) <br> \\( = 2m/s \\) <br><br> \\( Avg.Velocity = \\frac{TotalDisplacement}{TotalTime} \\) <br> \\( = \\frac{0m}{100s} \\) <br> \\( = 0 m/s \\)";
//
//        MCQOption mcqs14op1 = new MCQOption("2 m/s and 2 m/s", false, exp14, mcqs14Ids[0]);
//        MCQOption mcqs14op2 = new MCQOption("2 m/s and 0 m/s", true, exp14,  mcqs14Ids[0]);
//        MCQOption mcqs14op3 = new MCQOption("0 m/s and 0 m/s", false, exp14,  mcqs14Ids[0]);
//        MCQOption mcqs14op4 = new MCQOption("0 m/s and 2 m/s", false, exp14,  mcqs14Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs14op1, mcqs14op2, mcqs14op3, mcqs14op4);
//
//        RevisionSlide s14 = new RevisionSlide(14, topicId, SlideType.REVISION_QUESTION);
//        s14.setMcqId(mcqs14Ids[0]);
//
//        RevisionPoint rp1s15 = new RevisionPoint("Lastly we'll see what do we mean by Instantaneous speed and Instantaneous velocity", "We often say that it was an instantaneous reaction. Instantaneous means <font color='#ee0000'>at that point of time</font> or at that moment.", null);
//        RevisionPoint rp2s15 = new RevisionPoint(null, "The speed of a body <font color='#ee0000'>at a given instant</font> is called its <b>Instantaneous speed</b>. Similarly, the velocity of a body <font color='#ee0000'>at a given instant</font> is called its <b>Instantaneous velocity</b>.", null);
//        long[] rps15Ids = revisionPointDao.insertAll(rp1s15, rp2s15);
//
//        RevisionSlide s15 = new RevisionSlide(15, topicId, SlideType.REVISION_POINT);
//        s15.setRevisionPoint1Id(rps15Ids[0]);
//        s15.setRevisionPoint2Id(rps15Ids[1]);
//
//        MultipleChoiceQuestion mcqs16 = new MultipleChoiceQuestion("What does the Speedometer of the car tell you?", "Choose the ONE correct answer", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs16Ids = multipleChoiceQuestionDao.insertAll(mcqs16);
//        String exp16 = "Speedometer shows the speed of the vehicle at that instant of time. It never consider the direction so it does not show instantaneous velocity";
//        MCQOption mcqs16op1 = new MCQOption("Average speed", false, exp16, mcqs16Ids[0]);
//        MCQOption mcqs16op2 = new MCQOption("Instantaneous speed ", false, exp16,  mcqs16Ids[0]);
//        MCQOption mcqs16op3 = new MCQOption("Average velocity", true, exp16,  mcqs16Ids[0]);
//        MCQOption mcqs16op4 = new MCQOption("Instantaneous velocity", false, exp16,  mcqs16Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs16op1, mcqs16op2, mcqs16op3, mcqs16op4);
//
//        RevisionSlide s16 = new RevisionSlide(16, topicId, SlideType.REVISION_QUESTION);
//        s16.setMcqId(mcqs16Ids[0]);
//
//        revisionSlideDao.insertAll(s1, s2, s3, s4, s5, s6, s7, s9, s10, s11, s12, s13, s14, s15, s16);
//    }
//
//    public static void feedPhysicsSlidesChapter1Topic3(AppDatabase db, long topicId) {
//        RevisionSlideDao revisionSlideDao = db.revisionSlideDao();
//
//        RevisionPointDao revisionPointDao = db.revisionPointDao();
//        DiagramDao diagramDao = db.diagramDao();
//        RevealInteractionDao revealInteractionDao = db.revealInteractionDao();
//        ConfirmInteractionDao confirmInteractionDao = db.confirmInteractionDao();
//
//        MultipleChoiceQuestionDao multipleChoiceQuestionDao = db.multipleChoiceQuestionDao();
//        OneWordAnswerQuestionDao oneWordAnswerQuestionDao = db.oneWordAnswerQuestionDao();
//        MCQOptionDao mcqOptionDao = db.mcqOptionDao();
//
//        RevisionPoint rp1s1 = new RevisionPoint("In this part we will study the concepts of Acceleration and Deceleration", null, null);
//        long[] rps1Ids = revisionPointDao.insertAll(rp1s1);
//
//        RevisionSlide s1 = new RevisionSlide(1, topicId, SlideType.REVISION_POINT);
//        s1.setRevisionPoint1Id(rps1Ids[0]);
//
//        RevisionPoint rp1s2 = new RevisionPoint("Let's see what acceleration actually is.", "<b>Acceleration</b> is a quantity that helps us understanding how fast or <font color='#ee0000'>how slow the velocity of an object is changing</font>.", null);
//        RevisionPoint rp2s2 = new RevisionPoint(null, "In a non-uniform motion velocity varies with time. <font color='#ee0000'>The rate at which velocity changes</font> is called <b>acceleration</b>.", null);
//        long[] rps2Ids = revisionPointDao.insertAll(rp1s2, rp2s2);
//        RevisionSlide s2 = new RevisionSlide(2, topicId, SlideType.REVISION_POINT);
//        s2.setRevisionPoint1Id(rps2Ids[0]);
//        s2.setRevisionPoint2Id(rps2Ids[1]);
//
//        RevisionPoint rp1s3 = new RevisionPoint(null, "If <font color='#ee0000'>velocity is increasing</font> then <b>acceleration</b> is in the direction of the velocity and is called  acceleration", null);
//        RevisionPoint rp2s3 = new RevisionPoint(null, "If the <font color='#ee0000'>velocity is decreasing</font> then acceleration is in the direction opposite to the direction of velocity and then it is called <b>deceleration</b> or <b>retardation</b>. This is -ve acceleration.", null);
//        long[] rps3Ids = revisionPointDao.insertAll(rp1s3, rp2s3);
//
//        RevisionSlide s3 = new RevisionSlide(3, topicId, SlideType.REVISION_POINT);
//        s3.setRevisionPoint1Id(rps3Ids[0]);
//        s3.setRevisionPoint2Id(rps3Ids[1]);
//
//        RevisionPoint rp1s4 = new RevisionPoint("As we know, acceleration is change in Velocity. But, do you know what does velocity depend on?", "It depends on speed and direction and so <font color='#ee0000'>acceleration also changes if speed changes or direction changes or both change</font>.", null);
//        RevisionPoint rp2s4 = new RevisionPoint(null, "As velocity is a <font color='#ee0000'>vector</font> quantity so <b>acceleration</b> is also a vector quantity", null);
//        long[] rps4Ids = revisionPointDao.insertAll(rp1s4, rp2s4);
//
//        RevisionSlide s4 = new RevisionSlide(4, topicId, SlideType.REVISION_POINT);
//        s4.setRevisionPoint1Id(rps4Ids[0]);
//        s4.setRevisionPoint2Id(rps4Ids[1]);
//
//        RevisionPoint rp1s5 = new RevisionPoint("Let's see how do we calculate acceleration", "If the velocity of an object changes from an <font color='#ee0000'>initial velocity \\( u \\) </font> to the <font color='#ee0000'> final velocity \\( v \\) </font>, in <font color='#ee0000'> time \\( t \\) </font>, then <font color='#ee0000'> acceleration \\( a \\) </font> is given by <br><br> \\( a = \\frac{ChangeInVelocity}{TimeTaken} \\) <br> \\( a = \\frac{v-u}{t} \\)<br>unit \\( m/s^2 \\)", null);
//        RevisionPoint rp2s5 = new RevisionPoint("Let's move further to understand it better", null, null);
//        long[] rps5Ids = revisionPointDao.insertAll(rp1s5, rp2s5);
//
//        RevisionSlide s5 = new RevisionSlide(5, topicId, SlideType.REVISION_POINT);
//        s5.setRevisionPoint1Id(rps5Ids[0]);
//        s5.setRevisionPoint2Id(rps5Ids[1]);
//
//        RevisionPoint rps6 = new RevisionPoint(null, null, null);
//        RevealInteraction ris6 = new RevealInteraction("When you start your car on a straight road, the speed increases from 0 to 60. Do you think that there is acceleration?", "<font color='#ffffff'>YES! It's acceleration because speed is changing.</font>");
//
//        long[] rps6Ids = revisionPointDao.insertAll(rps6);
//        long[] ris6Ids = revealInteractionDao.insertAll(ris6);
//
//        RevisionSlide s6 = new RevisionSlide(6, topicId, SlideType.REVISION_POINT);
//        s6.setRevisionPoint1Id(rps6Ids[0]);
//        s6.setRevealInteractionId(ris6Ids[0]);
//
//        RevisionPoint rps7 = new RevisionPoint(null, null, null);
//        RevealInteraction ris7 = new RevealInteraction("Now the speed of your car is 60 and you travel with this speed on a straight road for an hour. What do you think - is it accelerating?", "<font color='#ffffff'>NO. Acceleration is 0. As there is no change in the speed or direction so no change in velocity and so there is no acceleration.</font>");
//
//        long[] rps7Ids = revisionPointDao.insertAll(rps7);
//        long[] ris7Ids = revealInteractionDao.insertAll(ris7);
//
//        RevisionSlide s7 = new RevisionSlide(7, topicId, SlideType.REVISION_POINT);
//        s7.setRevisionPoint1Id(rps7Ids[0]);
//        s7.setRevealInteractionId(ris7Ids[0]);
//
//        RevisionPoint rps8 = new RevisionPoint(null, null, null);
//        RevealInteraction ris8 = new RevealInteraction("With the same speed (60 kmph) if you take a turn then what do you think about acceleration?", "<font color='#ffffff'>In this case the speed is constant but as the  direction is changing so velocity is changing and so you have an acceleration</font>");
//
//        long[] rps8Ids = revisionPointDao.insertAll(rps8);
//        long[] ris8Ids = revealInteractionDao.insertAll(ris8);
//
//        RevisionSlide s8 = new RevisionSlide(8, topicId, SlideType.REVISION_POINT);
//        s8.setRevisionPoint1Id(rps8Ids[0]);
//        s8.setRevealInteractionId(ris8Ids[0]);
//
//        RevisionPoint rps9 = new RevisionPoint(null, null, null);
//        RevealInteraction ris9 = new RevealInteraction("Finally you slowdown your car and stop it. Is there any acceleration?", "<font color='#ffffff'>YES. As the speed is changing so there is acceleration but as the initial velocity is greater than the final velocity (0), it is negative acceleration (deceleration or retardation)</font>");
//
//        long[] rps9Ids = revisionPointDao.insertAll(rps9);
//        long[] ris9Ids = revealInteractionDao.insertAll(ris9);
//
//        RevisionSlide s9 = new RevisionSlide(9, topicId, SlideType.REVISION_POINT);
//        s9.setRevisionPoint1Id(rps9Ids[0]);
//        s9.setRevealInteractionId(ris9Ids[0]);
//
//        RevisionPoint rp1s10 = new RevisionPoint("The way we have uniform speed and velocity similarly we have uniform acceleration as well", "<b>Uniform acceleration</b> means <font color='#ee0000'>acceleration remains same or constant</font>.", null);
//        RevisionPoint rp2s10 = new RevisionPoint("When does it happen?", null, null);
//        long[] rps10Ids = revisionPointDao.insertAll(rp1s10, rp2s10);
//
//        RevisionSlide s10 = new RevisionSlide(10, topicId, SlideType.REVISION_POINT);
//        s10.setRevisionPoint1Id(rps10Ids[0]);
//        s10.setRevisionPoint2Id(rps10Ids[1]);
//
//        RevisionPoint rp1s11 = new RevisionPoint("Two condition for uniform acceleration", "1. <font color='#ee0000'>Object must travel in a straight line</font> because if the direction changes then velocity will change and then acceleration will also change.<br><br>2. <font color='#ee0000'>Velocity increases by equal amount in equal intervals of time</font>", null);
//        RevisionPoint rp2s11 = new RevisionPoint("It means for that Uniform acceleration to take place, Velocity must change in a Uniform way, i.e, Velocity must increase or decrease uniformly.<br><br>Let us look at an example", null, null);
//        long[] rps11Ids = revisionPointDao.insertAll(rp1s11, rp2s11);
//
//        RevisionSlide s11 = new RevisionSlide(11, topicId, SlideType.REVISION_POINT);
//        s11.setRevisionPoint1Id(rps11Ids[0]);
//        s11.setRevisionPoint2Id(rps11Ids[1]);
//
//        RevisionPoint rp1s12 = new RevisionPoint(null, "If the speed of the car is 10 in first min, 20 in second min, 30 in the third and so on then speed is increasing by 10 in each interval of time (i.e. each min). This is uniform acceleration. But if speed becomes 35 in 4th min then it's non uniform acceleration.", null);
//        RevisionPoint rp2s12 = new RevisionPoint("Some examples of Uniform acceleration", "Freely falling bodies, motion of ball rolling down the inclined plane etc.", null);
//        long[] rps12Ids = revisionPointDao.insertAll(rp1s12, rp2s12);
//
//        RevisionSlide s12 = new RevisionSlide(12, topicId, SlideType.REVISION_POINT);
//        s12.setRevisionPoint1Id(rps12Ids[0]);
//        s12.setRevisionPoint2Id(rps12Ids[1]);
//
//        MultipleChoiceQuestion mcqs13 = new MultipleChoiceQuestion("When an object is moving with uniform velocity, what is its acceleration?", "Choose the ONE correct answer", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs13Ids = multipleChoiceQuestionDao.insertAll(mcqs13);
//        String exp13 = "Uniform velocity means no change in velocity and so there will be Zero acceleration.";
//
//        MCQOption mcqs13op1 = new MCQOption("Zero", true, exp13, mcqs13Ids[0]);
//        MCQOption mcqs13op2 = new MCQOption("Uniform", false, exp13,  mcqs13Ids[0]);
//        MCQOption mcqs13op3 = new MCQOption("Non-Uniform", false, exp13,  mcqs13Ids[0]);
//        MCQOption mcqs13op4 = new MCQOption("Negative", false, exp13,  mcqs13Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs13op1, mcqs13op2, mcqs13op3, mcqs13op4);
//
//        RevisionSlide s13 = new RevisionSlide(13, topicId, SlideType.REVISION_QUESTION);
//        s13.setMcqId(mcqs13Ids[0]);
//
//        RevisionPoint rp1s14 = new RevisionPoint("In case of Uniform acceleration, we can calculate the average velocity as below.", "For Uniform Acceleration, the velocity of the object changes at a uniform rate. In this case, the average velocity is given by \\[ V_{av} = \\frac{v-u}{2} \\]", null);
//        long[] rps14Ids = revisionPointDao.insertAll(rp1s14);
//
//        RevisionSlide s14 = new RevisionSlide(14, topicId, SlideType.REVISION_POINT);
//        s14.setRevisionPoint1Id(rps14Ids[0]);
//
//        revisionSlideDao.insertAll(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14);
//    }
//
//    public static void feedMath9thTOC(AppDatabase db, long subjectGroupId) {
//        SubjectDao subjectDao = db.subjectDao();
//
//        Subject maths9th = new Subject("Maths", subjectGroupId, 9);
//        long[] subjectIds = subjectDao.insertAll(maths9th);
//
//        ChapterDao chapterDao = db.chapterDao();
//
//        Chapter ch1 = new Chapter("Number System", 1, subjectIds[0]);
//        Chapter ch2 = new Chapter("Polynomials", 2, subjectIds[0]);
//        Chapter ch3 = new Chapter("Coordinate Geometry", 3, subjectIds[0]);
//        Chapter ch4 = new Chapter("Linear Equations in Two Variables", 4, subjectIds[0]);
//        Chapter ch5 = new Chapter("Introduction to Euclid’s Geometry", 5, subjectIds[0]);
//
//        Chapter ch6 = new Chapter("Lines and Angles", 6, subjectIds[0]);
//        Chapter ch7 = new Chapter("Triangles", 7, subjectIds[0]);
//        Chapter ch8 = new Chapter("Quadrilaterals", 8, subjectIds[0]);
//        Chapter ch9 = new Chapter("Areas of Parallelograms and Triangles", 9, subjectIds[0]);
//        Chapter ch10 = new Chapter("Circles", 10, subjectIds[0]);
//
//        Chapter ch11 = new Chapter("Constructions", 11, subjectIds[0]);
//        Chapter ch12 = new Chapter("Heron’s Formula", 12, subjectIds[0]);
//        Chapter ch13 = new Chapter("Surface Areas and Volumes", 13, subjectIds[0]);
//        Chapter ch14 = new Chapter("Statistics and Probability", 14, subjectIds[0]);
//
//        long[] chIds = chapterDao.insertAll(ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12, ch13, ch14);
//
//        QPT qpt1 = new QPT(1, chIds[1], 15);
//        QPT qpt2 = new QPT(2, chIds[1], 10);
//        QPT qpt3 = new QPT(3, chIds[1], 9);
//
//        QPTDao qptDao = db.qptDao();
//
//        long[] qptIds = qptDao.insertAll(qpt1, qpt2, qpt3);
//
//        Topic topic1 = new Topic("Introduction", 1, "Concepts of Polynomial Expressions and Coefficients", chIds[1], false);
//        Topic topic2 = new Topic("Polynomials in one variable", 2, "Polynomial in 1 variable, degree and types of polynomials", chIds[1], false);
//        Topic topic3 = new Topic("Zeros of a polynomial", 3, "Finding the roots of polynomials", chIds[1], true);
//
//        Topic topic4 = new Topic("Operation of Polynomials", 4, "Basic arithmetic operations on polynomials", chIds[1], false);
//
//        Topic topic5 = new Topic("Remainder Theorem", 5, "Using remainder theorem to find remainders quickly", chIds[1], true);
//        Topic topic6 = new Topic("Factorization of Polynomials", 6, "Factor Theorem and Factorization of Quadratic Polynomials", chIds[1], false);
//        Topic topic7 = new Topic("Algebraic Identities", 7, "How to use algebraic identities for factorization", chIds[1], true);
//
//        topic1.setTopicStatus(Topic.TopicRevised);
//        topic2.setTopicStatus(Topic.TopicActive);
//        topic3.setTopicStatus(Topic.TopicActive);
//        topic4.setTopicStatus(Topic.TopicUnlocked);
//
//        topic1.setQptId(qptIds[0]);
//        topic2.setQptId(qptIds[0]);
//        topic3.setQptId(qptIds[0]);
//
//        topic4.setQptId(qptIds[1]);
//
//        topic5.setQptId(qptIds[1]);
//        topic6.setQptId(qptIds[2]);
//        topic7.setQptId(qptIds[2]);
//
//        TopicDao topicDao = db.topicDao();
//
//        long[] topicIds = topicDao.insertAll(topic1, topic2, topic3);
//
//        feedPolynomialTopic1Slides(db, topicIds[0]);
//        feedPolynomialTopic2Slides(db, topicIds[1]);
//        feedPolynomialTopic3Slides(db, topicIds[2]);
//    }
//
//    public static void feedPolynomialTopic1Slides(AppDatabase db, long topicId) {
//        RevisionSlideDao revisionSlideDao = db.revisionSlideDao();
//
//        RevisionPointDao revisionPointDao = db.revisionPointDao();
//        DiagramDao diagramDao = db.diagramDao();
//        RevealInteractionDao revealInteractionDao = db.revealInteractionDao();
//
//        MultipleChoiceQuestionDao multipleChoiceQuestionDao = db.multipleChoiceQuestionDao();
//        MCQOptionDao mcqOptionDao = db.mcqOptionDao();
//
//        RevisionPoint rp1s1 = new RevisionPoint("Before starting, let’s understand the meaning of <b>polynomial</b>?", "<b>Poly</b> means  <font color='#ee0000'>many</font> and <b>nomial</b> means  <font color='#ee0000'>terms</font>. So <b>polynomial</b> is an expression that contains <font color='#ee0000'>many terms</font>.", null);
//        RevisionPoint rp2s1 = new RevisionPoint("But then, what is an expression exactly?", "<b>Expression</b> is a statement that  <font color='#ee0000'>contains numbers, variables</font> ( \\( x, y \\) etc) and operators ( \\( + , - , \\times , \\div \\) etc). For example \\( 5x + 4y + 2 \\)", null);
//
//        long[] rps1Ids = revisionPointDao.insertAll(rp1s1, rp2s1);
//        RevisionSlide s1 = new RevisionSlide(1, topicId, SlideType.REVISION_POINT);
//        s1.setRevisionPoint1Id(rps1Ids[0]);
//        s1.setRevisionPoint2Id(rps1Ids[1]);
//
//        RevisionPoint rp1s2 = new RevisionPoint(null, "Below is a polynomial. Look at <b>terms, exponents (powers), variables, constant</b> and <b>coefficients</b>", null);
//        Diagram ds2 = new Diagram(null, "polynomial", null);
//
//        long[] rps2Ids = revisionPointDao.insertAll(rp1s2);
//        long[] ds2Ids = diagramDao.insertAll(ds2);
//
//        RevisionSlide s2 = new RevisionSlide(2, topicId, SlideType.REVISION_POINT);
//        s2.setRevisionPoint1Id(rps2Ids[0]);
//        s2.setDiagramId(ds2Ids[0]);
//
//        RevisionPoint rp1s3 = new RevisionPoint(null, "<b>Variable</b> is something whose <font color='#ee0000'>value can vary</font> (change) whereas <b>constant</b> is <font color='#ee0000'>fixed numeric value</font>. Variables are generally referred as \\(x, y, z\\) etc, which can take any value.", null);
//        RevisionPoint rp2s3 = new RevisionPoint("If the variable in polynomial is x then we denote the polynomial as p(x), q(x)etc.", "\\( p(x) = 4x^2 + 3x + 3 \\) <br> \\( q(y) = -5y^3 + 4y - 1 \\)", null);
//
//        long[] rps3Ids = revisionPointDao.insertAll(rp1s3, rp2s3);
//
//        RevisionSlide s3 = new RevisionSlide(3, topicId, SlideType.REVISION_POINT);
//        s3.setRevisionPoint1Id(rps3Ids[0]);
//        s3.setRevisionPoint2Id(rps3Ids[1]);
//
//        RevisionPoint rp1s4 = new RevisionPoint(null, "Let us now look at <b>constants</b> and <b>coefficients</b>", null);
//        RevealInteraction ris4 = new RevealInteraction("Can you tell me in \\(3x^2+5x+7\\), what is <b>constant</b> and what is <b>coefficient</b>?", "<font color='#ffffff'>Coefficient is a <font color='#ee0000'>fixed numeric value</font> associated with variable whereas constant is stand-alone  numeric value. Thus in above polynomial, \\( 7 \\) is constant whereas \\( 3 \\) and \\( 5 \\) are coefficients</font>");
//
//        long[] rp1s4Ids = revisionPointDao.insertAll(rp1s4);
//        long[] ris4Ids = revealInteractionDao.insertAll(ris4);
//
//        RevisionSlide s4 = new RevisionSlide(4, topicId, SlideType.REVISION_POINT);
//        s4.setRevisionPoint1Id(rp1s4Ids[0]);
//        s4.setRevealInteractionId(ris4Ids[0]);
//
//        RevisionPoint rp1s5 = new RevisionPoint(null, "<b>Polynomial</b> appears to be the same as <b>expression</b>- is there any difference?", null);
//        RevisionPoint rp2s5 = new RevisionPoint(null, "<b>Polynomial</b> is a type of expression which <font color='#ee0000'>cannot contain rational numbers as coefficients/constants and exponents must be whole numbers</font>.", null);
//
//        long[] rps5Ids = revisionPointDao.insertAll(rp1s5, rp2s5);
//
//        RevisionSlide s5 = new RevisionSlide(5, topicId, SlideType.REVISION_POINT);
//        s5.setRevisionPoint1Id(rps5Ids[0]);
//        s5.setRevisionPoint2Id(rps5Ids[1]);
//
//        RevisionPoint rp1s6 = new RevisionPoint(null, "Every term in the polynomial has a power associated with it.", null);
//        RevealInteraction ris6 = new RevealInteraction("What is the power of second term in \\( 4x^2 - 3x - 5 \\) ?", "<font color='#ffffff'>Remember that \\( x^1 = x \\). If a term appears not to have an exponent then its exponent is always 1, as \\( x \\) mean \\( x^1 \\).</font>");
//
//        long[] rp1s6Ids = revisionPointDao.insertAll(rp1s6);
//        long[] ris6Ids = revealInteractionDao.insertAll(ris6);
//
//        RevisionSlide s6 = new RevisionSlide(6, topicId, SlideType.REVISION_POINT);
//        s6.setRevisionPoint1Id(rp1s6Ids[0]);
//        s6.setRevealInteractionId(ris6Ids[0]);
//
//        RevisionPoint rp1s7 = new RevisionPoint(null, "Let us do 1 more", null);
//        RevealInteraction ris7 = new RevealInteraction("Tell me what is the power of last term in \\( 4x^2 - 3x - 5 \\) ?", "<font color='#ffffff'>Last term is a constant and can be written as \\( -5x^0 \\). Anything raised to the power 0 is 1, so \\( x^0=1 \\) and thus the power of last term is 0</font>");
//
//        long[] rp1s7Ids = revisionPointDao.insertAll(rp1s7);
//        long[] ris7Ids = revealInteractionDao.insertAll(ris7);
//
//        RevisionSlide s7 = new RevisionSlide(7, topicId, SlideType.REVISION_POINT);
//        s7.setRevisionPoint1Id(rp1s7Ids[0]);
//        s7.setRevealInteractionId(ris7Ids[0]);
//
//        MultipleChoiceQuestion mcq1s8 = new MultipleChoiceQuestion("Recall what all has been studied and click all the valid polynomials below: <br><br>A. \\( 2x^4 - 4.5x^{23} + x^0 + 5 \\) <br>B. \\( x \\)<br>C. \\( 2x^{3/2} - 6x + 4 \\)", "Check All the answers to understand", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        mcq1s8.setAlternateDisplay(true);
//        long[] mcqs8Ids = multipleChoiceQuestionDao.insertAll(mcq1s8);
//
//        MCQOption mcq1s8op1 = new MCQOption("A", true, "Rational numbers are not allowed but Real numbers are permitted so \\( 4.5 \\) is ok . Also \\( x^0 = 1 \\) so it’s also ok.", mcqs8Ids[0]);
//        MCQOption mcq1s8op2 = new MCQOption("B", true, "It is valid polynomial as coefficient of \\( x \\) is \\( -1 \\) and constant is \\( 0 \\)",  mcqs8Ids[0]);
//        MCQOption mcq1s8op3 = new MCQOption("C", false, "It is not a polynomial because \\( x \\) is being raised to a power \\( \\frac{3}{2} \\) that's not a whole number and power must be a whole numbers, i.e., \\( 0, 1, 2... \\) ",  mcqs8Ids[0]);
//
//        mcqOptionDao.insertAll(mcq1s8op1, mcq1s8op2, mcq1s8op3);
//
//        RevisionSlide s8 = new RevisionSlide(8, topicId, SlideType.REVISION_QUESTION);
//        s8.setMcqId(mcqs8Ids[0]);
//        s8.setInteractive(true);
//
//        MultipleChoiceQuestion mcq1s9 = new MultipleChoiceQuestion("What do you think about following:<br><br>A. \\( \\frac{2}{x} + 3x + 4 \\)<br>B. \\( 5x^2 + 4^x + 5 \\)<br>C. \\( 2 \\)", "Choose the correct answers", "" , MCQType.SINGLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        mcq1s9.setAlternateDisplay(true);
//        long[] mcqs9Ids = multipleChoiceQuestionDao.insertAll(mcq1s9);
//
//        MCQOption mcq1s9op1 = new MCQOption("A", false, "When you move the variable up and down sign of the power changes. For Example \\( 3x^2 = \\frac{3}{x^{-2}} \\) .So \\( \\frac{2}{x} = 2x^{-1} \\) and exponents can have only whole numbers, i.e., \\( 0, 1, 2... \\) –ve exponents are not allowed - invalid", mcqs9Ids[0]);
//        MCQOption mcq1s9op2 = new MCQOption("B", true, "It is valid polynomial as coefficient of \\( x \\) is \\( -1 \\) and constant is \\( 0 \\)",  mcqs9Ids[0]);
//        MCQOption mcq1s9op3 = new MCQOption("C", false, "2 is as good as \\( 2x^0 \\). So, \\( 2 \\) and \\( x^0 \\) both are valid polynomials ",  mcqs9Ids[0]);
//
//        mcqOptionDao.insertAll(mcq1s9op1, mcq1s9op2, mcq1s9op3);
//
//        RevisionSlide s9 = new RevisionSlide(9, topicId, SlideType.REVISION_QUESTION);
//        s9.setMcqId(mcqs9Ids[0]);
//        s9.setInteractive(true);
//
//        revisionSlideDao.insertAll(s1, s2, s3, s4, s5, s6, s7, s8, s9);
//    }
//
//    public static void feedPolynomialTopic2Slides(AppDatabase db, long topicId) {
//        RevisionSlideDao revisionSlideDao = db.revisionSlideDao();
//
//        RevisionPointDao revisionPointDao = db.revisionPointDao();
//        MultipleChoiceQuestionDao multipleChoiceQuestionDao = db.multipleChoiceQuestionDao();
//        MCQOptionDao mcqOptionDao = db.mcqOptionDao();
//
//        RevisionPoint rp1s1 = new RevisionPoint("Let’s understand concept of the <b>degree</b> of a polynomial", "<table width='100%'><tr><td><b>Term</b></td><td><b>Degree</b></td></tr><tr><td> \\( 3x^2 \\) </td><td> \\( 2 \\) </td></tr><tr><td> \\( 5x \\) </td><td> \\( 1 \\) </td></tr><tr>	<td> \\( 5x^{67} \\) </td><td> \\( 67 \\) </td></tr><tr><td> \\( 7 \\) </td><td> \\( 0 \\) </td></tr></table>", null);
//        RevisionPoint rp2s1 = new RevisionPoint(null, "\\( 7 \\) is a constant polynomial and has a degree \\( 0 \\) because \\( 7 \\) is as good as \\( 7x^0 \\) ( as \\( x^0 = 1 \\) )", null);
//
//        long[] rps1Ids = revisionPointDao.insertAll(rp1s1, rp2s1);
//        RevisionSlide s1 = new RevisionSlide(1, topicId, SlideType.REVISION_POINT);
//        s1.setRevisionPoint1Id(rps1Ids[0]);
//        s1.setRevisionPoint2Id(rps1Ids[1]);
//
//        RevisionPoint rp1s2 = new RevisionPoint("In the same way there's also the <b>degree of a polynomial</b>", "The <b>degree of a polynomial</b> is the <font color='#ee0000'>largest degree</font> of any of its terms", null);
//        RevisionPoint rp2s2 = new RevisionPoint("Let's look at examples", "<table width='100%'><tr><td><b>Polynomial</b></td><td><b>Degree</b></td></tr><tr><td> \\( 5x^3 + 6x + 9 \\) </td><td> \\( 3 \\) </td></tr><tr><td> \\( x^23 + x^6 + 4x - 2 \\) </td><td> \\( 23 \\) </td></tr><tr>	<td> \\( 4x^6 + 3x^5 + 2x^3 \\) </td><td> \\( 6 \\) </td></tr><tr><td> \\( 7 \\) </td><td> \\( 0 \\) </td></tr><tr><td> \\( 4x + 5x^2 \\) </td><td> \\( 2 \\) </td></tr></table>", null);
//
//        long[] rps2Ids = revisionPointDao.insertAll(rp1s2, rp2s2);
//        RevisionSlide s2 = new RevisionSlide(2, topicId, SlideType.REVISION_POINT);
//        s2.setRevisionPoint1Id(rps2Ids[0]);
//        s2.setRevisionPoint2Id(rps2Ids[1]);
//
//        RevisionPoint rp1s3 = new RevisionPoint("So is it correct that degree of all constant polynomials is zero?", "No! Degree of only non-zero constant polynomial is \\( 0 \\). The constant polynomial \\( 0 \\) has no defined degree and is called zero polynomial", null);
//        RevisionPoint rp2s3 = new RevisionPoint(null, "When \\( 0 \\) is multiplied by any variable of any exponent, it remains \\( 0 \\) . So there is no clarity what is the degree of \\( 0 \\) and that's why it's <b>undefined</b>", null);
//
//        long[] rps3Ids = revisionPointDao.insertAll(rp1s3, rp2s3);
//        RevisionSlide s3 = new RevisionSlide(3, topicId, SlideType.REVISION_POINT);
//        s3.setRevisionPoint1Id(rps3Ids[0]);
//        s3.setRevisionPoint2Id(rps3Ids[1]);
//
//        RevisionPoint rp1s4 = new RevisionPoint(null, "We can classify <b>Polynomials</b> on the basis of <font color='#ee0000'>number of terms</font> in it. Note that <br><b>mono</b> means <b>1</b>, <b>bi</b> means <b>2</b> and <b>tri</b> means <b>3</b>", null);
//        RevisionPoint rp2s4 = new RevisionPoint(null, "So the polynomials with 1, 2 and 3 terms are called <b>monomial, binomial and trinomial</b> respectively", null);
//
//        long[] rps4Ids = revisionPointDao.insertAll(rp1s4, rp2s4);
//        RevisionSlide s4 = new RevisionSlide(4, topicId, SlideType.REVISION_POINT);
//        s4.setRevisionPoint1Id(rps4Ids[0]);
//        s4.setRevisionPoint2Id(rps4Ids[1]);
//
//        RevisionPoint rp1s5 = new RevisionPoint(null, "<table width='100%'><tr><td><b>Polynomials</b></td><td><b># of Terms</b></td></tr><tr><td> \\( 3x^2 \\) </td><td> \\( 1 \\) </td></tr><tr><td> \\( 5x + 1 \\) </td><td> \\( 2 \\) </td></tr><tr>	<td> \\( 3x^2 + 2x + 4 \\) </td><td> \\( 3 \\) </td></tr><tr><td> \\( x^5 + 2x^2 + 4x + 7 \\) </td><td> \\( 4 \\) </td></tr></table>", null);
//        RevisionPoint rp2s5 = new RevisionPoint(null, "Thus in the above table, the polynomial with 1 term is a Monomial, with 2 terms is a Binomial, with 3 terms is a Trinomial and with 4 terms is a Quadrinomial", null);
//
//        long[] rps5Ids = revisionPointDao.insertAll(rp1s5, rp2s5);
//        RevisionSlide s5 = new RevisionSlide(5, topicId, SlideType.REVISION_POINT);
//        s5.setRevisionPoint1Id(rps5Ids[0]);
//        s5.setRevisionPoint2Id(rps5Ids[1]);
//
//        MultipleChoiceQuestion mcq1s6 = new MultipleChoiceQuestion("Which of the following are binomial?<br><br> A. \\( 3x^2 - 5x^3 + 5 \\) <br> B. \\( 6y^3 - y \\) <br> C. \\( 3xy + 3x + 5 \\) <br> D. \\( 4x + 3 \\)", "Choose ALL the correct answers", "Binomial is a polynomial with 2 terms." , MCQType.MULTIPLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        mcq1s6.setAlternateDisplay(true);
//        long[] mcqs6Ids = multipleChoiceQuestionDao.insertAll(mcq1s6);
//
//        MCQOption mcq1s6op1 = new MCQOption("A", false, "", mcqs6Ids[0]);
//        MCQOption mcq1s6op2 = new MCQOption("B", true, "",  mcqs6Ids[0]);
//        MCQOption mcq1s6op3 = new MCQOption("C", false, "",  mcqs6Ids[0]);
//        MCQOption mcq1s6op4 = new MCQOption("D", true, "",  mcqs6Ids[0]);
//
//        mcqOptionDao.insertAll(mcq1s6op1, mcq1s6op2, mcq1s6op3, mcq1s6op4);
//
//        RevisionSlide s6 = new RevisionSlide(6, topicId, SlideType.REVISION_QUESTION);
//        s6.setMcqId(mcqs6Ids[0]);
//
//        RevisionPoint rp1s7 = new RevisionPoint("We can alo classify Polynomials on the basis of it’s degree", "<b>Linear polynomial</b> is one that has <b>degree 1</b>. For example - \\( 3x + 3 \\) , \\( 4y - 3 \\)", null);
//        RevisionPoint rp2s7 = new RevisionPoint(null, "General form of <b>linear polynomial</b> is \\[ p(x) = ax + b \\] where \\( a \\neq 0 \\) .<br><br>Also, as we can see, it <font color='#ee0000'>has at most 2 terms</font>.", null);
//
//        long[] rps7Ids = revisionPointDao.insertAll(rp1s7, rp2s7);
//        RevisionSlide s7 = new RevisionSlide(7, topicId, SlideType.REVISION_POINT);
//        s7.setRevisionPoint1Id(rps7Ids[0]);
//        s7.setRevisionPoint2Id(rps7Ids[1]);
//
//        RevisionPoint rp1s8 = new RevisionPoint(null, "Similarly <b>quadratic polynomial</b> has <b>degree 2</b>. For example - \\( x^2 + 3x + 3 \\) , \\( 6y^2 - 4y - 3 \\)", null);
//        RevisionPoint rp2s8 = new RevisionPoint(null, "General form of <b>quadratic polynomial</b> is \\[ p(x) = ax^2 + bx + c \\] where \\( a \\neq 0 \\) .<br><br>Again, it can <font color='#ee0000'>at most have 3 terms</font>.", null);
//
//        long[] rps8Ids = revisionPointDao.insertAll(rp1s8, rp2s8);
//        RevisionSlide s8 = new RevisionSlide(8, topicId, SlideType.REVISION_POINT);
//        s8.setRevisionPoint1Id(rps8Ids[0]);
//        s8.setRevisionPoint2Id(rps8Ids[1]);
//
//        RevisionPoint rp1s9 = new RevisionPoint(null, "Similarly <b>cubic polynomial</b> is <b>degree 3</b>. For example - \\( 4x^3 - 2x^2 + 6x + 3 \\) , \\( 3y^3 + 6y^2 - 4y - 3 \\)", null);
//        RevisionPoint rp2s9 = new RevisionPoint(null, "General form of <b>cubic polynomial</b> is \\[ p(x) = ax^3 + bx^2 + cx + d \\] where \\( a \\neq 0 \\) .<br><br>Again, it can <font color='#ee0000'>at most have 4 terms</font>.", null);
//
//        long[] rps9Ids = revisionPointDao.insertAll(rp1s9, rp2s9);
//        RevisionSlide s9 = new RevisionSlide(9, topicId, SlideType.REVISION_POINT);
//        s9.setRevisionPoint1Id(rps9Ids[0]);
//        s9.setRevisionPoint2Id(rps9Ids[1]);
//
//        MultipleChoiceQuestion mcq1s10 = new MultipleChoiceQuestion("Which of the following are quadratic polynomials?<br><br> A. \\( 3x^2 - 5x^3 + 5 \\) <br> B. \\( 6y^3 - y \\) <br> C. \\( 3xy + 3x + 5 \\) <br> D. \\( 4x + 3x^2 \\)", "Choose ALL the correct answers", "Only \\( 4x + 3x^2 \\) is a quadratic polynomial because its highest power of a variable is 2" , MCQType.MULTIPLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        mcq1s10.setAlternateDisplay(true);
//        long[] mcqs10Ids = multipleChoiceQuestionDao.insertAll(mcq1s10);
//
//        MCQOption mcq1s10op1 = new MCQOption("A", false, "", mcqs10Ids[0]);
//        MCQOption mcq1s10op2 = new MCQOption("B", false, "",  mcqs10Ids[0]);
//        MCQOption mcq1s10op3 = new MCQOption("C", false, "",  mcqs10Ids[0]);
//        MCQOption mcq1s10op4 = new MCQOption("D", true, "",  mcqs10Ids[0]);
//
//        mcqOptionDao.insertAll(mcq1s10op1, mcq1s10op2, mcq1s10op3, mcq1s10op4);
//
//        RevisionSlide s10 = new RevisionSlide(10, topicId, SlideType.REVISION_QUESTION);
//        s10.setMcqId(mcqs10Ids[0]);
//
//        revisionSlideDao.insertAll(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
//    }
//
//    public static void feedPolynomialTopic3Slides(AppDatabase db, long topicId) {
//        RevisionSlideDao revisionSlideDao = db.revisionSlideDao();
//
//        RevisionPointDao revisionPointDao = db.revisionPointDao();
//        DiagramDao diagramDao = db.diagramDao();
//        RevealInteractionDao revealInteractionDao = db.revealInteractionDao();
//        ConfirmInteractionDao confirmInteractionDao = db.confirmInteractionDao();
//
//        MultipleChoiceQuestionDao multipleChoiceQuestionDao = db.multipleChoiceQuestionDao();
//        OneWordAnswerQuestionDao oneWordAnswerQuestionDao = db.oneWordAnswerQuestionDao();
//        MCQOptionDao mcqOptionDao = db.mcqOptionDao();
//
//        RevisionPoint rp1s1 = new RevisionPoint(null, "In this topic we will learn about <b>solving a polynomial</b> at a particular value", null);
//        ConfirmInteraction cis1 = new ConfirmInteraction("Can you solve <br> \\( p(x) = 3x^2 - 3x + 5 \\) at \\( x = 2 \\) ");
//
//        long[] rps1Ids = revisionPointDao.insertAll(rp1s1);
//        long[] cis1Ids = confirmInteractionDao.insertAll(cis1);
//
//        RevisionSlide s1 = new RevisionSlide(1, topicId, SlideType.REVISION_POINT);
//        s1.setRevisionPoint1Id(rps1Ids[0]);
//        s1.setConfirmInteractionId(cis1Ids[0]);
//
//        RevisionPoint rp1s2 = new RevisionPoint(null, "<b>Solving the polynomial</b> at \\( x = 2 \\) means <font color='#ee0000'>replace value of \\( x \\) with \\( 2 \\) and then calculate</font> the expression", null);
//        RevisionPoint rp2s2 = new RevisionPoint(null, "So,<br><br> \\( p(x) = 3x^2 - 3x + 5 \\) at \\( x = 2 \\) means <br> \\( p(2) = 3(2)^2 - 3(2) + 5 \\) <br> \\( = 12 + 6 + 5 \\) <br> \\( = 11 \\) .<br>So the value of polynomial at \\( x = 2 \\) is \\( 11 \\) ", null);
//
//        long[] rps2Ids = revisionPointDao.insertAll(rp1s2, rp2s2);
//        RevisionSlide s2 = new RevisionSlide(2, topicId, SlideType.REVISION_POINT);
//        s2.setRevisionPoint1Id(rps2Ids[0]);
//        s2.setRevisionPoint2Id(rps2Ids[1]);
//
//        RevisionPoint rp1s3 = new RevisionPoint(null, "OK then. Solve the following polynomial at \\( t = -2 \\) ", null);
//        RevealInteraction ris3 = new RevealInteraction(" \\[ p(t) = 4t^3 + 5t^2 - 5 \\]", "<font color='#ffffff'>Substituting \\( t = 2 \\) in \\( p(t) \\) <br> \\( p(-2) = 4(-2)^3 + 5(-2)^2 - 5 \\) <br> \\( = 4(-8) + 5(4) - 5 \\) <br> \\( = -17 \\) </font>");
//
//        long[] rp1s3Ids = revisionPointDao.insertAll(rp1s3);
//        long[] ris3Ids = revealInteractionDao.insertAll(ris3);
//
//        RevisionSlide s3 = new RevisionSlide(3, topicId, SlideType.REVISION_POINT);
//        s3.setRevisionPoint1Id(rp1s3Ids[0]);
//        s3.setRevealInteractionId(ris3Ids[0]);
//
//        RevisionPoint rp1s4 = new RevisionPoint("Let’s understand what do we mean by  the roots or zeros of a polynomial", "For \\( p(x) = 3x-6 \\) , if we take \\( x=2 \\) then \\( p(2) = 3*2-6 = 0 \\),  so \\( 2 \\) is a root (zero) of \\( p(x) = 3x-6 \\).", null);
//        RevisionPoint rp2s4 = new RevisionPoint(null, "The value of the variable \\( x \\) which makes the polynomial \\( p(x) \\) equal to zero is called the root or zero of polynomial. ", null);
//        long[] rps4Ids = revisionPointDao.insertAll(rp1s4, rp2s4);
//
//        RevisionSlide s4 = new RevisionSlide(4, topicId, SlideType.REVISION_POINT);
//        s4.setRevisionPoint1Id(rps4Ids[0]);
//        s4.setRevisionPoint2Id(rps4Ids[1]);
//
//        RevisionPoint rp1s5 = new RevisionPoint("Let’s see how to find the roots (zeros) of a polynomial \\( p(y) = 4y - 12 \\)", "First make \\( p(y) = 0 \\) i.e. \\( 4y - 12 = 0 \\) ", null);
//        RevisionPoint rp2s5 = new RevisionPoint(null, "Then solve to find the value of variable <br> => \\( 4y = 12 \\) <br> => \\( y= \\frac{12}{4} \\) <br> => \\( y = 3 \\) <br> So, \\( 3 \\) is the root of \\( 4y-12 \\) ", null);
//        long[] rps5Ids = revisionPointDao.insertAll(rp1s5, rp2s5);
//
//        RevisionSlide s5 = new RevisionSlide(5, topicId, SlideType.REVISION_POINT);
//        s5.setRevisionPoint1Id(rps5Ids[0]);
//        s5.setRevisionPoint2Id(rps5Ids[1]);
//
//        RevisionPoint rp1s6 = new RevisionPoint(null, "What are the zeros of \\( p(x)=(x+1)(x-2) \\)", null);
//        RevisionPoint rp2s6 = new RevisionPoint(null, "First make \\( p(x)=0 \\) i.e. \\( (x+1)(x-2) = 0 \\) <br> It means that \\( (x+1) = 0 \\) or \\( (x-2)=0 \\) <br> \\( x = -1 \\) or \\( x = 2 \\) <br>So it has two roots -1 and 2. Thus <font color='#ee0000'>a polynomial can have more than one zeros.</font>", null);
//        long[] rps6Ids = revisionPointDao.insertAll(rp1s6, rp2s6);
//
//        RevisionSlide s6 = new RevisionSlide(6, topicId, SlideType.REVISION_POINT);
//        s6.setRevisionPoint1Id(rps6Ids[0]);
//        s6.setRevisionPoint2Id(rps6Ids[1]);
//
//        RevisionPoint rps7 = new RevisionPoint(null, "Let us recall Constant Polynomials", null);
//        RevealInteraction ris7 = new RevealInteraction("You know that <font color='#ee0000'>p=10 is a constant polynomial</font>. Can you guess what is the zero this polynomial?", "<font color='#ffffff'>You cannot equate a non-zero constant polynomial to \\( 0 \\). Writing \\( 10 = 0 \\) is fundamentally incorrect but If \\( p = 0 \\) then \\( 0 = 0 \\) is ok. So, a nonzero constant polynomial does not have a zero.</font>");
//
//        long[] rps7Ids = revisionPointDao.insertAll(rps7);
//        long[] ris7Ids = revealInteractionDao.insertAll(ris7);
//
//        RevisionSlide s7 = new RevisionSlide(7, topicId, SlideType.REVISION_POINT);
//        s7.setRevisionPoint1Id(rps7Ids[0]);
//        s7.setRevealInteractionId(ris7Ids[0]);
//
//        RevisionPoint rps8 = new RevisionPoint(null, null, null);
//        RevealInteraction ris8 = new RevealInteraction("Can \\( 0 \\) be a Zero (root) of a polynomial?", "<font color='#ffffff'>Yes it can. Let us take an example. If you take \\( x^2-2x = 0 \\) then \\( x(x-2)=0 \\) <br>So, \\( x = 0 \\) or \\( x=2 \\). Thus \\( 0 \\) may be a Zero of a polynomial. In fact all the polynomials like \\( x^2, x^3, x^4 \\) will have \\( 0 \\) as zero.</font>");
//
//        long[] rps8Ids = revisionPointDao.insertAll(rps8);
//        long[] ris8Ids = revealInteractionDao.insertAll(ris8);
//
//        RevisionSlide s8 = new RevisionSlide(8, topicId, SlideType.REVISION_POINT);
//        s8.setRevisionPoint1Id(rps8Ids[0]);
//        s8.setRevealInteractionId(ris8Ids[0]);
//
//        RevisionPoint rp1s9 = new RevisionPoint(null, "Now you know that the general form of a linear polynomial is \\[ p(x) = ax + b \\], where \\( a \\neq 0 \\) <br> So root is \\( ax + b = 0 \\) <br> This implies that <br> \\( \\frac{-b}{a} \\) ", null);
//        RevisionPoint rp2s9 = new RevisionPoint(null, "It means a linear polynomial has only one root ie. \\( x = \\frac{-b}{a} \\) .", null);
//        long[] rps9Ids = revisionPointDao.insertAll(rp1s9, rp2s9);
//
//        RevisionSlide s9 = new RevisionSlide(9, topicId, SlideType.REVISION_POINT);
//        s9.setRevisionPoint1Id(rps9Ids[0]);
//        s9.setRevisionPoint2Id(rps9Ids[1]);
//
//        String exp10 = "First we set \\( x^2 – 9 = 0 \\) <br>" +
//                "=> \\( x^2 = 9 \\) <br> => \\( x = \\pm3 \\) <br>" +
//                "In other words, we can put x = 3 or x = -3 to make the value of polynomial zero. This means 3 and -3 both are valid roots. <br>" +
//                "So, a polynomial can have more than one zeros.";
//        MultipleChoiceQuestion mcqs10 = new MultipleChoiceQuestion("What are the roots of the polynomial \\( x^2 – 9 \\) ?", "Select ALL the correct answers", exp10, MCQType.MULTIPLE_ANSWER, QuestionCategory.CONCEPTUAL, QuestionLevel.EASY, topicId);
//        long[] mcqs10Ids = multipleChoiceQuestionDao.insertAll(mcqs10);
//
//        MCQOption mcqs10op1 = new MCQOption("", false, "", mcqs10Ids[0]);
//        MCQOption mcqs10op2 = new MCQOption("", false, "",  mcqs10Ids[0]);
//        MCQOption mcqs10op3 = new MCQOption("", true, "",  mcqs10Ids[0]);
//        MCQOption mcqs10op4 = new MCQOption("", false, "",  mcqs10Ids[0]);
//
//        mcqOptionDao.insertAll(mcqs10op1, mcqs10op2, mcqs10op3, mcqs10op4);
//
//        RevisionSlide s10 = new RevisionSlide(10, topicId, SlideType.REVISION_QUESTION);
//        s10.setMcqId(mcqs10Ids[0]);
//
//        revisionSlideDao.insertAll(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
//    }
}