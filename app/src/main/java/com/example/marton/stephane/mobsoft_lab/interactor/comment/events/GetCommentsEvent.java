package com.example.marton.stephane.mobsoft_lab.interactor.comment.events;

import com.example.marton.stephane.mobsoft_lab.models.Comment;

import java.util.List;

public class GetCommentsEvent {
    private int code;
    private List<Comment> animeListItems;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public GetCommentsEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Comment> getComments() {
        return animeListItems;
    }

    public void setComments(List<Comment> animeListItems) {
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
