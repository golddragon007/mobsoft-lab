package com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch;

/**
 * Created by Marton on 2017.03.27..
 */

public interface AnimeWatchScreen {
    void showMessage(String text);
    void onCacheSuccess(Object object);
    void onCacheImageReady(String id);
}
