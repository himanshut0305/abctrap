package com.qedplus.particles.services;

import android.content.Context;
import android.util.Log;

import com.qedplus.particles.dao.BadgeDao;
import com.qedplus.particles.dao.UserDao;
import com.qedplus.particles.db.Badge;
import com.qedplus.particles.db.ConfirmInteraction;
import com.qedplus.particles.db.User;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.model.BadgeBO;
import com.qedplus.particles.model.UserBO;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StudentServices {
    public interface UITask{
        void task(boolean hasAccessToken, boolean accessTokenIsStillValid);
    }

    private StudentServices.UITask uiTask;
    public static String IP;

    boolean hasAccessToken = false;
    boolean accessTokenIsStillValid = false;

    public StudentServices(Context context, StudentServices.UITask uiTask) {
        this.uiTask = uiTask;
        this.IP =  Utility.getProperty("IP", context);
    }

    public void getStudentDetails(final Context cxt) {

        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                PrefManager prefManager = new PrefManager(cxt);
                User u = prefManager.getUserDetails();

                if(u != null) {
                    if(!u.getAccessToken().equals("")) {
                        hasAccessToken = true;

                        try {
                            URL url = new URL(IP + "/api/get/studentDetails");

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
