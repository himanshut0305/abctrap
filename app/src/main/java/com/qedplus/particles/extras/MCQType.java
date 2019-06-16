package com.qedplus.particles.extras;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface MCQType {
    String SINGLE_ANSWER = "SINGLE_ANSWER";
    String MULTIPLE_ANSWER = "MULTIPLE_ANSWER";
    String TRUE_FALSE = "TRUE_FALSE";

    @StringDef({SINGLE_ANSWER, MULTIPLE_ANSWER, TRUE_FALSE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type { }
}
