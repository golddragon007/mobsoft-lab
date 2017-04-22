package com.example.marton.stephane.mobsoft_lab.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Marton on 2017.04.10..
 */

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    public Repository provideRepository() {
        return new SugarOrmRepository();
    }
}
