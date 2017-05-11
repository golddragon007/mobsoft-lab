package com.example.marton.stephane.mobsoft_lab.repository;

import android.content.Context;

import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.models.Profile;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marton on 2017.04.10..
 */

public class SugarOrmRepository implements Repository {

    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Comment> getComments(String animeId) {
        List<Comment> comments = new ArrayList<>();
        List<Comment> allComment = SugarRecord.listAll(Comment.class);

        for (int i = 0; i < allComment.size(); i++) {
            Comment comment = allComment.get(i);
            if (comment.getAnimeId().equals(animeId)) {
                comments.add(comment);
            }
        }

        return comments;
    }

    @Override
    public void saveComment(Comment comment) {
        SugarRecord.saveInTx(comment);
    }

    @Override
    public void updateComments(List<Comment> comments) {
        List<Comment> favourites = SugarRecord.listAll(Comment.class);
        List<Comment> toUpdate = new ArrayList<>(favourites.size());
        for (Comment favourite : favourites) {
            for (Comment todo : comments) {
                if (todo.getCommentId().equals(favourite.getCommentId())) {
                    toUpdate.add(todo);
                }
            }
        }
        SugarRecord.saveInTx(toUpdate);
    }

    @Override
    public void removeComment(Comment comment) {
        SugarRecord.deleteInTx(comment);
    }

    @Override
    public Profile Login() {
        return null;
    }

    @Override
    public boolean isInDB(Comment comment) {
        return SugarRecord.findById(Comment.class, Integer.parseInt(comment.getCommentId())) != null;
    }
}
