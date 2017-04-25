package com.example.marton.stephane.mobsoft_lab.repository;

import android.content.Context;

import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.models.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marton on 2017.04.10..
 */

public class MemoryRepository implements Repository {
    private static final long MINUTE = 60 * 1000;

    public static List<AnimeListItem> animeListItems;
    public static Profile profile;

    @Override
    public void open(Context context) {
        animeListItems = new ArrayList<>();
    }

    @Override
    public void close() {

    }

    @Override
    public List<AnimeListItem> getAnimeListItems() {
        return animeListItems;
    }

    @Override
    public void saveAnimeListItem(AnimeListItem animeListItem) {
        animeListItems.add(animeListItem);

    }

    @Override
    public void updateAnimeListItems(List<AnimeListItem> animeListItems) {
        for (int i = 0; i < this.animeListItems.size(); i++) {
            AnimeListItem favourite = this.animeListItems.get(i);
            for (AnimeListItem animeListItem : animeListItems) {
                if (animeListItem.getId().equals(favourite.getId())) {
                    this.animeListItems.set(i, animeListItem);
                }
            }
        }
    }

    @Override
    public void removeAnimeListItem(AnimeListItem animeListItem) {
        animeListItems.remove(animeListItem);
    }

    @Override
    public Profile Login() {
        return profile;
    }

    @Override
    public boolean isInDB(AnimeListItem flight) {
        return animeListItems.contains(flight);
    }
}
