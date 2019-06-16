package com.qedplus.particles.services;

import android.content.Context;
import android.util.Log;

import com.qedplus.particles.dao.BadgeDao;
import com.qedplus.particles.db.Badge;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.model.BadgeBO;

import java.util.ArrayList;
import java.util.List;

public class BadgeServices {
    public interface UITask{
        void task(ArrayList<BadgeBO> badges);
    }

    private BadgeServices.UITask uiTask;

    public BadgeServices(BadgeServices.UITask uiTask) {
        this.uiTask = uiTask;
    }

    public void getAllBadges(final Context cxt) {
        final ArrayList<BadgeBO> badgeBOS = new ArrayList<>();
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);
                BadgeDao badgeDao = db.badgeDao();

                List<Badge> badges = badgeDao.getAllBadges();
                for (Badge badge : badges) {
                    Log.e("Badge Service", badge.toString());
                    BadgeBO badgeBO = new BadgeBO(badge.getName(), badge.getType(), badge.getUrl(), badge.getDescription(), badge.getAcquiredOn(), badge.isSeen());
                    badgeBOS.add(badgeBO);
                }
            }

            @Override
            public void afterTask() {
                uiTask.task(badgeBOS);
            }
        });

        dbService.execute();
    }
}
