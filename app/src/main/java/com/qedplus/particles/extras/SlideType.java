package com.qedplus.particles.extras;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface SlideType {
    String REVISION_POINT = "REVISION_POINT";
    String REVISION_QUESTION = "REVISION_QUESTION";

    @StringDef({REVISION_POINT, REVISION_QUESTION})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type { }
}
