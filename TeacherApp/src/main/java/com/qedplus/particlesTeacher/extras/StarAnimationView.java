package com.qedplus.particlesTeacher.extras;

import android.animation.TimeAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.qedplus.particlesTeacher.R;

import java.util.Random;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class StarAnimationView extends View {

    private static class Star {
        private Float x;
        private Float y;
        private float deltaX;
        private float deltaY;
        private float scale;
        private float speed;
    }

    int count = 0;

    private static final int BASE_SPEED_DP_PER_S = 100;
    private static final int COUNT = 80;
    private static final int SEED = 1000;

    private static final float SCALE_MIN_PART = 0.3f;

    private final Star[] mStars = new Star[COUNT];
    private final Random mRnd = new Random(SEED);

    private TimeAnimator mTimeAnimator;
    private Drawable mDrawable;

    private float mBaseSpeed;
    private float mBaseSize;

    public StarAnimationView(Context context) {
        super(context);
        init();
    }

    public StarAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StarAnimationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_dot);
        mBaseSize = Math.max(mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight()) / 2f;
        mBaseSpeed = BASE_SPEED_DP_PER_S * getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        for (int i = 0; i < mStars.length; i++) {
            final Star star = new Star();
            initializeStar(star, width, height);
            mStars[i] = star;
        }
    }

    Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        final int viewHeight = getHeight();
        final int viewWidth = getWidth();

        for (final Star star : mStars) {
            final float starSize = star.scale * mBaseSize;
            if (star.y + starSize < 0 || star.y - starSize > viewHeight) {
                continue;
            }

            final int save = canvas.save();
            canvas.translate(star.x, star.y);
            final float progress = (star.y + starSize) / viewHeight;
            canvas.rotate(360 * progress);

            final int size = Math.round(starSize);
            mDrawable.setBounds(-size, -size, size, size);
            mDrawable.draw(canvas);

            canvas.restoreToCount(save);
            for(Star friend : mStars) {
                Double distance = Math.sqrt(Math.pow(star.x - friend.x, 2) + Math.pow(star.y - friend.y, 2));
                if(distance < 200) {
                    int alph = (int)(200d - distance);
                    paint.setColor(Color.argb(alph, 255, 255, 255));
                    canvas.drawLine(star.x, star.y, friend.x, friend.y, paint);
                }
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mTimeAnimator = new TimeAnimator();
        mTimeAnimator.setTimeListener(new TimeAnimator.TimeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                if (!isLaidOut()) {
                    return;
                }

                updateState(deltaTime);
                invalidate();
            }
        });

        mTimeAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTimeAnimator.cancel();
        mTimeAnimator.setTimeListener(null);
        mTimeAnimator.removeAllListeners();
        mTimeAnimator = null;
    }

    private void updateState(float deltaMs) {
        final float deltaSeconds = deltaMs / 1000f;
        final int viewWidth = getWidth();
        final int viewHeight = getHeight();

        for (final Star star : mStars) {
            star.x = star.x + (star.speed * deltaSeconds * star.deltaX / 100);
            star.y = star.y - (star.speed * deltaSeconds * star.deltaY / 100);

            final float size = star.scale * mBaseSize;
            if (star.y < -100 || star.y > viewHeight + 100 || star.x < -100 || star.x > viewWidth + 100) {
                initializeStar(star, viewWidth, viewHeight);
            }
        }
    }

    int a1, a2, a3, a4 = 0;

    int timeCount = 0;
    private void initializeStar(Star star, int viewWidth, int viewHeight) {
        timeCount++;

        star.scale = SCALE_MIN_PART;

        if(count < COUNT) {
            count++;
            star.x = mRnd.nextFloat() * viewWidth;
            star.y = mRnd.nextFloat() * viewHeight;
        }
        else {
            Integer temp = mRnd.nextInt(4);

            switch (temp) {
                case 0:
                    star.x = -100f;
                    star.y = mRnd.nextFloat() * viewHeight;
                    a1++;
                    break;
                case 1:
                    star.x = viewWidth + 100f;
                    star.y = mRnd.nextFloat() * viewHeight;
                    a2++;
                    break;
                case 2:
                    star.x = mRnd.nextFloat() * viewWidth;
                    star.y = -100f;
                    a3++;
                    break;
                case 3:
                    star.x = mRnd.nextFloat() * viewWidth;
                    star.y = viewHeight + 100f;
                    a4++;
                    break;
            }
        }

        star.deltaX = mRnd.nextInt(200) - 100;
        star.deltaY = mRnd.nextInt(200) - 100;

        star.speed = mBaseSpeed * mRnd.nextFloat() / star.scale / 3;
    }
}