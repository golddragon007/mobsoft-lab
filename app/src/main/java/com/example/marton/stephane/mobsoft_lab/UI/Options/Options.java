package com.example.marton.stephane.mobsoft_lab.UI.Options;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marton.stephane.mobsoft_lab.MobSoftApplication;
import com.example.marton.stephane.mobsoft_lab.R;
import com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch.AnimeWatchPresenter;

import javax.inject.Inject;

public class Options extends AppCompatActivity implements OptionsScreen {

    @Inject
    OptionsPresenter optionsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        optionsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        optionsPresenter.detachScreen();
    }
}
