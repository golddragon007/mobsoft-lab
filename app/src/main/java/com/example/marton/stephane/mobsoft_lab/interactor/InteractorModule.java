package com.example.marton.stephane.mobsoft_lab.interactor;

import dagger.Module;
import dagger.Provides;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.CommentsInteractor;
import com.example.marton.stephane.mobsoft_lab.interactor.login.LoginInteractor;

@Module
public class InteractorModule {


    @Provides
    public CommentsInteractor provideAnimeListItems() {
        return new CommentsInteractor();
    }

    @Provides
    public LoginInteractor provideLogin() {
        return new LoginInteractor();
    }

}
