package com.qedplus.particlesTeacher;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qedplus.particlesTeacher.db.Topic;
import com.qedplus.particlesTeacher.extras.PrefManager;
import com.qedplus.particlesTeacher.extras.Utility;
import com.qedplus.particlesTeacher.model.ChapterBO;
import com.qedplus.particlesTeacher.model.TeacherClassSubjectBO;
import com.qedplus.particlesTeacher.model.TopicBO;
import com.qedplus.particlesTeacher.services.SyllabusServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


    public class SyllabusActivity extends AppCompatActivity {
        LayoutInflater inflater;
        LinearLayout parentLayout;

        Intent teacherIntent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            Utility.Theme theme = Utility.getTheme(getApplicationContext());

            setTheme(theme.theme);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_syllabus);

            inflater = LayoutInflater.from(getApplicationContext());
            parentLayout = findViewById(R.id.syllabus_activity_layout);
            String subjectName = getIntent().getStringExtra(TeacherActivity.IK_SUBJECT_NAME);
            int schoolYear = getIntent().getIntExtra(TeacherActivity.IK_SCHOOL_YEAR, 0);

            teacherIntent = new Intent(this, TeacherActivity.class);

            SyllabusServices syllabusServices = new SyllabusServices(new SyllabusServices.SyllabusUITask() {
                @Override
                public void task(HashSet<ChapterBO> chapters) {
                    Set<ChapterBO> s= chapters;
                    ArrayList<ChapterBO> l = new ArrayList(s);

                    Collections.sort(l, new Comparator<ChapterBO>() {
                        @Override
                        public int compare(ChapterBO o1, ChapterBO o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });

                    for(ChapterBO chapter : l) {
                        View cv = inflater.inflate(R.layout.syllabus_chapter_list_item, parentLayout, false);

                        TextView chapterTextView = cv.findViewById(R.id.syllabus_chapter_list_item_text_view);
                        chapterTextView.setText(chapter.getChapterIndex() + " - " + chapter.getName());
                        HashSet<TopicBO> topics = chapter.getRevisions();

                        parentLayout.addView(cv);

                        for(TopicBO topic : topics) {
                            View tv = inflater.inflate(R.layout.syllabus_topic_list_item, parentLayout, false);

                            CheckBox checkBox = tv.findViewById(R.id.syllabus_topic_checkbox);
                            TextView topicTextView = tv.findViewById(R.id.syllabus_topic_text_view);
                            topicTextView.setText(topic.getName());

                            if(topic.isUnlocked() || topic.isActive() || topic.isRevised()) {
                                checkBox.setChecked(true);
                                checkBox.setEnabled(false);
                            }

                            parentLayout.addView(tv);
                        }
                    }
                }
            });

            syllabusServices.getTocForSubject(getApplicationContext(), subjectName, schoolYear);
        }

        public void onSubmitClick(View v) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            startActivity(teacherIntent);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:

                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(SyllabusActivity.this, R.style.myDialog));
            builder.setMessage("Mark these topics as COMPLETED? Action cannot be undone")
                    .setPositiveButton("CONFIRM", dialogClickListener)
                    .setNegativeButton("CANCEL", dialogClickListener)
                    .show();
        }
    }


