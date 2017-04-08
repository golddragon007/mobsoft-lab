package com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marton.stephane.mobsoft_lab.MobSoftApplication;
import com.example.marton.stephane.mobsoft_lab.R;

import javax.inject.Inject;

public class AnimeWatch extends AppCompatActivity implements AnimeWatchScreen {

    @Inject
    AnimeWatchPresenter animeWatchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_watch);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        animeWatchPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        animeWatchPresenter.detachScreen();
    }
}
