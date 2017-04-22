package com.example.marton.stephane.mobsoft_lab.models;

/**
 * Created by Marton on 2016.11.27..
 */

public class Anime extends AnimeListItem {
    private String type;

    private SimilarAnime[] similarAnimes;

    private String description;

    private Comment[] comments;

    public String getType() {
        return type;
    }

    public SimilarAnime[] getSimilarAnimes() {
        return similarAnimes;
    }

    public String getDescription() {
        return description;
    }

    public Comment[] getComments() { return comments; }

    public void setComments(Comment[] comments) { this.comments = comments; }

    public Anime(String picURL, String id, String title, String eps, String startDate, String endDate, String ratingTemp, String ratingTempCount, String ratingPerm, String ratingPermCount, boolean restricted, boolean favorite, String type, SimilarAnime[] similarAnimes, String description) {
        super(picURL, id, title, eps, startDate, endDate, ratingTemp, ratingTempCount, ratingPerm, ratingPermCount, restricted, favorite);
        this.type = type;
        this.similarAnimes = similarAnimes;
        this.description = description;
    }

    public Anime(String picURL, String id, String title, String eps, String startDate, String endDate, String ratingTemp, String ratingTempCount, String ratingPerm, String ratingPermCount, boolean restricted, boolean favorite, String type, SimilarAnime[] similarAnimes, String description, Comment[] comments) {
        super(picURL, id, title, eps, startDate, endDate, ratingTemp, ratingTempCount, ratingPerm, ratingPermCount, restricted, favorite);
        this.type = type;
        this.similarAnimes = similarAnimes;
        this.description = description;
        this.comments = comments;
    }
}
