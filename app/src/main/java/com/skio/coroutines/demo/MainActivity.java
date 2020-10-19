package com.skio.coroutines.demo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;

import com.skio.coroutines.R;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    View rootLayout;

    private int revealX;
    private int revealY;

    private RevealAnimation mRevealAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_main);

        final Intent intent = getIntent();

        rootLayout = findViewById(R.id.root_layout);

        mRevealAnimation = new RevealAnimation(rootLayout, intent, this);

        onAnimateLayout(savedInstanceState, intent);
    }

    @Override
    public void onBackPressed() {
        mRevealAnimation.unRevealActivity();
    }

    private void onAnimateLayout(Bundle savedInstanceState, Intent intent) {
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mRevealAnimation.revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }
    }

}
