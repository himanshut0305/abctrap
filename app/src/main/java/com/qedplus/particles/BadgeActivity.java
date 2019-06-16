package com.qedplus.particles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.model.BadgeBO;
import com.qedplus.particles.services.BadgeServices;

import java.util.ArrayList;

public class BadgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utility.Theme t = Utility.getTheme(getApplicationContext());

        setTheme(t.blackTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_badge);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final LinearLayout badgeActivityLayout = findViewById(R.id.badge_activity_layout);
        final ConstraintLayout badgeShareLayout = findViewById(R.id.badge_layout);

        BadgeServices badgeServices = new BadgeServices(new BadgeServices.UITask() {
            @Override
            public void task(ArrayList<BadgeBO> badges) {

                for(final BadgeBO badge : badges) {
                    Log.e("Badge Activity", badge.toString());

                    View badgeListItemLayout = inflater.inflate(R.layout.badge_list_item, badgeActivityLayout, false);

                    ImageView badgeImageView = badgeListItemLayout.findViewById(R.id.badge_image_view);

                    TextView badgeHeading = badgeListItemLayout.findViewById(R.id.badge_heading);
                    TextView badgeDescription = badgeListItemLayout.findViewById(R.id.badge_description);
                    TextView badgeStatus = badgeListItemLayout.findViewById(R.id.badge_status);

                    badgeHeading.setText(badge.getName());
                    badgeDescription.setText(badge.getDescription());

                    if(badge.isSeen()) {
                        badgeStatus.setText("Aquired");
                        badgeStatus.setTextColor(getResources().getColor(R.color.blue));
                        badgeImageView.setImageResource(getResources().getIdentifier(badge.getUrl(), "drawable", getPackageName()));

                        badgeListItemLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ImageView badgeShareImage = badgeShareLayout.findViewById(R.id.share_badge_image_view);
                                TextView badgeDescription = badgeShareLayout.findViewById(R.id.share_badge_description);

                                badgeShareImage.setImageResource(getResources().getIdentifier(badge.getUrl(), "drawable", getPackageName()));
                                badgeDescription.setText(badge.getDescription());
                                badgeShareLayout.setVisibility(View.VISIBLE);

                                ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
                                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                        float val = (Float) valueAnimator.getAnimatedValue();
                                        badgeShareLayout.setAlpha(val);
                                    }
                                });

                                anim.setDuration(300);
                                anim.start();
                            }
                        });
                    }
                    else {
                        badgeStatus.setText("Locked");
                        badgeImageView.setImageResource(getResources().getIdentifier(badge.getUrl() + "_blurry", "drawable", getPackageName()));
                        badgeListItemLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "You haven't acquired this badge yet", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    badgeActivityLayout.addView(badgeListItemLayout);
                }
            }
        });

        badgeServices.getAllBadges(getApplicationContext());
    }

    public void onBadgeLayoutClick(final View v) {
        ValueAnimator anim = ValueAnimator.ofFloat(1f, 0f);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float val = (Float) valueAnimator.getAnimatedValue();
                v.setAlpha(val);
            }
        });

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.GONE);
            }
        });

        anim.setDuration(300);
        anim.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}