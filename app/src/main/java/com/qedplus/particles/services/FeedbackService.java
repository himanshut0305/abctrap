package com.qedplus.particles.services;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.qedplus.particles.services.StudentServices.IP;

public class FeedbackService extends AsyncTask<Object, String, Object> {
    @Override
    protected Object doInBackground(Object... objects) {
//        try {
//            URL url = new URL(IP + "/api/auth/signin");
//
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json");
//
//            JSONObject params = new JSONObject();
//            params.put("Feedback", username);
//
//
//            OutputStream os = con.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//            writer.write(params.toString());
//            writer.flush();
//            writer.close();
//            os.close();
//
//            con.connect();
//
//            int responseCode = con.getResponseCode();
//
//            if (responseCode == 200) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//
//                in.close();
//            }
//        }
//
//        catch (Exception e) {
//            Log.e("Connection Error", e.toString());
//        }
//
        return null;
    }
}
