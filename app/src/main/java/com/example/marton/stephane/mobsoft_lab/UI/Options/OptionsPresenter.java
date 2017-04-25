package com.example.marton.stephane.mobsoft_lab.UI.Options;

import android.util.Log;

import com.example.marton.stephane.mobsoft_lab.UI.Presenter;
import com.example.marton.stephane.mobsoft_lab.interactor.login.LoginInteractor;
import com.example.marton.stephane.mobsoft_lab.interactor.login.events.LoginEvent;
import com.example.marton.stephane.mobsoft_lab.models.Profile;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Marton on 2017.03.27..
 */

public class OptionsPresenter extends Presenter<OptionsScreen> {

    @Inject
    LoginInteractor loginInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;


    public OptionsPresenter() {
    }

    @Override
    public void attachScreen(OptionsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void Login(final String username, final String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loginInteractor.Login(username, password);
            }
        });
    }

    public void onEventMainThread(LoginEvent event) {
        Log.d("test","test");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {
                Profile profile = event.getProfile();
                if (profile != null) {
                    screen.showMessage("Ok!");
                }
                else {
                    screen.showMessage("Wrong user/pass!");
                }
            }
        }
    }

}
