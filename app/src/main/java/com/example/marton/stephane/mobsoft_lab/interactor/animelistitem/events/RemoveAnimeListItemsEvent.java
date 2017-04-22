package com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.events;

import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;

public class RemoveAnimeListItemsEvent {
    private int code;
    private AnimeListItem animeListItems;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public RemoveAnimeListItemsEvent() {
    }

    public RemoveAnimeListItemsEvent(int code, AnimeListItem animeListItems, Throwable throwable) {
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

    public AnimeListItem getAnimeListItems() {
        return animeListItems;
    }

    public void setAnimeListItems(AnimeListItem animeListItems) {
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
