package com.qedplus.particles;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.services.LoginService;

public class FinishedRevisionActivity extends AppCompatActivity {
    Utility.Theme theme;
    Intent topicsIntent;
    Intent practiceTestIntent;

    private int feedback_value;
    private ImageView emoji_1;
    private ImageView emoji_2;
    private ImageView emoji_3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_revision);
        theme = Utility.getTheme(getApplicationContext());


        feedback_value=0;
        emoji_1 = findViewById(R.id.emoji_not_so_well);
        emoji_2 = findViewById(R.id.emoji_very_well);
        emoji_3 = findViewById(R.id.emoji_nothing_at_all);

        Log.e("PrimaryCol :", theme.primaryColor + "");

        String subjectName = getIntent().getStringExtra(TopicsActivity.IK_SUBJECT_NAME);

        int schoolYear = getIntent().getIntExtra(TopicsActivity.IK_SCHOOL_YEAR, 0);
        int chapterIndex = getIntent().getIntExtra(TopicsActivity.IK_CHAPTER_INDEX, 0);
        int topicIndex = getIntent().getIntExtra(TopicsActivity.IK_TOPIC_INDEX, 0);
        int qptIndex = getIntent().getIntExtra(TopicsActivity.IK_QPT_INDEX, 0);

        topicsIntent = new Intent(FinishedRevisionActivity.this, TopicsActivity.class);
        topicsIntent.putExtra(MainActivity.IK_SUBJECT_NAME, subjectName);

        practiceTestIntent = new Intent(this, PracticeTestActivity.class);

        practiceTestIntent.putExtra(TopicsActivity.IK_SUBJECT_NAME, subjectName);
        practiceTestIntent.putExtra(TopicsActivity.IK_SCHOOL_YEAR, schoolYear);
        practiceTestIntent.putExtra(TopicsActivity.IK_CHAPTER_INDEX, chapterIndex);
        practiceTestIntent.putExtra(TopicsActivity.IK_TOPIC_INDEX, topicIndex);
        practiceTestIntent.putExtra(TopicsActivity.IK_QPT_INDEX, qptIndex);
    }

    public void onContinueClick(View v){
//        LoginService loginService = new LoginService(getApplicationContext(), feedback_value);

        if(feedback_value != 0)
            Toast.makeText(getApplicationContext(), "Thankyou! For your Feedback", Toast.LENGTH_LONG).show();

        finish();
        FinishedRevisionActivity.this.startActivity(topicsIntent);


    }

    public void onPracticeClick(View v) {
        finish();
        FinishedRevisionActivity.this.startActivity(practiceTestIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onNotSoWellEmojiClick(View v){



        emoji_1.setColorFilter(theme.primaryColor);
        feedback_value = 1;

        emoji_2.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
        emoji_3.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));


    }
    public void onVeryWellEmojiClick(View v){


        emoji_2.setColorFilter(theme.primaryColor);
        feedback_value=2;
        emoji_1.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
        emoji_3.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));

    }
    public void onNothingAtAllEmojiClick(View v){

        emoji_3.setColorFilter(theme.primaryColor);
        feedback_value=3;
        emoji_1.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
        emoji_2.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));

    }
    public void setFeedback_value()
    {

    }


}
