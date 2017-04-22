package com.example.marton.stephane.mobsoft_lab.models;

import com.orm.dsl.Table;

/**
 * Created by Marton on 2017.04.10..
 */

@Table
public class Todo {
    private Long id = null;
    private String name;


    public Todo() {
    }

    public Todo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
