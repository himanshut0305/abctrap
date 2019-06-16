package com.qedplus.particlesTeacher;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.qedplus.particlesTeacher.extras.PrefManager;
import com.qedplus.particlesTeacher.extras.Utility;
import com.qedplus.particlesTeacher.model.SchoolBO;
import com.qedplus.particlesTeacher.services.SchoolServices;
import com.qedplus.particlesTeacher.services.TeacherUserServices;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private PrefManager prefManager;
    private SchoolBO schoolBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //startService(new Intent(this, UpdaterServiceManager.class));

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        context = this.getApplicationContext();


        prefManager = new PrefManager(this);
        schoolBO = prefManager.getSchoolDetails();

        if(prefManager.isFirstTimeLaunch()) {
            demoDataFeeder();
        }

        if (schoolBO == null) {
            setContentView(R.layout.activity_splash_first_time);

            final EditText et1 = (EditText) findViewById(R.id.et1);
            final EditText et2 = (EditText) findViewById(R.id.et2);
            final EditText et3 = (EditText) findViewById(R.id.et3);

            final EditText et4 = (EditText) findViewById(R.id.et4);
            final EditText et5 = (EditText) findViewById(R.id.et5);
            final EditText et6 = (EditText) findViewById(R.id.et6);

            final ColorStateList colorStateList = ColorStateList.valueOf(Color.WHITE);

            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }

                @Override
                public void afterTextChanged(Editable s) {
                    int textLength = s.toString().length();

                    if (et1.getText().hashCode() == s.hashCode()) {
                        if (textLength == 1)
                            et2.requestFocus();
                    }

                    if (et2.getText().hashCode() == s.hashCode()) {
                        if (textLength == 1)
                            et3.requestFocus();
                        if (textLength == 0)
                            et1.requestFocus();
                    }

                    if (et3.getText().hashCode() == s.hashCode()) {
                        if (textLength == 1)
                            et4.requestFocus();
                        if (textLength == 0)
                            et2.requestFocus();
                    }

                    if (et4.getText().hashCode() == s.hashCode()) {
                        if (textLength == 1)
                            et5.requestFocus();
                        if (textLength == 0)
                            et3.requestFocus();
                    }

                    if (et5.getText().hashCode() == s.hashCode()) {
                        if (textLength == 1)
                            et6.requestFocus();
                        if (textLength == 0)
                            et4.requestFocus();
                    }

                    if (et6.getText().hashCode() == s.hashCode()) {
                        if (textLength == 0)
                            et5.requestFocus();
                        if (textLength == 1) {
                            ViewCompat.setBackgroundTintList(et6, colorStateList);
                            String submittedSchoolCode = et1.getText().toString() + et2.getText().toString() + et3.getText().toString() + et4.getText().toString() + et5.getText().toString() + et6.getText().toString();
                            submittedSchoolCode.toUpperCase();

                            if(submittedSchoolCode.length() == 6) {
                                SchoolServices schoolServices = new SchoolServices(new SchoolServices.UITask() {
                                    @Override
                                    public void task(SchoolBO school) {
                                        if (school == null) {
                                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                                            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                                            Snackbar.make(findViewById(R.id.splash_activity_layout), "School Code is Invalid", Snackbar.LENGTH_INDEFINITE)
                                                    .setAction("Clear", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            et6.setText("");
                                                            et5.setText("");
                                                            et4.setText("");
                                                            et3.setText("");
                                                            et2.setText("");
                                                            et1.setText("");

                                                            et6.clearFocus();
                                                        }
                                                    })
                                                    .setActionTextColor(ContextCompat.getColor(context, R.color.blue))
                                                    .show();
                                        }
                                        else {
                                            prefManager.setSchoolDetails(school);

                                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                            SplashActivity.this.startActivity(intent);
                                            finish();
                                        }
                                    }
                                });

                                Log.e("School Code :", submittedSchoolCode);
                                schoolServices.checkIfSchoolExists(getApplicationContext(), submittedSchoolCode);
                            }
                        }
                    }
                }
            };

            et1.addTextChangedListener(textWatcher);
            et2.addTextChangedListener(textWatcher);
            et3.addTextChangedListener(textWatcher);

            et4.addTextChangedListener(textWatcher);
            et5.addTextChangedListener(textWatcher);
            et6.addTextChangedListener(textWatcher);

            View.OnKeyListener onKeyListener = new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (i == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == 0) {
                        if (view.equals(et2)) et1.requestFocus();
                        if (view.equals(et3)) et2.requestFocus();
                        if (view.equals(et4)) et3.requestFocus();
                        if (view.equals(et5)) et4.requestFocus();
                        if (view.equals(et6)) et5.requestFocus();
                    }

                    return false;
                }
            };

            et1.setOnKeyListener(onKeyListener);
            et2.setOnKeyListener(onKeyListener);
            et3.setOnKeyListener(onKeyListener);

            et4.setOnKeyListener(onKeyListener);
            et5.setOnKeyListener(onKeyListener);
            et6.setOnKeyListener(onKeyListener);
        }
        else {
            Utility.Theme t = Utility.getTheme(getApplicationContext());

            setContentView(R.layout.activity_splash_regular);
            CoordinatorLayout coordinatorLayout = findViewById(R.id.splash_activity_layout);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                coordinatorLayout.setBackground(getResources().getDrawable(t.gradientBackground));
            }

            ImageView schoolLogoImage = findViewById(R.id.school_splash_logo_image);
            Bitmap schoolLogo = BitmapFactory.decodeFile(schoolBO.getSchoolLogoFileName());
            schoolLogoImage.setImageBitmap(schoolLogo);
        }

        if(!isStoragePermissionGranted()) {
            setContentView(R.layout.storage_permission_layout);
        }
    }

    @Override
    public void onClick(View v) { }

    public void onSchoolLogoClick(View v) {
        TeacherUserServices teacherUserServices = new TeacherUserServices(getApplicationContext(), new TeacherUserServices.UITask() {
            @Override
            public void task(boolean hasAccessToken, boolean accessTokenIsStillValid) {
                if(hasAccessToken && accessTokenIsStillValid) {
                    Intent intent = new Intent(SplashActivity.this, TeacherActivity.class);
                    SplashActivity.this.startActivity(intent);
                    finish();
                }
                else if(hasAccessToken) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(intent);
                    finish();
                }
            }
        });

        teacherUserServices.getTeacherDetails(context);
    }

    public void demoDataFeeder() {
        prefManager.setFirstTimeLaunch(false);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("Bitmap","Storage Permission is granted");
                return true;
            }
            else {
                Log.v("Bitmap","Storage Permission is not granted yet");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            Log.v("Bitmap","Storage Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1 : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(SplashActivity.this, SplashActivity.class);
                    SplashActivity.this.startActivity(i);
                    Toast.makeText(getApplicationContext(), "Thank you for granting permission", Toast.LENGTH_SHORT).show();
                }
                else {
                    setContentView(R.layout.storage_permission_denied_layout);
                }

                return;
            }
        }
    }

    public void onWakeApClick(View v) {
        Intent i = new Intent(SplashActivity.this, SplashActivity.class);
        SplashActivity.this.startActivity(i);
    }
}