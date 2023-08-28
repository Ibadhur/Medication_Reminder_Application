package com.ibadhur.medicinetime.healthcarenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ibadhur.medicinetime.R;
import com.ibadhur.medicinetime.authentication.LoginActivity;
import com.ibadhur.medicinetime.authentication.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class newsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);


    }

}