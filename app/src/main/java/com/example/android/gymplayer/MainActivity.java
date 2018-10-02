package com.example.android.gymplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the view that shows the intensity playlist and set a click listener on it (same for the other playlists)
        findViewById(R.id.frame_intensity).setOnClickListener(this);
        findViewById(R.id.frame_volume).setOnClickListener(this);
        findViewById(R.id.frame_cardio).setOnClickListener(this);
        findViewById(R.id.frame_stretching).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.frame_intensity: {
                startActivity(new Intent(MainActivity.this, IntensityActivity.class));
                break;
            }
            case R.id.frame_volume: {
                startActivity(new Intent(MainActivity.this, VolumeActivity.class));
                break;
            }
            case R.id.frame_cardio: {
                startActivity(new Intent(MainActivity.this, CardioActivity.class));
                break;
            }
            case R.id.frame_stretching: {
                startActivity(new Intent(MainActivity.this, StretchingActivity.class));
                break;
            }
        }
    }
}
