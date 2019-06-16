package com.qedplus.particles;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.qedplus.particles.extras.DownloadServiceManager;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.SubjectBookPair;
import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.model.AnnouncementBO;
import com.qedplus.particles.model.SchoolBO;
import com.qedplus.particles.services.AnnouncementServices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static PrefManager prefManager;
    private SchoolBO schoolBO;
    private static Context context;
    private static Activity activity;
    private static Utility.Theme t;

    private static Drawable cardBackground;

    private ArrayList<SubjectBookPair> subjectBookPairs;

    public static String IK_SUBJECT_NAME = "SubjectName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        t = Utility.getTheme(getApplicationContext());
        cardBackground = getResources().getDrawable(t.cardGradient);
        prefManager = new PrefManager(getApplicationContext());
        schoolBO = prefManager.getSchoolDetails();

        setTheme(t.altTheme);
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        subjectBookPairs = (ArrayList<SubjectBookPair>) getIntent().getSerializableExtra("SUBJECT_BOOK_PAIRS");

        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        activity = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(schoolBO.getSchoolName());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        if(prefManager.hasSpaceBadge()) {
            ConstraintLayout constraintLayout = findViewById(R.id.badge_layout);
            constraintLayout.setVisibility(View.VISIBLE);
            prefManager.setSpaceBadge(true);
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() { }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = null;
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_main_1, container, false);
                    FrameLayout frameLayout = rootView.findViewById(R.id.announcement_card_layout);
                    frameLayout.setBackgroundColor(t.primaryDarkColor);

                    prefManager = new PrefManager(context);

                    if(prefManager.isFirstTimeLaunch()) {
                        TapTargetView.showFor(activity,
                                TapTarget.forView(rootView.findViewById(R.id.new_science_chapters_indicator), "New Topics Unlocked", "Look out for this indicator. It appears when your teacher unlocks a new topic for you to study.")
                                        .outerCircleColor(R.color.black)
                                        .outerCircleAlpha(0.90f)
                                        .targetCircleColor(R.color.white)
                                        .titleTextSize(20)
                                        .titleTextColor(R.color.white)
                                        .descriptionTextSize(12)
                                        .descriptionTextColor(R.color.red)
                                        .textColor(R.color.white)
                                        .textTypeface(Typeface.SANS_SERIF)
                                        .dimColor(R.color.black)
                                        .drawShadow(true)
                                        .cancelable(false)
                                        .tintTarget(true)
                                        .transparentTarget(false)
                                        .targetRadius(60),
                                new TapTargetView.Listener() {
                                    @Override
                                    public void onTargetClick(TapTargetView view) {
                                        super.onTargetClick(view);
                                    }
                                });

                        prefManager.setFirstTimeLaunch(false);
                    }

                    final CardView scienceCard = rootView.findViewById(R.id.science_card_view);
                    final CardView socialScienceCard = rootView.findViewById(R.id.social_science_card_view);
                    final CardView englishCard = rootView.findViewById(R.id.english_card_view);
                    final CardView mathsCard = rootView.findViewById(R.id.mathematics_card_view);

                    final HashMap<String, Boolean> toggle = new HashMap<>();

                    toggle.put("SCIENCE", true);
                    toggle.put("SSC", true);
                    toggle.put("ENGLISH", true);

                    View.OnClickListener onClickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(final View cardView) {
                            final ViewGroup v = (ViewGroup) cardView;
                            final String tag = (String) cardView.getTag();

                            final ObjectAnimator oa1, oa2;

                            if(toggle.get(tag)) {
                                oa1 = ObjectAnimator.ofFloat(cardView, "rotationX", 0, 100);
                                oa2 = ObjectAnimator.ofFloat(cardView, "rotationX", 100, 180);
                            }
                            else {
                                oa1 = ObjectAnimator.ofFloat(cardView, "rotationX", 180, 270);
                                oa2 = ObjectAnimator.ofFloat(cardView, "rotationX", 270, 360);
                            }

                            oa1.setInterpolator(new AccelerateInterpolator());
                            oa2.setInterpolator(new AccelerateDecelerateInterpolator());

                            oa1.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        if(toggle.get(tag)) {
                                            v.getChildAt(0).setVisibility(View.INVISIBLE);
                                            v.getChildAt(1).setVisibility(View.VISIBLE);

                                            toggle.put(tag, false);
                                        }
                                        else {
                                            v.getChildAt(0).setVisibility(View.VISIBLE);
                                            v.getChildAt(1).setVisibility(View.INVISIBLE);

                                            toggle.put(tag, true);
                                        }
                                    }

                                    oa2.setDuration(250);
                                    oa2.start();
                                }
                            });

                            oa1.setDuration(250);
                            oa1.start();
                        }
                    };

                    scienceCard.setOnClickListener(onClickListener);
                    socialScienceCard.setOnClickListener(onClickListener);
                    englishCard.setOnClickListener(onClickListener);

                    final FrameLayout f1 = rootView.findViewById(R.id.announcement_card_layout);
                    final FrameLayout f2 = rootView.findViewById(R.id.no_more_announcement_card_layout);

                    f1.setBackgroundColor(t.primaryDarkColor);
                    f2.setBackgroundColor(t.primaryDarkColor);

                    final TextView announcementTeacher = rootView.findViewById(R.id.announcement_heading);
                    final TextView announcementText = rootView.findViewById(R.id.announcement_text);
                    final TextView announcementDate = rootView.findViewById(R.id.announcement_date);

                    Button announcementOkButton = rootView.findViewById(R.id.announcement_ok_button);

                    final ArrayList<AnnouncementBO> topOne = new ArrayList<>();
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

                                AnnouncementBO earliestAnnouncement = announcements.get(0);

                                announcementTeacher.setText(earliestAnnouncement.getTeacher());
                                announcementText.setText(earliestAnnouncement.getAnnouncement());
                                announcementDate.setText(new SimpleDateFormat("E, dd MMM").format(earliestAnnouncement.getMadeOn()));

                                announcements.get(0).setSeen(true);
                                topOne.add(announcements.get(0));
                            }
                            else {
                                f1.setVisibility(View.GONE);
                                f2.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                    announcementServices.getUnseenAnnouncements(context);

                    announcementOkButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            announcementServices.updateAnnouncements(context, topOne.get(0));
                        }
                    });

                    f2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(context, AnnouncementActivity.class));
                        }
                    });

                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_main_2, container, false);

                    TextView proficiencyNumber = rootView.findViewById(R.id.proficiency_number_label);
                    TextView proficiencyText = rootView.findViewById(R.id.proficiency_number_text);

                    proficiencyNumber.setBackgroundColor(t.primaryDarkColor);
                    proficiencyText.setBackgroundColor(t.primaryDarkColor);

                    ConstraintLayout constraintLayout = rootView.findViewById(R.id.report_card_layout);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        constraintLayout.setBackground(cardBackground);
                    }
                    else {
                        constraintLayout.setBackgroundDrawable(cardBackground);
                    }

                    BarChart chart = rootView.findViewById(R.id.chart_analysis);

                    List<BarEntry> arrayList = new ArrayList<BarEntry>();

                    arrayList.add(new BarEntry(1, 20));
                    arrayList.add(new BarEntry(2, 6));
                    arrayList.add(new BarEntry(3, 10));
                    arrayList.add(new BarEntry(4, 22));
                    arrayList.add(new BarEntry(5, 28));
                    arrayList.add(new BarEntry(6, 8));
                    arrayList.add(new BarEntry(7, 20));
                    arrayList.add(new BarEntry(8, 15));
                    arrayList.add(new BarEntry(9, 10));
                    arrayList.add(new BarEntry(10, 22));
                    arrayList.add(new BarEntry(11, 8));
                    arrayList.add(new BarEntry(12, 12));
                    arrayList.add(new BarEntry(13, 20));
                    arrayList.add(new BarEntry(14, 24));
                    arrayList.add(new BarEntry(15, 18));
                    arrayList.add(new BarEntry(16, 22));
                    arrayList.add(new BarEntry(17, 30));
                    arrayList.add(new BarEntry(18, 18));

                    BarDataSet dataSet = new BarDataSet(arrayList, "Marks per Test");
                    BarData lineData = new BarData(dataSet);

                    List<Integer> colorList = new ArrayList<>();
                    colorList.add(Color.parseColor("#cc000000"));
                    dataSet.setColors(colorList);
                    dataSet.setDrawValues(false);
                    dataSet.setBarBorderWidth(0f);

                    chart.setViewPortOffsets(0f, 0f, 0f, 0f);
                    chart.setData(lineData);
                    chart.getAxisLeft().setDrawGridLines(false);
                    chart.getAxisRight().setDrawGridLines(false);
                    chart.getXAxis().setDrawGridLines(false);
                    chart.getXAxis().setEnabled(false);
                    chart.getAxisLeft().setDrawAxisLine(false);
                    chart.getAxisRight().setDrawAxisLine(false);
                    chart.setScaleEnabled(false);
                    chart.setDescription(null);
                    chart.getAxisLeft().setDrawLabels(false);
                    chart.getAxisRight().setDrawLabels(false);
                    chart.getXAxis().setDrawLabels(false);
                    chart.getLegend().setEnabled(false);

                    chart.invalidate();

                    LinkedHashMap<String, ArrayList<String>> subjects = Utility.getSubjects();
                    LinearLayout fragmentLayout = rootView.findViewById(R.id.fragment_main_2_layout);

                    for(LinkedHashMap.Entry<String, ArrayList<String>> entry : subjects.entrySet()) {
                        View subjectReportCard = inflater.inflate(R.layout.report_subject_card, fragmentLayout, false);
                        LinearLayout subjectReportCardLayout = subjectReportCard.findViewById(R.id.report_subject_card_layout);
                        fragmentLayout.addView(subjectReportCard);

                        for(String subName : entry.getValue()) {
                            View reportSubjectListItem = inflater.inflate(R.layout.report_subject_list_item, subjectReportCardLayout, false);
                            ((TextView) reportSubjectListItem.findViewById(R.id.report_subject_name)).setText(subName);
                            ((Button) reportSubjectListItem.findViewById(R.id.report_subject_details_button)).setTextColor(t.primaryDarkColor);
                            subjectReportCardLayout.addView(reportSubjectListItem);
                        }
                    }

                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_main_3, container, false);

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ImageView announcementIcon = rootView.findViewById(R.id.announcement_icon);
                        ImageView badgeIcon = rootView.findViewById(R.id.badge_icon);
                        ImageView bookmarkIcon = rootView.findViewById(R.id.bookmark_icon);
                        ImageView accountIcon = rootView.findViewById(R.id.account_icon);

                        Button logoutButton = rootView.findViewById(R.id.logout_button);

                        VectorDrawable ann = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.icon_personal_announcement);
                        VectorDrawable bad = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.icon_personal_badge);
                        VectorDrawable boo = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.icon_personal_bookmark);
                        VectorDrawable acc = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.icon_personal_account);

                        Drawable log = ContextCompat.getDrawable(context, R.drawable.custom_button_small);

                        ann.setTint(t.primaryDarkColor);
                        bad.setTint(t.primaryDarkColor);
                        boo.setTint(t.primaryDarkColor);
                        acc.setTint(t.primaryDarkColor);

                        log.setTint(t.primaryDarkColor);

                        announcementIcon.setImageDrawable(ann);
                        badgeIcon.setImageDrawable(bad);
                        bookmarkIcon.setImageDrawable(boo);
                        accountIcon.setImageDrawable(acc);

                        logoutButton.setBackground(log);

                        if(prefManager.getUserDetails() != null) {
                            ((TextView) rootView.findViewById(R.id.student_name)).setText(prefManager.getUserDetails().getName());
                            ((TextView) rootView.findViewById(R.id.student_username)).setText(prefManager.getUserDetails().getUsername());
                        }
                    }

                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_main_1, container, false);
                    break;
            }

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void onSubjectClick(View v) {
        String subjectName = (String) v.getTag();
        Intent i = new Intent(MainActivity.this, TopicsActivity.class);
        i.putExtra(IK_SUBJECT_NAME, subjectName);
        MainActivity.this.startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(i);
                break;

            case R.id.action_faq:
                Intent k = new Intent(MainActivity.this, FAQActivity.class);
                MainActivity.this.startActivity(k);
                break;

            case R.id.action_logout:
                prefManager.removeUserDetails();
                Intent j = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(j);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAnalysisClick(View v) {
        Intent j = new Intent(MainActivity.this, AnalysisActivity.class);
        MainActivity.this.startActivity(j);
    }

    public void onFacebookShareClick(View v) {
        Bitmap image = getBitmap();
        onClickApp("Jiten just received a new badge from his school", image, "com.facebook.katana");
    }

    public void onInstagramShareClick(View v) {
        Bitmap image = getBitmap();
        onClickApp("Jiten just received a new badge from his school", image, "com.instagram.android");
    }

    public void onWhatsappShareClick(View v) {
        Bitmap image = getBitmap();
        onClickApp("Jiten just received a new badge from his school", image, "com.whatsapp");
    }

    public void onClickApp(String msg, Bitmap bitmap, String packageName) {
        PackageManager pm = context.getPackageManager();

        try {
            Log.e("Bitmap", "Sharing");

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
            Uri imageUri = Uri.parse(path);

            @SuppressWarnings("unused")
            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("image/*");
            waIntent.setPackage(packageName);
            waIntent.putExtra(android.content.Intent.EXTRA_STREAM, imageUri);
            waIntent.putExtra(Intent.EXTRA_TEXT, msg);
            waIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.this.startActivity(Intent.createChooser(waIntent, "Share with"));
        }
        catch (Exception e) {
            Log.e("Error on sharing", e + " ");
            Snackbar.make(findViewById(R.id.main_content), "App Not Installed", Snackbar.LENGTH_LONG).show();
        }
    }

    private Bitmap getBitmap() {
        Log.e("Bitmap", "Reaching");

        FrameLayout frameLayout = findViewById(R.id.instagram_post);
        ImageView instaSchoolLogo = findViewById(R.id.school_logo_image);

        String schoolLogoFileName = "school_logo_" + schoolBO.getSchoolLogoFileName();
        int resId = getResources().getIdentifier(schoolLogoFileName, "drawable", getPackageName());
        instaSchoolLogo.setImageResource(resId);

        Bitmap bitmap = getScreenshot(frameLayout);

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        String path = Environment.getExternalStorageDirectory().toString() + "/screenshot" + ts + ".jpg";

        try {
            Log.e("Bitmap", "Trying");

            FileOutputStream outputStream = new FileOutputStream(new File(path));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        }
        catch (Exception e) {
            Log.e("Bitmap", e.toString());
            e.printStackTrace();
        }

        return  bitmap;
    }

    private Bitmap getScreenshot(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }

    public void onBadgeLayoutClick(View v) {
        v.setVisibility(View.GONE);
    }

    public void onAnnouncementsClick(View v) {
        Intent i = new Intent(MainActivity.this, AnnouncementActivity.class);
        startActivity(i);
    }

    public void onBadgesClick(View v) {
        Intent i = new Intent(MainActivity.this, BadgeActivity.class);
        startActivity(i);
    }

    public void onBookmarksClick(View v) {
        Toast.makeText(getApplicationContext(), "You haven't bookmarked any topic yet", Toast.LENGTH_LONG).show();
    }

    public void onLogoutClick(View v) {
        prefManager.removeUserDetails();
        Intent j = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(j);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}