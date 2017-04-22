package com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.events;

import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.models.Todo;

import java.util.List;

public class GetAnimeListItemsEvent {
    private int code;
    private List<AnimeListItem> animeListItems;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public GetAnimeListItemsEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AnimeListItem> getAnimeListItems() {
        return animeListItems;
    }

    public void setAnimeListItems(List<AnimeListItem> animeListItems) {
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
