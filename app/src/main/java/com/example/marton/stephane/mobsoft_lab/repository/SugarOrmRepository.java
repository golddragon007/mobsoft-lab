package com.example.marton.stephane.mobsoft_lab.repository;

import android.content.Context;

import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
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
    public List<AnimeListItem> getAnimeListItems() {
        return SugarRecord.listAll(AnimeListItem.class);
    }

    @Override
    public void saveAnimeListItem(AnimeListItem animeListItem) {
        SugarRecord.saveInTx(animeListItem);
    }

    @Override
    public void updateAnimeListItems(List<AnimeListItem> animeListItems) {
        List<AnimeListItem> favourites = getAnimeListItems();
        List<AnimeListItem> toUpdate = new ArrayList<>(favourites.size());
        for (AnimeListItem favourite : favourites) {
            for (AnimeListItem todo : animeListItems) {
                if (todo.getId().equals(favourite.getId())) {
                    toUpdate.add(todo);
                }
            }
        }
        SugarRecord.saveInTx(toUpdate);
    }

    @Override
    public void removeAnimeListItem(AnimeListItem animeListItem) {
        SugarRecord.deleteInTx(animeListItem);
    }

    @Override
    public boolean isInDB(AnimeListItem animeListItem) {
        return SugarRecord.findById(AnimeListItem.class, animeListItem.getIdAsLong()) != null;
    }
}
