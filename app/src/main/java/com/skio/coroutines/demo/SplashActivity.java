package com.skio.coroutines.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.skio.coroutines.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presentActivity(findViewById(R.id.progressbar));
            }
        }, 500L);
    }

    public void presentActivity(View view) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (width / 2);
        int revealY = (int) (height / 2);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        startActivity(intent,options.toBundle());

    }

}
