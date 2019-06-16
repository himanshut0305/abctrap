package com.qedplus.particlesTeacher.services;

import android.content.Context;

public class UserServices {
    public interface UITask {
        void task(boolean doesExist);
    }

    private UserServices.UITask uiTask;

    public UserServices(UserServices.UITask uiTask) {
        this.uiTask = uiTask;
    }

    public void checkIfUserExists(final Context cxt, String username) {

    }
}
