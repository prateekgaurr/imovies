package com.prateek.imovies.models;

import java.util.List;

public class Category{
    public String category_id;
    public String category_name;
    public int parent_id;
    private List<Movie> movies;
}



