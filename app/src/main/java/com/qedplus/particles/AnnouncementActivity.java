package com.qedplus.particles;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qedplus.particles.db.Announcement;
import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.model.AnnouncementBO;
import com.qedplus.particles.services.AnnouncementServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AnnouncementActivity extends AppCompatActivity {

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utility.Theme t = Utility.getTheme(getApplicationContext());

        setTheme(t.theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inflater = LayoutInflater.from(getApplicationContext());
        final LinearLayout announcementActivityLayout = findViewById(R.id.announcement_activity_layout);

        final AnnouncementServices announcementServices = new AnnouncementServices(new AnnouncementServices.UITask() {
            @Override
            public void task(ArrayList<AnnouncementBO> announcements) {
                if(announcements.size() > 0) {
                    Collections.sort(announcements, new Comparator<AnnouncementBO>() {
                        @Override
                        public int compare(AnnouncementBO o1, AnnouncementBO o2) {
                            return o2.getMadeOn().compareTo(o1.getMadeOn());
                        }
                    });

                    for(AnnouncementBO announcement : announcements) {
                        View announcementListItem = inflater.inflate(R.layout.announcement_list_item, announcementActivityLayout, false);

                        TextView heading = (TextView) announcementListItem.findViewById(R.id.announcement_heading);
                        TextView text = (TextView) announcementListItem.findViewById(R.id.announcement_text);
                        TextView date = (TextView) announcementListItem.findViewById(R.id.announcement_date);

                        heading.setText(announcement.getTeacher());
                        text.setText(announcement.getAnnouncement());
                        date.setText(new SimpleDateFormat("E, dd MMM").format(announcement.getMadeOn()));

                        if(!announcement.isSeen()) {
                            heading.setTypeface(null, Typeface.BOLD);
                            date.setTextColor(getResources().getColor(R.color.blue));
                        }

                        announcementActivityLayout.addView(announcementListItem);
                    }
                }
            }
        });

        announcementServices.getAllAnnouncements(getApplicationContext());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
