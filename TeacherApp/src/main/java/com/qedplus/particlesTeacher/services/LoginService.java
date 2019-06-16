package com.qedplus.particlesTeacher.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.qedplus.particlesTeacher.db.Teacher;
import com.qedplus.particlesTeacher.db.TeacherSubjectClass;
import com.qedplus.particlesTeacher.db.Topic;
import com.qedplus.particlesTeacher.db.User;
import com.qedplus.particlesTeacher.extras.AppDatabase;
import com.qedplus.particlesTeacher.extras.PrefManager;
import com.qedplus.particlesTeacher.extras.SubjectBookPair;
import com.qedplus.particlesTeacher.extras.Utility;
import com.qedplus.particlesTeacher.payload.BoardResponse;
import com.qedplus.particlesTeacher.payload.BookResponse;

import com.qedplus.particlesTeacher.payload.ChapterResponse;
import com.qedplus.particlesTeacher.payload.SchoolClassResponse;
import com.qedplus.particlesTeacher.payload.SchoolClassSubjectsResponse;
import com.qedplus.particlesTeacher.payload.SchoolResponse;
import com.qedplus.particlesTeacher.payload.SchoolYearResponse;
import com.qedplus.particlesTeacher.payload.SigninResponse;
import com.qedplus.particlesTeacher.payload.SubjectGroupResponse;
import com.qedplus.particlesTeacher.payload.SubjectResponse;
import com.qedplus.particlesTeacher.payload.SubjectYearResponse;
import com.qedplus.particlesTeacher.payload.TeacherDetailResponse;
import com.qedplus.particlesTeacher.payload.TopicResponse;
import com.qedplus.particlesTeacher.payload.UserResponse;
import com.qedplus.particlesTeacher.payload.YearResponse;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginService extends AsyncTask<Object, String, Object> {
    private PrefManager prefManager;

    public interface LoginTask {
        void afterTask();
        void duringTask(String update);
    }


    Context context;
    private String username;
    private String password;
    private LoginTask loginTask;
    private SigninResponse signinResponse;

    SubjectDao subjectDao;
    SchoolClassDao schoolClassDao;
    SchoolDao schoolDao;
    TeacherSubjectClassDao teacherSubjectClassDao;
    EducationBoardDao educationBoardDao;
    SubjectGroupDao subjectGroupDao;
    BookDao bookDao;
    ChapterDao chapterDao;
    TopicDao topicDao;

    private ArrayList<SubjectBookPair> subjectBookPairs = new ArrayList<>();

    AppDatabase db;
    private UserDao userDao;

    private String IP;

    public LoginService(Context context, String username, String password, LoginTask loginTask) {
        this.context = context;
        this.username = username;
        this.password = password;
        this.loginTask = loginTask;
        db = AppDatabase.getAppDatabase(context);

        subjectDao = db.subjectDao();
        schoolClassDao = db.schoolClassDao();
        schoolDao = db.schoolDao();
        teacherSubjectClassDao = db.teacherSubjectClassDao();
        educationBoardDao = db.educationBoardDao();
        subjectGroupDao = db.subjectGroupDao();
        bookDao = db.bookDao();
        chapterDao= db.chapterDao();
        topicDao =db.topicDao();

        this.IP = Utility.getProperty("IP", context);
        prefManager = new PrefManager(context);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        publishProgress("Logging In");
        try {
            URL url = new URL(IP + "/api/auth/signin");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            JSONObject params = new JSONObject();
            params.put("usernameOrEmail", username);
            params.put("password", password);

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params.toString());
            writer.flush();
            writer.close();
            os.close();

            con.connect();

            int responseCode = con.getResponseCode();
            Log.e("Response :", responseCode + "");

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                signinResponse = new ObjectMapper().readValue(response.toString(), SigninResponse.class);
                Log.i("Response", signinResponse.toString());

                userDao = db.userDao();

                User user = userDao.getUserByUsername(username);

                if (user != null) {
                    Log.i("UserDetails", user.toString());
                    user.setAccessToken(signinResponse.accessToken);
                    userDao.updateAll(user);
                    getTeacherDetails(user);

                }
                else {
                    User newUser = new User(username, signinResponse.accessToken);
                    long userIds[] = userDao.insertAll(newUser);
                    getTeacherDetails(userDao.getUserByUsername(username));
                }
            }
        }
        catch (Exception e) {
            Log.e("Connection Error", e.toString());
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        loginTask.duringTask(progress[0]);
    }

    @Override
    protected void onPostExecute(Object o) {
        loginTask.afterTask();
    }

    private void getTeacherDetails(User user) throws Exception {
        Log.e("Flow :", "getTeacherDetails");

        URL url = new URL(IP + "/api/get/teacherDetails");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("authorization", "Bearer " + signinResponse.accessToken);
        con.setRequestProperty("Content-Type", "application/json");

        OutputStream os = con.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.flush();
        writer.close();
        os.close();

        con.connect();

        int responseCode = con.getResponseCode();
        Log.e("Response Code", "" + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        Log.e("Teacher Detail :", response.toString());

        TeacherDetailResponse teacherDetailResponse = new ObjectMapper().readValue(response.toString(), TeacherDetailResponse.class);

        UserResponse userResponse = teacherDetailResponse.getUserResponse();
        SchoolResponse schoolResponse = teacherDetailResponse.getSchoolResponse();
        BoardResponse boardResponse = schoolResponse.getBoard();

        List<SchoolClassSubjectsResponse> schoolClassSubjectsResponses = Arrays.asList(teacherDetailResponse.getSchoolClassSubjects());

        EducationBoard educationBoard = educationBoardDao.findByName(boardResponse.getName());
        int educationBoardId;

        if (educationBoard == null) {
            EducationBoard eb = new EducationBoard(boardResponse.getName(), boardResponse.getAka());
            educationBoardId = (int) educationBoardDao.insertAll(eb)[0];
        }
        else {
            educationBoardId = educationBoard.getEducationBoardId();
        }

        School school = schoolDao.findByCode(schoolResponse.getSchoolCode());
        int schoolId;

        if (school == null) {
            School sc = new School(schoolResponse.getName(), schoolResponse.getSchoolCode(), educationBoardId, schoolResponse.getLogoURL(), schoolResponse.getSchoolTheme());
            schoolId = (int) schoolDao.insertAll(sc)[0];
        }
        else {
            schoolId = school.getSchoolId();
        }

        user.setName(userResponse.getName());
        userDao.updateAll(user);
        prefManager.setUserDetails(user);

        User checku = prefManager.getUserDetails();
        Log.e("Pref U :", checku.toString());

        Log.e("SCSR Size : ", schoolClassSubjectsResponses.size() + "");
        for (SchoolClassSubjectsResponse scsr : schoolClassSubjectsResponses) {
            Log.e("Login scsr : ", scsr.toString());

            SchoolClassResponse schoolClassResponse = scsr.getSchoolClassResponse();
            SchoolYearResponse schoolYearResponse = schoolClassResponse.getSchoolYear();
            YearResponse yearResponse = schoolYearResponse.getYear();

            int schoolYear = yearResponse.getYear();

            SchoolClass schoolClass = schoolClassDao.findByNameAndSchool(schoolClassResponse.getName(), schoolId);
            int schoolClassId;

            if (schoolClass == null) {
                SchoolClass scc = new SchoolClass(schoolYear, schoolClassResponse.getSection(), schoolId);
                schoolClassId = (int) schoolClassDao.insertAll(scc)[0];
            }
            else {
                schoolClassId = schoolClass.getSchoolClassId();
            }

            SubjectResponse subjectResponse = scsr.getSubjectResponse();
            SubjectGroupResponse subjectGroupResponse = subjectResponse.getSubjectGroup();

            SubjectGroup subjectGroup = subjectGroupDao.findByName(subjectGroupResponse.getName());
            long subjectGroupId;
            if (subjectGroup == null) {
                subjectGroup = new SubjectGroup(subjectGroupResponse.getName());
                subjectGroupId = subjectGroupDao.insertAll(subjectGroup)[0];
            }
            else {
                subjectGroupId = subjectGroup.getSubjectGroupId();
            }

            Subject subject = subjectDao.getByNameAndYear(subjectResponse.getName(), schoolYear);
            long subjectId;
            if(subject == null) {
                subject = new Subject(subjectResponse.getName(), subjectGroupId, schoolYear);
                subjectId = subjectDao.insertAll(subject)[0];
            }
            else {
                subjectId = subject.getSubjectId();
            }

            TeacherSubjectClass tsc = teacherSubjectClassDao.findByTeacherSubjectSchoolClass(user.getUserId(), subjectId, schoolClassId);

            if(tsc == null) {
                Log.e("Login TSC : ", "Null HAI");
                tsc = new TeacherSubjectClass(user.getUserId(), subjectId, schoolClassId);
                teacherSubjectClassDao.insertAll(tsc);
            }
            else {
                Log.e("Login TSC : ", tsc.toString());
            }
            getBooksBySubjectSchoolYear();
        }

    }


//    public void getChaptersAndTopicBySubjectSchoolYear(SubjectResponse subjectResponse,SchoolYearResponse schoolYearResponse) throws Exception {
//        Log.e("Flow :", "getTeacherChapterAndTopicBySubjectSchoolYear");
//
//        URL url = new URL(IP + "/api/get/ChaptersAndTopicBySubjectSchoolYear");
//
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//        con.setRequestMethod("POST");
//        con.setRequestProperty("authorization", "Bearer " + signinResponse.accessToken);
//        con.setRequestProperty("Content-Type", "application/json");
//
//        OutputStream os = con.getOutputStream();
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//        writer.flush();
//        writer.close();
//        os.close();
//
//        con.connect();
//
//        int responseCode = con.getResponseCode();
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuilder response = new StringBuilder();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//
//        in.close();
//
//        ChapterAndTopicResponse ChapterAndTopicResponse = new ObjectMapper().readValue(response.toString(), ChapterAndTopicResponse.class);
//
//
//
//
//
//
//
//    }



    public void getBooksBySubjectSchoolYear() throws Exception {
        List<TeacherSubjectClass> teacherSubjectClasses = teacherSubjectClassDao.findAll();

        for(TeacherSubjectClass tsb : teacherSubjectClasses) {
            Subject s = subjectDao.findById(tsb.getSubjectId());
            SchoolClass sc = schoolClassDao.findById(tsb.getSchoolClassId());
            School schl = schoolDao.findById(sc.getSchoolId());

            URL url = new URL(IP + "/api/get/bookBySubjectSchoolYear");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("authorization", "Bearer " + signinResponse.accessToken);
            con.setRequestProperty("Content-Type", "application/json");

            JSONObject params = new JSONObject();

            Log.e("parameters :",s.getName()+"----"+schl.getCode()+"----"+sc.getSchoolYear());
            params.put("subjectName", s.getName());
            params.put("schoolCode", schl.getCode());
            params.put("year", sc.getSchoolYear());

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params.toString());
            writer.flush();
            writer.close();
            os.close();

            con.connect();

            int responseCode = con.getResponseCode();
            Log.e("Books Response :",responseCode+"");
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                BookResponse bookResponse = new ObjectMapper().readValue(response.toString(), BookResponse.class);
                Log.e("Books Individually :",bookResponse.toString());


                Book book = bookDao.findByName(bookResponse.getName());

                if (book == null) {
                    book = new Book(bookResponse.getName(), s.getSubjectId(), s.getSchoolYear(), bookResponse.getVersion());
                    bookDao.insertAll(book);
                }

                getChaptersByBook();
            }

        }
        List<Book> books= bookDao.findAll();
        for (Book book:books
             ) {
            Log.e("Book:",book.toString());

        }

    }

    public void getChaptersByBook() throws Exception {
        List<Book> books= bookDao.findAll();

        for(Book book : books) {
            URL url = new URL(IP + "/api/get/chaptersByBook");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("authorization", "Bearer " + signinResponse.accessToken);
            con.setRequestProperty("Content-Type", "application/json");

            JSONObject params = new JSONObject();
            params.put("bookName", book.getName());


            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params.toString());
            writer.flush();
            writer.close();
            os.close();

            con.connect();

            int responseCode = con.getResponseCode();
            Log.e("Chapter Response Code :",responseCode+"");
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                ChapterResponse chapterResponse=new  ObjectMapper().readValue(response.toString(), ChapterResponse.class);
                Log.e("Chapter Individually :",chapterResponse.toString());
                Chapter chapter = new Chapter(chapterResponse.getName(),chapterResponse.getChapterIndex(),book.getSubjectId() , chapterResponse.getVersion());
                chapterDao.insertAll(chapter);
            }

            getTopicsByChapter(book);
        }

        List<Chapter> chapters= chapterDao.findAll();

        for (Chapter chapter:chapters
             ) {
            Log.e("Chapters :",chapter.toString());

        }

    }

    public void getTopicsByChapter(Book book) throws Exception{
        List<Chapter> chapters= chapterDao.findAll();
        for(Chapter chapter : chapters) {
            URL url = new URL(IP + "/api/get/topicByChapter");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("authorization", "Bearer " + signinResponse.accessToken);
            con.setRequestProperty("Content-Type", "application/json");

            JSONObject params = new JSONObject();
            params.put("chapterName",chapter.getName());
            params.put("bookName",book.getName());
            params.put("version",chapter.getVersion());

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params.toString());
            writer.flush();
            writer.close();
            os.close();

            con.connect();

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                TopicResponse topicResponse=new  ObjectMapper().readValue(response.toString(), TopicResponse.class);
                Topic topic= new Topic(topicResponse.getName(),topicResponse.getTopicIndex(),topicResponse.getDescription(),chapter.getChapterId());
                topicDao.insertAll(topic);
            }
        }
    }
}
