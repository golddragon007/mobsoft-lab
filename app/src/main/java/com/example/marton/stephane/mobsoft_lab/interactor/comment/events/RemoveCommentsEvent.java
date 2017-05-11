package com.example.marton.stephane.mobsoft_lab.interactor.comment.events;

import com.example.marton.stephane.mobsoft_lab.models.Comment;

public class RemoveCommentsEvent {
    private int code;
    private Comment animeListItems;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public RemoveCommentsEvent() {
    }

    public RemoveCommentsEvent(int code, Comment animeListItems, Throwable throwable) {
        this.code = code;
        this.animeListItems = animeListItems;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Comment getComments() {
        return animeListItems;
    }

    public void setComments(Comment animeListItems) {
        this.animeListItems = animeListItems;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    //</editor-fold>
}
