package com.moore.project1_tempconverter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class TempConverterActivity extends AppCompatActivity
implements TextView.OnEditorActionListener, View.OnClickListener{

    //define variables for the widgets
    private EditText fahrenheitET;
    private TextView celciusTV;
    private Button resetButton;

    //define the SharedPreferences object
    private SharedPreferences savedValues;

    //define instance variable that should be saved
    private String fahrenheitString = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.weather_few_clouds);

        //get references to widgets
        fahrenheitET = (EditText) findViewById(R.id.fahrenheitET);
        celciusTV = (TextView) findViewById(R.id.celciusTV);
        resetButton = (Button) findViewById(R.id.resetButton);

        //set the listeners
        fahrenheitET.setOnEditorActionListener(this);
        resetButton.setOnClickListener(this);

        //get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues",MODE_PRIVATE);
    }

    public void calculateAndDisplay(){

        //get Fahrenheit
        fahrenheitString = fahrenheitET.getText().toString();
        float fahrenheit;
        float celciusTemp;
        if(fahrenheitString.equals("")){
            fahrenheit = 0;

        } else {
                    fahrenheit = Float.parseFloat(fahrenheitString);
        }

        if(fahrenheit==0){
            celciusTemp = 0;
        }else {
            celciusTemp = (fahrenheit - 32) * 5 / 9;
        }

        if(celciusTemp < 0){
            celciusTV.setTextColor(Color.rgb(0,0,255));
        }else if (celciusTemp > 0){
            celciusTV.setTextColor(Color.rgb(255,0,0));
        }else{
            celciusTV.setTextColor(Color.rgb(0,0,0));
        }


        //display temperature on layout
        NumberFormat celcius = NumberFormat.getNumberInstance();
        celciusTV.setText(celcius.format(celciusTemp));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.resetButton){
            fahrenheitET.setText("");
            calculateAndDisplay();
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
            calculateAndDisplay();
        }
        return false;
    }

    @Override
    protected void onPause() {
        //save the instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("fahrenheitString",fahrenheitString);
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //get the instance variables
        fahrenheitString = savedValues.getString("fahrenheitString","");

        //set the fahrenheit
        fahrenheitET.setText(fahrenheitString);;

        //calculate and display
        calculateAndDisplay();
    }
}
