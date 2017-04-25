package com.example.marton.stephane.mobsoft_lab.interactor.animelistitem;

import com.example.marton.stephane.mobsoft_lab.MobSoftApplication;
import com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.events.GetAnimeListItemsEvent;
import com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.events.RemoveAnimeListItemsEvent;
import com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.events.SaveAnimeListItemsEvent;
import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;


public class AnimeListItemsInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;



    public AnimeListItemsInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void getAnimeListItems() {
        GetAnimeListItemsEvent event = new GetAnimeListItemsEvent();
        try {
            List<AnimeListItem> animeListItems = repository.getAnimeListItems();
            event.setAnimeListItems(animeListItems);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveAnimeListItems(AnimeListItem animeListItems) {

        SaveAnimeListItemsEvent event = new SaveAnimeListItemsEvent();
        event.setAnimeListItem(animeListItems);
        try {
            repository.saveAnimeListItem(animeListItems);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void updateAnimeListItems(List<AnimeListItem> todo) {
        try {
            repository.updateAnimeListItems(todo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeAnimeListItems(AnimeListItem animeListItems) {
        RemoveAnimeListItemsEvent event = new RemoveAnimeListItemsEvent();
        event.setAnimeListItems(animeListItems);
        try {
            repository.removeAnimeListItem(animeListItems);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
