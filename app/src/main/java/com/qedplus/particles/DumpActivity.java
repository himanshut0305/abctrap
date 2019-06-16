package com.qedplus.particles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import katex.hourglass.in.mathlib.MathView;

public class DumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dump);

        ((MathView) findViewById(R.id.mathview)).setDisplayText("This is a <b> Polynomial </b></br> \\( \\Rightarrow p(2)=3(2)2 - 3(2)+5 \\) ");
    }
}
