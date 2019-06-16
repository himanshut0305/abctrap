package com.qedplus.particles.extras;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface QuestionLevel {
    int VERY_EASY = 1;
    int EASY = 2;
    int MEDIUM = 3;
    int HARD = 4;
    int VERY_HARD = 5;

    @IntDef({VERY_EASY, EASY, MEDIUM, HARD, VERY_HARD})
    @Retention(RetentionPolicy.SOURCE)
    @interface Level {}
}
