package com.qedplus.particles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishedTestActivity extends AppCompatActivity {
    Intent practiceTestIntent;
    Intent topicsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_test);

        String subjectName = getIntent().getStringExtra(TopicsActivity.IK_SUBJECT_NAME);

        int schoolYear = getIntent().getIntExtra(TopicsActivity.IK_SCHOOL_YEAR, 0);
        int chapterIndex = getIntent().getIntExtra(TopicsActivity.IK_CHAPTER_INDEX, 0);
        int topicIndex = getIntent().getIntExtra(TopicsActivity.IK_TOPIC_INDEX, 0);
        int qptIndex = getIntent().getIntExtra(TopicsActivity.IK_QPT_INDEX, 0);

        int questionsAsked = getIntent().getIntExtra(PracticeTestActivity.IK_QUESTIONS_ASKED, 0);
        int questionsAnswered = getIntent().getIntExtra(PracticeTestActivity.IK_QUESTIONS_ANSWERED, 0);

        Log.e("SubYearChapTop", subjectName + " " + schoolYear + " " + chapterIndex + " " + topicIndex);

        practiceTestIntent = new Intent(this, PracticeTestActivity.class);

        practiceTestIntent.putExtra(TopicsActivity.IK_SUBJECT_NAME, subjectName);
        practiceTestIntent.putExtra(TopicsActivity.IK_SCHOOL_YEAR, schoolYear);
        practiceTestIntent.putExtra(TopicsActivity.IK_CHAPTER_INDEX, chapterIndex);
        practiceTestIntent.putExtra(TopicsActivity.IK_TOPIC_INDEX, topicIndex);
        practiceTestIntent.putExtra(TopicsActivity.IK_QPT_INDEX, qptIndex);

        TextView scoreView = findViewById(R.id.score_text_view);
        scoreView.setText(questionsAnswered + "/" + questionsAsked);

        topicsIntent = new Intent(this, TopicsActivity.class);
        topicsIntent.putExtra(MainActivity.IK_SUBJECT_NAME, subjectName);

        if(qptIndex > 0) {
            Button fiveMoreButton = findViewById(R.id.five_more_button);
            fiveMoreButton.setVisibility(View.GONE);

            TextView t = findViewById(R.id.practice_completed_text_view);
            t.setText("QPT Completed");
        }
    }

    public void onFiveMoreClick(View v) {
        finish();
        startActivity(practiceTestIntent);
    }

    public void onFinishClick(View v) {
        finish();
        startActivity(topicsIntent);
    }
}
