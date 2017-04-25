package com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.events;

import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;

public class SaveAnimeListItemsEvent {
    private int code;
    private AnimeListItem todos;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public SaveAnimeListItemsEvent() {
    }

    public SaveAnimeListItemsEvent(int code, AnimeListItem todos, Throwable throwable) {
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

    public AnimeListItem getAnimeListItem() {
        return todos;
    }

    public void setAnimeListItem(AnimeListItem animeListItem) {
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
