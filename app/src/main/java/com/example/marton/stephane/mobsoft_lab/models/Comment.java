package com.example.marton.stephane.mobsoft_lab.models;

/**
 * Created by Marton on 2017.04.22..
 */

public class Comment {
    private String message;
    private String id;
    private String user;
    private String dateCreated;
    private String dateModified;

    public Comment(String message, String id, String user, String dateCreated, String dateModified) {
        this.message = message;
        this.id = id;
        this.user = user;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }
}
