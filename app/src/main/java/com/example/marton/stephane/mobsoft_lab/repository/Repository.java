package com.example.marton.stephane.mobsoft_lab.repository;

import android.content.Context;

import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;
import com.example.marton.stephane.mobsoft_lab.models.Todo;

import java.util.List;

/**
 * Created by Marton on 2017.04.10..
 */

public interface Repository {

    void open(Context context);

    void close();

    List<AnimeListItem> getAnimeListItems();

    void saveAnimeListItem(AnimeListItem animeListItem);

    void updateAnimeListItems(List<AnimeListItem> animeListItems);

    void removeAnimeListItem(AnimeListItem animeListItem);

    boolean isInDB(AnimeListItem animeListItem);
}
