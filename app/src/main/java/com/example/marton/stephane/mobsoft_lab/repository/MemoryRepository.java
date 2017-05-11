package com.example.marton.stephane.mobsoft_lab.repository;

import android.content.Context;

import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.models.Profile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marton on 2017.04.10..
 */

public class MemoryRepository implements Repository {
    private static final long MINUTE = 60 * 1000;

    public static List<Comment> comments;
    public static Profile profile;

    @Override
    public void open(Context context) {
        comments = new ArrayList<>();
        profile = new Profile();
        profile.setEmail("somebody@somewhere.now");
        profile.setId(1);
        profile.setUsername("Me");
    }

    @Override
    public void close() {

    }

    @Override
    public List<Comment> getComments(String animeId) {
        List<Comment> animeComments = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++) {
            Comment currentComment = comments.get(i);
            if (currentComment.getAnimeId().equals(animeId)) {
                animeComments.add(currentComment);
            }
        }

        return animeComments;
    }

    @Override
    public void saveComment(Comment comment) {
        comments.add(comment);

    }

    @Override
    public void updateComments(List<Comment> commentsUpdate) {
        for (int i = 0; i < comments.size(); i++) {
            Comment favourite = comments.get(i);
            for (Comment comment : commentsUpdate) {
                if (comment.getCommentId().equals(favourite.getCommentId())) {
                    comments.set(i, comment);
                }
            }
        }
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public Profile Login() {
        return profile;
    }

    @Override
    public boolean isInDB(Comment comment) {
        return comments.contains(comment);
    }
}
