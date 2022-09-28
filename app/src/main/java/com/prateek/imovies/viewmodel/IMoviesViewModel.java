package com.prateek.imovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prateek.imovies.models.Category;
import com.prateek.imovies.models.Movie;
import com.prateek.imovies.repository.DataRepository;

import java.util.List;

public class IMoviesViewModel extends AndroidViewModel {

    private final LiveData<List<Movie>> movieLiveData;
    private final LiveData<List<Category>> categoryLiveData;

    public IMoviesViewModel(@NonNull Application application) {
        super(application);
        DataRepository repository = new DataRepository();
        this.categoryLiveData = repository.getAllCategories();
        this.movieLiveData = repository.getAllMovies();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieLiveData;
    }

    public LiveData<List<Category>> getCategories(){
        return categoryLiveData;
    }


}
