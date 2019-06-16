package com.qedplus.particlesTeacher.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qedplus.particlesTeacher.dao.SchoolDao;
import com.qedplus.particlesTeacher.db.School;
import com.qedplus.particlesTeacher.extras.AppDatabase;
import com.qedplus.particlesTeacher.extras.Utility;
import com.qedplus.particlesTeacher.model.SchoolBO;
import com.qedplus.particlesTeacher.payload.SchoolResponse;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SchoolServices {
    public interface UITask {
        void task(SchoolBO schoolBO);
    }

    private SchoolServices.UITask uiTask;
    public static String IP;

    public SchoolServices(SchoolServices.UITask uiTask) {
        this.uiTask = uiTask;
    }

    public void checkIfSchoolExists(final Context cxt, final String schoolCode) {
        IP = Utility.getProperty("IP", cxt);

        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            SchoolBO schoolBO = null;
            @Override
            public void task() {
                Log.e("School :", "School Service Check If");

                AppDatabase db = AppDatabase.getAppDatabase(cxt);
                SchoolDao schoolDao = db.schoolDao();

                School school = schoolDao.findByCode(schoolCode);

                if(school != null) {
                    Log.e("School :", school.toString());

                    schoolBO = new SchoolBO();

                    schoolBO.setSchoolCode(school.getCode());
                    schoolBO.setSchoolName(school.getName());
                    schoolBO.setTheme(school.getSchoolTheme());
                    schoolBO.setSchoolLogoFileName(school.getLogoURI());
                }
                else {
                    Log.e("School :", "School not found");
                    try {
                        URL url = new URL(IP + "/api/get/schoolByCode");

                        Log.e("URL :", url.getHost() + "");

                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json");

                        JSONObject params = new JSONObject();
                        params.put("code", schoolCode);

                        Log.e("JSON :", params.toString());

                        OutputStream os = con.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(params.toString());
                        writer.flush();
                        writer.close();
                        os.close();

                        con.connect();

                        Log.e("Connection :", con.toString());

                        int responseCode = con.getResponseCode();
                        Log.i("Response Code", "" + responseCode);

                        if(responseCode == 200) {
                            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            String inputLine;
                            StringBuilder response = new StringBuilder();

                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }

                            in.close();

                            SchoolResponse schoolResponse = new ObjectMapper().readValue(response.toString(), SchoolResponse.class);
                            Log.i("Response :", schoolResponse.toString());

                            File logo = getLogoFromURL(schoolResponse.getLogoURL(), url, schoolResponse.getName());
                            School newSchool = new School(schoolResponse.getName(), schoolResponse.getSchoolCode(), 1,logo.getAbsolutePath(), schoolResponse.getSchoolTheme());

                            schoolDao.insertAll(newSchool);

                            school = schoolDao.findByCode(schoolCode);
                            if(school != null) {
                                schoolBO = new SchoolBO();

                                schoolBO.setSchoolCode(school.getCode());
                                schoolBO.setSchoolName(school.getName());
                                schoolBO.setTheme(school.getSchoolTheme());
                                schoolBO.setSchoolLogoFileName(school.getLogoURI());
                            }
                        }
                    }
                    catch (IOException e) {
                        Log.e("IO Exception :", e.getMessage());
                        e.printStackTrace();
                    }
                    catch (Exception e) {
                        Log.e("Exception :", e.getMessage());
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void afterTask() {
                uiTask.task(schoolBO);
            }
        });

        dbService.execute();
    }

    public File getLogoFromURL(String url, URL fallbackURL, String schoolName) {
        schoolName = schoolName.replace(" - ", "_");
        schoolName = schoolName.replace(" ", "_").toLowerCase();

        if (url.contains("localhost")) {
            url = url.replace("localhost", fallbackURL.getHost());
        }

        try {
            java.net.URL downloadUrl = new java.net.URL(url);
            HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/qedplus");
            if (!myDir.exists()) {
                myDir.mkdirs();
            }

            String fname = "logo_" + schoolName +".png";
            File file = new File (myDir, fname);

            if (file.exists ())
                file.delete ();

            FileOutputStream out = new FileOutputStream(file);
            myBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

            return file;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}