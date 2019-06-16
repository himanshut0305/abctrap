package com.qedplus.particlesTeacher.services;

import android.content.Context;

import com.qedplus.particlesTeacher.db.User;
import com.qedplus.particlesTeacher.extras.PrefManager;
import com.qedplus.particlesTeacher.extras.Utility;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TeacherUserServices {
    public interface UITask{
        void task(boolean hasAccessToken, boolean accessTokenIsStillValid);
    }

    private TeacherUserServices.UITask uiTask;
    public static String IP;

    boolean hasAccessToken = false;
    boolean accessTokenIsStillValid = false;

    public TeacherUserServices(Context context, TeacherUserServices.UITask uiTask) {
        this.uiTask = uiTask;
        this.IP =  Utility.getProperty("IP", context);
    }

    public void getTeacherDetails(final Context cxt) {

        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                PrefManager prefManager = new PrefManager(cxt);
                User u = prefManager.getUserDetails();

                if(u != null) {
                    if(!u.getAccessToken().equals("")) {
                        hasAccessToken = true;

                        try {
                            URL url = new URL(IP + "/api/get/teacherDetails");

                            HttpURLConnection con = (HttpURLConnection) url.openConnection();

                            con.setRequestMethod("POST");
                            con.setRequestProperty("authorization", "Bearer " + u.getAccessToken());
                            con.setRequestProperty("Content-Type", "application/json");

                            OutputStream os = con.getOutputStream();
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                            writer.flush();
                            writer.close();
                            os.close();

                            con.connect();

                            int responseCode = con.getResponseCode();
                            if(responseCode >= 200 && responseCode < 300) {
                                accessTokenIsStillValid = true;
                            }
                        }
                        catch (Exception e) {}
                    }
                }
            }

            @Override
            public void afterTask() {
                uiTask.task(hasAccessToken, accessTokenIsStillValid);
            }
        });

        dbService.execute();
    }
}
