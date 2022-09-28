package com.prateek.imovies.api;

import com.prateek.imovies.models.Category;
import com.prateek.imovies.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("player_api.php?username=test&password=test1&action=get_vod_streams")
    Call<List<Movie>> getMovies();

    @GET("player_api.php?username=test&password=test1&action=get_vod_categories")
    Call<List<Category>> getCategories();
}
