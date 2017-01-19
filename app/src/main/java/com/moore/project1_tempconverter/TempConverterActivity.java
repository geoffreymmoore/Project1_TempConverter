package com.moore.project1_tempconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TempConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.weather_few_clouds);
    }
}
