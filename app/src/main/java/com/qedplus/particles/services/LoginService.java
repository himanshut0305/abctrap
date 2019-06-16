package com.qedplus.particles.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qedplus.particles.dao.BookDao;
import com.qedplus.particles.dao.EducationBoardDao;
import com.qedplus.particles.dao.SchoolClassDao;
import com.qedplus.particles.dao.SchoolDao;
import com.qedplus.particles.dao.SubjectDao;
import com.qedplus.particles.dao.SubjectGroupDao;
import com.qedplus.particles.dao.UserDao;
import com.qedplus.particles.db.Book;
import com.qedplus.particles.db.EducationBoard;
import com.qedplus.particles.db.School;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.db.SubjectGroup;
import com.qedplus.particles.db.User;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.SubjectBookPair;
import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.payload.BoardResponse;
import com.qedplus.particles.payload.BookSubjectResponse;
import com.qedplus.particles.payload.SchoolClassResponse;
import com.qedplus.particles.payload.SchoolResponse;
import com.qedplus.particles.payload.SchoolYearResponse;
import com.qedplus.particles.payload.SigninResponse;
import com.qedplus.particles.payload.StudentDetailResponse;
import com.qedplus.particles.payload.UserResponse;
import com.qedplus.particles.payload.YearResponse;

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
        void afterTask(ArrayList<SubjectBookPair> subjectBookPairs);
        void duringTask(String update);
    }

    Context context;
    private String username;
    private String password;
    private LoginTask loginTask;
    private SigninResponse signinResponse;

    private ArrayList<SubjectBookPair> subjectBookPairs = new ArrayList<>();

    AppDatabase db;
    private UserDao userDao;

    private String IP;

    public LoginService(Context context, String username, String password, LoginTask loginTask) {
        this.context = context;
        this.username = username;
        this.password = password;
        this.loginTask = loginTask;

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

                db = AppDatabase.getAppDatabase(context);
                userDao = db.userDao();

                User user = userDao.getUserByUsername(username);

                if (user != null) {
                    Log.i("UserDetails", user.toString());
                    user.setAccessToken(signinResponse.accessToken);
                    userDao.updateAll(user);
                    getStudentDetails(user);
                }
                else {
                    long userIds[] = userDao.insertAll(new User(username, signinResponse.accessToken));
                    getStudentDetails(userDao.getUserByUsername(username));
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
        loginTask.afterTask(subjectBookPairs);
    }

    private void getStudentDetails(User user) throws Exception {
        URL url = new URL(IP + "/api/get/studentDetails");

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
        Log.i("Response Code", "" + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        Log.e("Student Detail :", response.toString());

        StudentDetailResponse studentDetail = new ObjectMapper().readValue(response.toString(), StudentDetailResponse.class);

        SchoolClassResponse schoolClassResponse = studentDetail.getSchoolClass();
        SchoolYearResponse schoolYearResponse = schoolClassResponse.getSchoolYear();

        YearResponse yearResponse = schoolYearResponse.getYear();
        SchoolResponse schoolResponse = schoolYearResponse.getSchool();
        BoardResponse boardResponse = schoolResponse.getBoard();

        SchoolClassDao schoolClassDao = db.schoolClassDao();
        SchoolDao schoolDao = db.schoolDao();
        EducationBoardDao educationBoardDao = db.educationBoardDao();

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

        user.setName(studentDetail.getUser().getName());
        user.setRollNo(studentDetail.getRollNo());
        user.setAdmissionNo(studentDetail.getAdmissionNo());
        user.setSchoolClassId(schoolClassId);

        userDao.updateAll(user);
        prefManager.setUserDetails(user);
        prefManager.setSchoolYear(schoolYear);
        getSubjectsAndBooks(schoolYearResponse);
    }

    public void getSubjectsAndBooks(SchoolYearResponse schoolYearResponse) throws Exception {
        URL url = new URL(IP + "/api/get/subjectsAndBookBySchoolYear");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("authorization", "Bearer " + signinResponse.accessToken);
        con.setRequestProperty("Content-Type", "application/json");

        JSONObject params = new JSONObject();
        params.put("schoolYearId", schoolYearResponse.getName());

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

            List<BookSubjectResponse> bookSubjects = Arrays.asList(new ObjectMapper().readValue(response.toString(), BookSubjectResponse[].class));

            Log.e("Books :", "For Class " + schoolYearResponse.getYear().getName());
            Log.e("Books :", "" + bookSubjects);

            for (BookSubjectResponse bookSubjectResponse : bookSubjects) {
                if(bookSubjectResponse.getBook() != null) {
                    SubjectGroupDao subjectGroupDao = db.subjectGroupDao();
                    SubjectDao subjectDao = db.subjectDao();
                    BookDao bookDao = db.bookDao();

                    SubjectGroup subjectGroup = subjectGroupDao.findByName(bookSubjectResponse.getBook().getSubjectYear().getSubject().getSubjectGroup().getName());
                    long subjectGroupId;
                    if (subjectGroup == null) {
                        subjectGroup = new SubjectGroup(bookSubjectResponse.getBook().getSubjectYear().getSubject().getSubjectGroup().getName());
                        subjectGroupId = subjectGroupDao.insertAll(subjectGroup)[0];
                    }
                    else {
                        subjectGroupId = subjectGroup.getSubjectGroupId();
                    }

                    Subject subject = subjectDao.getByNameAndYear(bookSubjectResponse.getBook().getSubjectYear().getSubject().getName(), Integer.parseInt(bookSubjectResponse.getBook().getSubjectYear().getYear().getName()));
                    long subjectId;
                    if (subject == null) {
                        subject = new Subject();

                        subject.setName(bookSubjectResponse.getBook().getSubjectYear().getSubject().getName());
                        subject.setSubjectGroupId(subjectGroupId);
                        subject.setSchoolYear(Integer.parseInt(bookSubjectResponse.getBook().getSubjectYear().getYear().getName()));

                        subjectId = subjectDao.insertAll(subject)[0];
                    }
                    else {
                        subjectId = subject.getSubjectId();
                    }

                    Book book = bookDao.findByName(bookSubjectResponse.getBook().getName());

                    if (book == null) {
                        book = new Book();

                        book.setName(bookSubjectResponse.getBook().getName());
                        book.setSubjectId(subjectId);
                        book.setSchoolYear(Integer.parseInt(bookSubjectResponse.getBook().getSubjectYear().getYear().getName()));
                        book.setVersion(bookSubjectResponse.getBook().getVersion());

                        bookDao.insertAll(book);
                    }

                    SubjectBookPair sbp = new SubjectBookPair();

                    if (bookSubjectResponse.getBook() != null) {
                        sbp.setBook(bookSubjectResponse.getBook().getName());
                        sbp.setSubject(bookSubjectResponse.getBook().getSubjectYear().getSubject().getName());

                        subjectBookPairs.add(sbp);
                    }
                }
            }
        }
    }
}