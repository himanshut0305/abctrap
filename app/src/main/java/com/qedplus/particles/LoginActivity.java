package com.qedplus.particles;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qedplus.particles.extras.Feeder;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.SubjectBookPair;
import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.model.SchoolBO;
import com.qedplus.particles.payload.SigninResponse;
import com.qedplus.particles.services.LoginService;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private PrefManager prefManager;
    private Feeder feeder;
    private SchoolBO schoolBO;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utility.Theme t = Utility.getTheme(getApplicationContext());

        setTheme(t.theme);
        prefManager = new PrefManager(getApplicationContext());
        schoolBO = prefManager.getSchoolDetails();

        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_login);

        Button customButton = findViewById(R.id.login_button);
        GradientDrawable drawable = (GradientDrawable) customButton.getBackground();
        drawable.setColor(t.primaryDarkColor);

        TextView schoolNameLabel = findViewById(R.id.school_name_label);
        schoolNameLabel.setText(schoolBO.getSchoolName());

        ImageView schoolLogoImage = findViewById(R.id.school_logo_image);
        Bitmap schoolLogo = BitmapFactory.decodeFile(schoolBO.getSchoolLogoFileName());
        schoolLogoImage.setImageBitmap(schoolLogo);

        ImageView loginBackground = findViewById(R.id.login_background_image);
        loginBackground.setImageResource(t.loginBackground);
    }

    public void onLoginClick(View view) {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        if(username.equals("teacher")) {
            startActivity(new Intent(LoginActivity.this, TeacherActivity.class));
        }
        else {
            LoginService loginService = new LoginService(getApplicationContext(), username, password, new LoginService.LoginTask() {
                @Override
                public void afterTask(ArrayList<SubjectBookPair> subjectBookPairs) {
                    if(subjectBookPairs.size() > 0) {
                        Intent intent = null;
                        if(prefManager.isAppInitialisedFor9th() && prefManager.isAppInitialisedFor10th()) {
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                        }
                        else {
                            intent = new Intent(LoginActivity.this, DataDownloaderActivity.class);
                        }

                        intent.putExtra("SUBJECT_BOOK_PAIRS", subjectBookPairs);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Snackbar.make(findViewById(R.id.login_activity_layout), "Invalid Credentials", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                }

                @Override
                public void duringTask(String update) {
                    Button loginBtn = findViewById(R.id.login_button);
                    loginBtn.setText(update);
                }
            });

            loginService.execute();
        }
    }

    public void onChangeSchoolClick(View view) {
        prefManager.removeSchoolDetails();

        Intent i = new Intent(LoginActivity.this, SplashActivity.class);
        LoginActivity.this.startActivity(i);
        finish();
    }
}