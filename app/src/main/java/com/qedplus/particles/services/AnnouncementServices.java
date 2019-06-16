package com.qedplus.particles.services;

import android.content.Context;
import android.util.Log;

import com.qedplus.particles.dao.AnnouncementDao;
import com.qedplus.particles.db.Announcement;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.model.AnnouncementBO;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementServices {
    public interface UITask{
        void task(ArrayList<AnnouncementBO> announcements);
    }

    private UITask uiTask;

    public AnnouncementServices(UITask uiTask) {
        this.uiTask = uiTask;
    }

    public void getAllAnnouncements(final Context cxt) {
        final ArrayList<AnnouncementBO> announcementBOs = new ArrayList<>();
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);
                AnnouncementDao announcementDao = db.announcementDao();

                List<Announcement> announcements = announcementDao.getAllAnnouncements();
                for (Announcement announcement : announcements) {
                    AnnouncementBO announcementBO = new AnnouncementBO(announcement.getAnnouncement(), announcement.getMadeOn(), announcement.getTeacher(), announcement.isSeen());
                    announcementBOs.add(announcementBO);
                }
            }

            @Override
            public void afterTask() {
                uiTask.task(announcementBOs);
            }
        });

        dbService.execute();
    }

    public void getUnseenAnnouncements(final Context cxt) {
        final ArrayList<AnnouncementBO> announcementBOs = new ArrayList<>();
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);
                AnnouncementDao announcementDao = db.announcementDao();

                List<Announcement> announcements = announcementDao.getAllUnseenAnnouncements();
                for (Announcement announcement : announcements) {
                    AnnouncementBO announcementBO = new AnnouncementBO(announcement.getAnnouncement(), announcement.getMadeOn(), announcement.getTeacher(), announcement.isSeen());
                    announcementBOs.add(announcementBO);
                }
            }

            @Override
            public void afterTask() {
                uiTask.task(announcementBOs);
            }
        });

        dbService.execute();
    }

    public void updateAnnouncements(final Context cxt, final AnnouncementBO ann) {
        final ArrayList<AnnouncementBO> announcementBOs = new ArrayList<>();
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);
                AnnouncementDao announcementDao = db.announcementDao();
                announcementDao.setLatestAsSeen();

                List<Announcement> announcements = announcementDao.getAllUnseenAnnouncements();
                for (Announcement announcement : announcements) {
                    AnnouncementBO announcementBO = new AnnouncementBO(announcement.getAnnouncement(), announcement.getMadeOn(), announcement.getTeacher(), announcement.isSeen());
                    announcementBOs.add(announcementBO);
                }
            }

            @Override
            public void afterTask() {
                uiTask.task(announcementBOs);
            }
        });

        dbService.execute();
    }
}