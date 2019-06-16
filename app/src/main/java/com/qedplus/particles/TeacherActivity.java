package com.qedplus.particles;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.model.TeacherClassSubjectBO;
import com.qedplus.particles.services.TeacherServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeacherActivity extends AppCompatActivity {
    public static String IK_SUBJECT_NAME = "SubjectName";
    public static String IK_SCHOOL_YEAR = "SchoolYearResponse";

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utility.Theme theme = Utility.getTheme(getApplicationContext());

        setTheme(theme.theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PrefManager pm = new PrefManager(getApplicationContext());
        getSupportActionBar().setTitle(pm.getSchoolDetails().getSchoolName());

        inflater = LayoutInflater.from(getApplicationContext());
        final LinearLayout teacherActivityLayout = findViewById(R.id.teacher_activity_layout);

        final Intent i = new Intent(TeacherActivity.this, SyllabusActivity.class);

        TeacherServices teacherServices = new TeacherServices(new TeacherServices.CSTask() {
            @Override
            public void task(HashSet<TeacherClassSubjectBO> teacherClassSubjects) {
                Set<TeacherClassSubjectBO> s= teacherClassSubjects;
                List<TeacherClassSubjectBO> l = new ArrayList(s);
                Collections.sort(l, new Comparator<TeacherClassSubjectBO>() {
                    @Override
                    public int compare(TeacherClassSubjectBO o1, TeacherClassSubjectBO o2) {
                        return o1.getClassName().compareTo(o2.getClassName());
                    }
                });

                for(final TeacherClassSubjectBO tcs : l) {
                    View teacherListItem = inflater.inflate(R.layout.teacher_subject_list_item, teacherActivityLayout, false);

                    TextView cstv = teacherListItem.findViewById(R.id.class_subject_text_view);
                    TextView ccttv = teacherListItem.findViewById(R.id.current_chapter_topic_text_view);

                    cstv.setText(tcs.getClassName() + " - " + tcs.getSubjectName());
                    ccttv.setText(tcs.getCurrentChapter() + " - " + tcs.getCurrentTopic());

                    teacherActivityLayout.addView(teacherListItem);

                    ConstraintLayout teacherListItemLayout = teacherListItem.findViewById(R.id.teacher_subject_list_item_layout);

                    teacherListItemLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            i.putExtra(IK_SCHOOL_YEAR, tcs.getSchoolYear());
                            i.putExtra(IK_SUBJECT_NAME, tcs.getSubjectName());

                            startActivity(i);
                        }
                    });
                }
            }
        });

        teacherServices.getAllClasses(getApplicationContext());
    }
}
