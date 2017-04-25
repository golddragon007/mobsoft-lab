package com.example.marton.stephane.mobsoft_lab.interactor.login.events;

import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.models.Profile;

import java.util.List;

public class LoginEvent {
    private int code;
    private Profile profile;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public LoginEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

//</editor-fold>
}
