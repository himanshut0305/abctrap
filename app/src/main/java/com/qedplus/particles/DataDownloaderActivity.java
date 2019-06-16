package com.qedplus.particles;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.qedplus.particles.dao.SchoolClassDao;
import com.qedplus.particles.db.School;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.extras.DownloadServiceManager;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.SubjectBookPair;
import com.qedplus.particles.extras.UpdaterServiceManager;
import com.qedplus.particles.services.DataDownloadService;
import com.qedplus.particles.services.DataDownloadService;
import com.qedplus.particles.services.InitialiseService;

import java.util.ArrayList;
import java.util.Random;

public class DataDownloaderActivity extends AppCompatActivity {
    ArrayList<SubjectBookPair> subjectBookPairs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_downloader);

        subjectBookPairs = (ArrayList<SubjectBookPair>) getIntent().getSerializableExtra("SUBJECT_BOOK_PAIRS");

        InitialiseService initialiseService = new InitialiseService(getApplicationContext(), new InitialiseService.InitTask() {
            @Override
            public void afterTask(SchoolClass schoolClass) {
                startService(new Intent(DataDownloaderActivity.this, DownloadServiceManager.class));
                PrefManager prefManager = new PrefManager(getApplicationContext());

                if(schoolClass.getSchoolYear() == 9) {
                    prefManager.setIsAppInitialisedFor9th(true);
                }
                else if(schoolClass.getSchoolYear() == 10) {
                    prefManager.setIsAppInitialisedFor10th(true);
                }

                Intent intent = new Intent(DataDownloaderActivity.this, MainActivity.class);
                intent.putExtra("SUBJECT_BOOK_PAIRS", subjectBookPairs);
                startActivity(intent);
                finish();
            }

            @Override
            public void duringTask(String progress) {
                Button button = findViewById(R.id.init_progress_update_btn);
                button.setText(progress);

                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//                button.setBackgroundColor(color);
                button.setTextColor(color);
            }
        });

        initialiseService.execute();
    }
}