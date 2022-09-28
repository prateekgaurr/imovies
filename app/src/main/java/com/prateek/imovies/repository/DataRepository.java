package com.prateek.imovies.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prateek.imovies.api.DataService;
import com.prateek.imovies.api.RetfofitHelper;
import com.prateek.imovies.models.Category;
import com.prateek.imovies.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private final DataService service;
    private final MutableLiveData<List<Movie>> movieLiveData;
    private final MutableLiveData<List<Category>> categoryLiveData;


    public DataRepository() {
        this.service = RetfofitHelper.getInstance().create(DataService.class);
        this.movieLiveData = new MutableLiveData<>();
        this.categoryLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getAllMovies(){
        service.getMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if(response.isSuccessful() && response.body() != null){
                    movieLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
            }
        });
        return movieLiveData;
    }


    public LiveData<List<Category>> getAllCategories(){
        service.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                categoryLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
            }
        });
        return categoryLiveData;
    }
}
