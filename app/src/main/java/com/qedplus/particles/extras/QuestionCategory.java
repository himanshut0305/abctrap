package com.qedplus.particles.extras;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface QuestionCategory {
    String CONCEPTUAL = "CONCEPTUAL";
    String NUMERICAL = "NUMERICAL";
    String MEMORY_BASED = "MEMORY_BASED";

    @StringDef({CONCEPTUAL, NUMERICAL, MEMORY_BASED})
    @Retention(RetentionPolicy.SOURCE)
    @interface Category {}
}
