package com.example.oasistask5_aman_stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView3;
    private Button button;
    private Button button2;
    private Button button3;

    private int sec = 0;
    private boolean isRunning;
    private boolean wasRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        textView3=findViewById(R.id.textView3);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        if (savedInstanceState != null)
        {
            sec = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        startTimer();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", sec);
        savedInstanceState.putBoolean("running", isRunning);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            isRunning = true;
        }
    }

    public void Start(View view)
    {
        isRunning = true;
    }


    public void Hold(View view)
    {
        isRunning = false;
    }

    public void Stop(View view)
    {
        isRunning = false;
        sec = 0;
    }

    private void startTimer()
    {

        final Handler hd = new Handler();

        hd.post(new Runnable() {
            @Override

            public void run()
            {
                int hours_var = sec / 3600;
                int minutes_var = (sec % 3600) / 60;
                int secs_var = sec % 60;

                String time_value = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours_var, minutes_var, secs_var);

                textView.setText(time_value);

                if (isRunning)
                {
                    sec++;
                }

                hd.postDelayed(this, 1000);
            }
        });


    }
}