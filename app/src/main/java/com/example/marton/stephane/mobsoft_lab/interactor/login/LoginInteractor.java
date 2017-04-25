package com.example.marton.stephane.mobsoft_lab.interactor.login;

import com.example.marton.stephane.mobsoft_lab.MobSoftApplication;
import com.example.marton.stephane.mobsoft_lab.interactor.login.events.LoginEvent;
import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.models.Profile;
import com.example.marton.stephane.mobsoft_lab.network.User.UserApi;
import com.example.marton.stephane.mobsoft_lab.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;


public class LoginInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    @Inject
    UserApi userApi;


    public LoginInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void Login(String username, String password) {
        LoginEvent event = new LoginEvent();
        try {
            Profile profile = userApi.loginPost(username, password).execute().body();
            event.setProfile(profile);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
