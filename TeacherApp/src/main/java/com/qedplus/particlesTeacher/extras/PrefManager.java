package com.qedplus.particlesTeacher.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.qedplus.particlesTeacher.db.User;
import com.qedplus.particlesTeacher.model.SchoolBO;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "qed-preferences";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_APP_INITIALISED_FOR_9TH = "IsAppInitialisedFor9th";
    private static final String IS_APP_INITIALISED_FOR_10TH = "IsAppInitialisedFor10th";

    private static final String SCHOOL_DETAILS = "SchoolDetails";
    private static final String HAS_SPACE_BADGE = "HasSpaceBadge";
    private static final String HAS_EVEREST_BADGE = "HasEverestBadge";

    private static final String USER_DETAILS = "UserDetails";
    private static final String SCHOOL_YEAR = "SchoolYear";

    Gson gson;

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        gson = new Gson();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void setSpaceBadge(boolean hasSpaceBadge) {
        editor.putBoolean(HAS_SPACE_BADGE, hasSpaceBadge);
        editor.commit();
    }

    public void setEverestBadge(boolean hasEverestBadge) {
        editor.putBoolean(HAS_EVEREST_BADGE, hasEverestBadge);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setSchoolYear(int schoolYear) {
        Log.e("Setting Year:", schoolYear + "");
        editor.putInt(SCHOOL_YEAR, schoolYear);
        editor.commit();
    }

    public int getSchoolYear() {
        return pref.getInt(SCHOOL_YEAR, 0);
    }

    public void removeSchoolYear() {
        editor.remove(SCHOOL_YEAR);
        editor.commit();
    }

    public void setIsAppInitialisedFor9th(boolean isAppInitialisedFor9th) {
        editor.putBoolean(IS_APP_INITIALISED_FOR_9TH, isAppInitialisedFor9th);
        editor.commit();
    }

    public boolean isAppInitialisedFor9th() {
        return pref.getBoolean(IS_APP_INITIALISED_FOR_9TH, false);
    }

    public void setIsAppInitialisedFor10th(boolean isAppInitialisedFor10th) {
        editor.putBoolean(IS_APP_INITIALISED_FOR_10TH, isAppInitialisedFor10th);
        editor.commit();
    }

    public boolean isAppInitialisedFor10th() {
        return pref.getBoolean(IS_APP_INITIALISED_FOR_10TH, false);
    }

    public boolean hasSpaceBadge() {
        return pref.getBoolean(HAS_SPACE_BADGE, false);
    }

    public boolean hasEverestBadge() {
        return pref.getBoolean(HAS_EVEREST_BADGE, false);
    }

    public void setSchoolDetails(SchoolBO schoolBO) {
        String schoolDetailString = gson.toJson(schoolBO);
        editor.putString(SCHOOL_DETAILS, schoolDetailString);
        editor.commit();
    }

    public SchoolBO getSchoolDetails() {
        String schoolDetailString = pref.getString(SCHOOL_DETAILS, "");
        if (schoolDetailString.equals(""))
            return null;

        return gson.fromJson(schoolDetailString, SchoolBO.class);
    }

    public void removeSchoolDetails() {
        editor.remove(SCHOOL_DETAILS);
        editor.commit();
    }

    public void setUserDetails(User user) {
        String userDetails = gson.toJson(user);
        editor.putString(USER_DETAILS, userDetails);
        editor.commit();
    }

    public User getUserDetails() {
        String userDetailString = pref.getString(USER_DETAILS, "");
        if (userDetailString.equals(""))
            return null;

        return gson.fromJson(userDetailString, User.class);
    }

    public void removeUserDetails() {
        editor.remove(USER_DETAILS);
        editor.commit();
    }
}