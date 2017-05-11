package com.example.marton.stephane.mobsoft_lab.repository;

import android.content.Context;

import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.models.Profile;

import java.util.List;

/**
 * Created by Marton on 2017.04.10..
 */

public interface Repository {

    void open(Context context);

    void close();

    List<Comment> getComments(String animeId);

    void saveComment(Comment comment);

    void updateComments(List<Comment> commentsUpdate);

    void removeComment(Comment comment);

    Profile Login();

    boolean isInDB(Comment comment);
}
