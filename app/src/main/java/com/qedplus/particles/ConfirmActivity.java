package com.qedplus.particles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

//        ((TextView) findViewById(R.id.subject_name)).setText(getIntent().getExtras().getString("subject"));
//        ((TextView) findViewById(R.id.subject_topics)).setText(getIntent().getExtras().getString("topics"));
//        ((TextView) findViewById(R.id.teacher_name)).setText(getIntent().getExtras().getString("teacher"));
//        ((TextView) findViewById(R.id.number_of_questions)).setText(getIntent().getExtras().getInt("noq") + " questions");
    }

    public void onStartTestClick(View v) {
        Intent i = new Intent(ConfirmActivity.this, PracticeTestActivity.class);
        ConfirmActivity.this.startActivity(i);
    }

    public void onRefresherClick(View v) {
        Intent i = new Intent(ConfirmActivity.this, RevisionActivity.class);
        ConfirmActivity.this.startActivity(i);
    }
}
