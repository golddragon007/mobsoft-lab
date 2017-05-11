package com.example.marton.stephane.mobsoft_lab.interactor.comment.events;

import com.example.marton.stephane.mobsoft_lab.models.Comment;

public class SaveCommentsEvent {
    private int code;
    private Comment todos;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public SaveCommentsEvent() {
    }

    public SaveCommentsEvent(int code, Comment todos, Throwable throwable) {
        this.code = code;
        this.todos = todos;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Comment getComment() {
        return todos;
    }

    public void setComment(Comment animeListItem) {
        this.todos = animeListItem;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    //</editor-fold>
}
