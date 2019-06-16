package com.qedplus.particles;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.qedplus.particles.extras.Utility;

import java.util.ArrayList;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity {
    Utility.Theme theme;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        theme = Utility.getTheme(getApplicationContext());
        setTheme(theme.theme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_analysis);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subject Analysis");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        AppBarLayout appBarLayout = findViewById(R.id.analysis_app_bar_layout);
        appBarLayout.setBackgroundResource(theme.cardGradient);

        LineChart chart = findViewById(R.id.chart_line_analysis);

        List<Entry> arrayList1 = new ArrayList<>();

        arrayList1.add(new Entry(1, 2));
        arrayList1.add(new Entry(2, 4));
        arrayList1.add(new Entry(3, 5));
        arrayList1.add(new Entry(4, 4));
        arrayList1.add(new Entry(5, 6));
        arrayList1.add(new Entry(6, 9));
        arrayList1.add(new Entry(7, 8));
        arrayList1.add(new Entry(8, 5));
        arrayList1.add(new Entry(9, 8));
        arrayList1.add(new Entry(10, 6));

        List<Entry> arrayList2 = new ArrayList<>();

        arrayList2.add(new Entry(1, 8));
        arrayList2.add(new Entry(2, 7));
        arrayList2.add(new Entry(3, 5));
        arrayList2.add(new Entry(4, 6));
        arrayList2.add(new Entry(5, 4));
        arrayList2.add(new Entry(6, 6));
        arrayList2.add(new Entry(7, 6));
        arrayList2.add(new Entry(8, 4));
        arrayList2.add(new Entry(9, 2));
        arrayList2.add(new Entry(10, 3));

        LineDataSet dataSet1 = new LineDataSet(arrayList1, "Marks per Test");
        List<Integer> colorList1 = new ArrayList<>();
        colorList1.add(getResources().getColor(R.color.white));

        dataSet1.setColors(colorList1);
        dataSet1.setDrawValues(false);
        dataSet1.setDrawCircleHole(false);
        dataSet1.setDrawCircles(false);
        dataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet1.setLineWidth(3f);

        LineDataSet dataSet2 = new LineDataSet(arrayList2, "Marks per Test");
        List<Integer> colorList2 = new ArrayList<>();
        colorList2.add(getResources().getColor(R.color.black));

        dataSet2.setColors(colorList2);
        dataSet2.setDrawValues(false);
        dataSet2.setDrawCircleHole(false);
        dataSet2.setDrawCircles(false);
        dataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet2.setLineWidth(3f);

        LineData lineData = new LineData(dataSet1, dataSet2);

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

        Drawable d = getResources().getDrawable(R.drawable.progress_bar_red);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d.setTint(theme.primaryColor);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}