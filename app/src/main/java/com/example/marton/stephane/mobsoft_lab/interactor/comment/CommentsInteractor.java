package com.example.marton.stephane.mobsoft_lab.interactor.comment;

import com.example.marton.stephane.mobsoft_lab.MobSoftApplication;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.events.GetCommentsEvent;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.events.RemoveCommentsEvent;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.events.SaveCommentsEvent;
import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;


public class CommentsInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;



    public CommentsInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void getComments(String animeId) {
        GetCommentsEvent event = new GetCommentsEvent();
        try {
            List<Comment> animeListItems = repository.getComments(animeId);
            event.setComments(animeListItems);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveComments(Comment animeListItems) {

        SaveCommentsEvent event = new SaveCommentsEvent();
        event.setComment(animeListItems);
        try {
            repository.saveComment(animeListItems);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void updateComments(List<Comment> todo) {
        try {
            repository.updateComments(todo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeComments(Comment animeListItems) {
        RemoveCommentsEvent event = new RemoveCommentsEvent();
        event.setComments(animeListItems);
        try {
            repository.removeComment(animeListItems);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
