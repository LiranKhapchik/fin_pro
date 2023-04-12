package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class settings extends AppCompatActivity {
    public static final String preferences = "preferences";
    public static final String custom = "custom";

    public static int timeMs = 60000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void goToHomePage(View view) {
        Intent intent = new Intent(settings.this, MainActivity.class);
        startActivity(intent);
    }

    public void convertTime(View view) {
        EditText timeText = findViewById(R.id.time_text);
        String timeMsStr = timeText.getText().toString();
        try {
            int timeMs = Integer.parseInt(timeMsStr);
            Toast.makeText(this, "New time was set: ~" + timeMs/1000 +"."+ timeMs%10 +" sec.", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }



}