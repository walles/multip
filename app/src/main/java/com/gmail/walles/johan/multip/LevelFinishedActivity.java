package com.gmail.walles.johan.multip;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NonNls;

import java.util.Locale;

public class LevelFinishedActivity extends AppCompatActivity {

    @NonNls
    private static final String EXTRA_LEVEL_NUMBER = "levelNumber";
    private static final String EXTRA_CORRECT_COUNT = "correctCount";

    public static void start(Context context, int finishedLevel, int correctCount) {
        Intent intent = new Intent(context, LevelFinishedActivity.class);
        intent.putExtra(EXTRA_LEVEL_NUMBER, finishedLevel);
        intent.putExtra(EXTRA_CORRECT_COUNT, correctCount);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_finished);

        int levelNumber = getIntent().getIntExtra(EXTRA_LEVEL_NUMBER, -1);
        int correctCount = getIntent().getIntExtra(EXTRA_CORRECT_COUNT, -1);

        MediaPlayer wow;
        if (correctCount == 10) {
            wow = MediaPlayer.create(this, R.raw.happykids);
        } else {
            wow = MediaPlayer.create(this, R.raw.lightapplause);
        }
        wow.start();

        TextView textView = findViewById(R.id.levelFinishedText);
        textView.setText(String.format(Locale.ENGLISH, "Level %d finished, you got %d answers right!",
                levelNumber,
                correctCount));

        findViewById(R.id.levelFinishedContinue).setOnClickListener(
                v -> {
                    Intent intent = new Intent(LevelFinishedActivity.this, LevelLaunchingActivity.class);
                    startActivity(intent);
                });
    }
}
