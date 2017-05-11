package com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch;

import com.example.marton.stephane.mobsoft_lab.models.Comment;

import java.util.ArrayList;

/**
 * Created by Marton on 2017.03.27..
 */

public interface AnimeWatchScreen {
    void showMessage(String text);
    void onCacheSuccess(Object object);
    void onCacheImageReady(String id);
    void receiveComments(ArrayList<Comment> comments);
}
