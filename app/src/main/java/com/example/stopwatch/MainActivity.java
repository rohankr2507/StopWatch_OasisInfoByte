package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView time;
    private int sec = 0;
    private boolean running;
    private boolean paused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);

        Toast.makeText(this, "App Started", Toast.LENGTH_SHORT).show();

        if (savedInstanceState != null) {
            sec = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            paused = savedInstanceState.getBoolean("paused");
        }
        running_Timer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", sec);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", paused);
    }

    @Override
    protected void onPause() {
        super.onPause();
        paused = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (paused) {
            running = true;
        }
    }

    public void startRunning(View view) {
        running = true;
        Toast.makeText(this, "StopWatch Started", Toast.LENGTH_SHORT).show();
    }

    public void holdRunning(View view) {
        running = false;
        Toast.makeText(this, "StopWatch Hold", Toast.LENGTH_SHORT).show();
    }

    public void stopRunning(View view) {
        running = false;
        sec = 0;
        Toast.makeText(this, "StopWatch Hold & Restarted", Toast.LENGTH_SHORT).show();
    }

    private void running_Timer() {
        final Handler handle = new Handler();
        handle.post(new Runnable() {
            @Override
            public void run() {
                int hrs = sec / 3600;
                int mins = (sec % 3600) / 60;
                int secs = sec % 60;
                String time_t = String.format(Locale.getDefault(), "    %02d:%02d:%02d   ", hrs, mins, secs);
                time.setText(time_t);
                if (running) {
                    sec++;
                }
                handle.postDelayed(this, 1000);
            }
        });
    }
}