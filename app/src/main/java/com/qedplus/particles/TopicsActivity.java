package com.qedplus.particles;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.extras.PrefManager;
import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.model.ChapterBO;
import com.qedplus.particles.model.QptBO;
import com.qedplus.particles.model.TopicBO;
import com.qedplus.particles.services.ContentUpdateService;
import com.qedplus.particles.services.DataDownloadService;
import com.qedplus.particles.services.TopicServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class TopicsActivity extends AppCompatActivity {
    public static final String IK_SCHOOL_YEAR = "SchoolYearResponse";
    public static final String IK_SUBJECT_NAME = "SubjectName";
    public static final String IK_CHAPTER_INDEX = "ChapterIndex";
    public static final String IK_TOPIC_INDEX = "TopicIndex";

    public static final String IK_QPT_INDEX = "QPTIndex";

    private String subjectName;
    private Utility.Theme t;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        t = Utility.getTheme(getApplicationContext());

        setTheme(t.theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        initialiseActivity();
        applyThemeColorsToElements();
        prefManager = new PrefManager(getApplicationContext());

        TopicServices topicServices = new TopicServices(new TopicServices.UITask() {
            @Override
            public void task(List<ChapterBO> chapters) {
                LinearLayout linearLayout = findViewById(R.id.toc_layout);

                if(chapters != null && chapters.size() > 0) {
                    LinearLayout downloadLayout = findViewById(R.id.subject_content_download_layout);
                    downloadLayout.setVisibility(View.GONE);
                }

                int totalChapters = chapters.size();
                for(int i = 0; i < totalChapters; i++) {
                    final ChapterBO chapter = chapters.get(i);

                    View v = getLayoutInflater().inflate(R.layout.toc_chapter_heading, null);
                    ((TextView) v.findViewById(R.id.toc_chapter_heading_name_text_view)).setText(chapter.getName());
                    ((TextView) v.findViewById(R.id.toc_chapter_heading_number_text_view)).setText(chapter.getChapterIndex());

                    Button chapterButton = v.findViewById(R.id.toc_chapter_heading_button);

                    chapterButton.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Snackbar snackbar = Snackbar.make(findViewById(R.id.topics_activity_layout), "", Snackbar.LENGTH_INDEFINITE);
                            buildCustomSnackbar(snackbar, "Updating " + chapter.getName()).show();

                            ContentUpdateService contentUpdateService = new ContentUpdateService();

                            contentUpdateService.updateContentForChapter(TopicsActivity.this, subjectName, chapter.getName(), new ContentUpdateService.UITask(){
                                @Override
                                public void afterTask(boolean foundToUpdate, int topicsUpdated) {
                                    if(topicsUpdated > 0) {
                                        Snackbar s = Snackbar.make(findViewById(R.id.topics_activity_layout), "", Snackbar.LENGTH_LONG);
                                        buildCustomSnackbar(s, topicsUpdated + " topics updated ").show();
                                    }
                                    else {
                                        Snackbar s = Snackbar.make(findViewById(R.id.topics_activity_layout), "", Snackbar.LENGTH_LONG);
                                        buildCustomSnackbar(s, "Chapter is up-to-date").show();
                                    }
                                }
                            });
                        }
                    });

                    (v.findViewById(R.id.toc_heading_path)).setBackgroundColor(t.primaryColor);

                    linearLayout.addView(v);

                    if(chapter.getRevisions() != null) {
                        LinearLayout l = new LinearLayout(getApplicationContext());
                        l.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        l.setOrientation(LinearLayout.VERTICAL);

                        LinkedHashSet<TopicBO> revisions = chapter.getRevisions();
                        LinkedHashSet<QptBO> qpts = chapter.getQpts();
                        ArrayList<TopicBO> revsFrNextQPT = new ArrayList<>();

                        Iterator<QptBO> qptBOIterator = qpts.iterator();

                        int topicIndex = 0;
                        for (final TopicBO revision : revisions) {
                            topicIndex++;

                            final Intent revisionIntent = new Intent(TopicsActivity.this, RevisionActivity.class);
                            revisionIntent.putExtra(IK_SUBJECT_NAME, subjectName);
                            revisionIntent.putExtra(IK_SCHOOL_YEAR, prefManager.getSchoolYear());
                            revisionIntent.putExtra(IK_CHAPTER_INDEX, i+1);
                            revisionIntent.putExtra(IK_TOPIC_INDEX, topicIndex);

                            final Intent practiceTestIntent = new Intent(TopicsActivity.this, PracticeTestActivity.class);
                            practiceTestIntent.putExtra(IK_SUBJECT_NAME, subjectName);
                            practiceTestIntent.putExtra(IK_SCHOOL_YEAR, prefManager.getSchoolYear());
                            practiceTestIntent.putExtra(IK_CHAPTER_INDEX, i+1);
                            practiceTestIntent.putExtra(IK_TOPIC_INDEX, topicIndex);

                            revsFrNextQPT.add(revision);

                            View view = getLayoutInflater().inflate(R.layout.toc_topic_and_content, null);
                            ((TextView) view.findViewById(R.id.toc_topic_and_content_topic_name_text_view)).setText(revision.getName());
                            ((TextView) view.findViewById(R.id.toc_topic_and_content_topic_content_text_view)).setText(revision.getDescription());

                            Button qrActionButton = view.findViewById(R.id.toc_topic_and_content_play_button);

                            if(revision.isLocked()) {
                                qrActionButton.setBackgroundResource(R.drawable.qr_action_button_icon_locked);
                                qrActionButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Snackbar.make(findViewById(R.id.topics_activity_layout), "Topic Locked. Wait for Teacher to Unlock.", Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                            else if(revision.isUnlocked()) {
                                qrActionButton.setBackgroundResource(R.drawable.qr_action_button_icon_unlocked);
                                qrActionButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Snackbar.make(findViewById(R.id.topics_activity_layout), "Topic Inactive. Finish Previous Topics to Activate.", Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                            else if(revision.isActive()) {
                                qrActionButton.setBackgroundResource(R.drawable.qr_action_button_icon_active);
                                qrActionButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(revisionIntent);
                                    }
                                });
                            }
                            else if(revision.isRevised()) {
                                qrActionButton.setBackgroundResource(R.drawable.qr_action_button_icon_revised);
                                qrActionButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which){
                                                    case DialogInterface.BUTTON_POSITIVE:
                                                        practiceTestIntent.putExtra(IK_QPT_INDEX, -1);
                                                        startActivity(practiceTestIntent);
                                                        break;
                                                    case DialogInterface.BUTTON_NEGATIVE:
                                                        startActivity(revisionIntent);
                                                        break;
                                                }
                                            }
                                        };

                                        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(TopicsActivity.this, R.style.myDialog));
                                        builder.setMessage("You have already revised this topic. You can either revise again or check your proficiency by practicing questions.")
                                                .setPositiveButton("Practice", dialogClickListener)
                                                .setNegativeButton("Revise", dialogClickListener)
                                                .show();
                                    }
                                });
                            }

                            l.addView(view);

                            if(revision.isDoesPrecedeQPT()) {
                                final QptBO qpt = qptBOIterator.next();

                                View x = getLayoutInflater().inflate(R.layout.toc_topic_and_content, null);
                                ((TextView) x.findViewById(R.id.toc_topic_and_content_topic_name_text_view)).setText("QPT " + chapter.getIndex() + "." + qpt.getQptIndex());

                                String revisionTopicNames = "";
                                for (TopicBO rvsn : revsFrNextQPT) {
                                    revisionTopicNames += rvsn.getName() + ", ";
                                }

                                ((TextView) x.findViewById(R.id.toc_topic_and_content_topic_content_text_view)).setText(revisionTopicNames);

                                Button qptActionButton = x.findViewById(R.id.toc_topic_and_content_play_button);

                                if(qpt.isLocked()) {
                                    qptActionButton.setBackgroundResource(R.drawable.qpt_action_button_icon_locked);
                                    qptActionButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Snackbar.make(findViewById(R.id.topics_activity_layout), "QPT Locked. Finish Previous Revisions to Unlock.", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                                }
                                else if (qpt.isUnlocked()) {
                                    qptActionButton.setBackgroundResource(R.drawable.qpt_action_button_icon_active);
                                    qptActionButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            practiceTestIntent.putExtra(IK_QPT_INDEX, qpt.getQptIndex());
                                            startActivity(practiceTestIntent);
                                        }
                                    });
                                }
                                else if (qpt.isAttempted()) {
                                    qptActionButton.setBackgroundResource(R.drawable.qpt_action_button_icon_attempted);
                                    qptActionButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Snackbar.make(findViewById(R.id.topics_activity_layout), "Already Attempted", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                                }
                                else if (qpt.isAced()) {
                                    qptActionButton.setBackgroundResource(R.drawable.qpt_action_button_icon_aced);
                                    qptActionButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Snackbar.make(findViewById(R.id.topics_activity_layout), "Already Attempted", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                                }

                                l.addView(x);

                                revsFrNextQPT.clear();
                            }
                        }

                        linearLayout.addView(l);
                    }
                }
            }
        });

        String subjectName = getIntent().getStringExtra(MainActivity.IK_SUBJECT_NAME);

        topicServices.displayTOC(getApplicationContext(), subjectName, prefManager.getSchoolYear());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private String capitalize(String line) {
        line = line.toLowerCase();
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    private void initialiseActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subjectName = extras.getString(MainActivity.IK_SUBJECT_NAME);
            subjectName = capitalize(subjectName);
        }

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(subjectName);
                    isShow = true;
                }
                else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        int headerImage = -1;

        switch (subjectName) {
            case "Physics" :
                headerImage = R.drawable.subject_header_physics;
                break;
            case "Chemistry" :
                headerImage = R.drawable.subject_header_chemistry;
                break;
            case "Biology" :
                headerImage = R.drawable.subject_header_biology;
                break;
            case "History" :
                headerImage = R.drawable.subject_header_history;
                break;
            case "Civics" :
                headerImage = R.drawable.subject_header_civics;
                break;
            case "Geography" :
                headerImage = R.drawable.subject_header_geography;
                break;
            case "Economics" :
                headerImage = R.drawable.subject_header_economics;
                break;
            case "Mathematics" :
                headerImage = R.drawable.card_image_mathematics;
                break;
            case "Maths" :
                headerImage = R.drawable.card_image_mathematics;
                break;
            case "English Literature" :
                headerImage = R.drawable.card_image_english;
                break;
            case "English Grammar" :
                headerImage = R.drawable.card_image_english;
                break;
            default:
                headerImage = R.drawable.subject_header_physics;
                break;
        }

        imageView.setImageDrawable(getResources().getDrawable(headerImage));
    }

    public void applyThemeColorsToElements() {
        final float scale = getResources().getDisplayMetrics().density;

        LayerDrawable lockedLayer = (LayerDrawable) ContextCompat.getDrawable(TopicsActivity.this, R.drawable.qr_action_button_icon_locked);
        GradientDrawable lockedShape = (GradientDrawable) lockedLayer.findDrawableByLayerId(R.id.qr_action_button_icon_locked_circle);
        lockedShape.setColor(t.primaryColor);

        LayerDrawable unlockedLayer = (LayerDrawable) ContextCompat.getDrawable(TopicsActivity.this, R.drawable.qr_action_button_icon_unlocked);
        GradientDrawable unlockedShape = (GradientDrawable) unlockedLayer.findDrawableByLayerId(R.id.qr_action_button_icon_unlocked_circle);
        unlockedShape.setColor(t.primaryColor);

        LayerDrawable activeLayer = (LayerDrawable) ContextCompat.getDrawable(TopicsActivity.this, R.drawable.qr_action_button_icon_active);
        GradientDrawable activeShape = (GradientDrawable) activeLayer.findDrawableByLayerId(R.id.qr_action_button_icon_active_circle);
        activeShape.setColor(t.primaryColor);

        LayerDrawable revisedLayer = (LayerDrawable) ContextCompat.getDrawable(TopicsActivity.this, R.drawable.qr_action_button_icon_revised);
        GradientDrawable revisedShape = (GradientDrawable) revisedLayer.findDrawableByLayerId(R.id.qr_action_button_icon_revised_circle);
        int strokeWidth = (int) (1 * scale + 0.5f);
        revisedShape.setStroke(strokeWidth, t.primaryColor);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            VectorDrawable revisedIcon = (VectorDrawable) revisedLayer.findDrawableByLayerId(R.id.qr_action_button_icon_revised_icon);
            revisedIcon.setTint(t.primaryColor);
        }

        LayerDrawable chapterLayer = (LayerDrawable) ContextCompat.getDrawable(TopicsActivity.this, R.drawable.toc_chapter_button_icon);
        GradientDrawable chapterShape = (GradientDrawable) chapterLayer.findDrawableByLayerId(R.id.toc_chapter_button_circle);
        chapterShape.setColor(t.primaryColor);

        LayerDrawable pathLayer = (LayerDrawable) ContextCompat.getDrawable(TopicsActivity.this, R.drawable.toc_vertical_path_dotted);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            VectorDrawable revisedIcon = (VectorDrawable) pathLayer.findDrawableByLayerId(R.id.toc_vertical_dotted_path_item);
            revisedIcon.setTint(t.primaryColor);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5 && keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }

    public void onContentDownloadClick(View v) {
        Snackbar s = Snackbar.make(findViewById(R.id.topics_activity_layout), "", Snackbar.LENGTH_INDEFINITE);
        buildCustomSnackbar(s, "Downloading Content").show();
        final Snackbar snackbar = buildCustomSnackbar(Snackbar.make(findViewById(R.id.topics_activity_layout), "", Snackbar.LENGTH_SHORT), "Downloaded");

        DataDownloadService dataDownloadService = new DataDownloadService(new DataDownloadService.UITask() {
            @Override
            public void afterTask(SchoolClass schoolClass) {
                snackbar.show();
            }
        });

        dataDownloadService.downloadContentForSubject(getApplicationContext(), subjectName);
    }

    public Snackbar buildCustomSnackbar(Snackbar snackbar, String message) {
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setBackgroundColor(Color.parseColor("#00000000"));
        TextView textView = layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View snackView = getLayoutInflater().inflate(R.layout.custom_snackbar, null);

        TextView textViewTop = snackView.findViewById(R.id.snackbar_textview);
        textViewTop.setText(message);
        textViewTop.setTextColor(Color.WHITE);

        layout.setPadding(0,0,0,0);
        layout.addView(snackView, 0);
        return snackbar;
    }
}