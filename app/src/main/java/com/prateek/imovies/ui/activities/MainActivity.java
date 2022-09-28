package com.prateek.imovies.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.prateek.imovies.databinding.ActivityMainBinding;
import com.prateek.imovies.models.Category;
import com.prateek.imovies.models.Movie;
import com.prateek.imovies.ui.adapters.CategoriesAdapter;
import com.prateek.imovies.viewmodel.IMoviesViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private HashMap<String, String> categoryIdToCategoryName;
    private Map<String, List<Movie>> categoryIdToMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categoryIdToCategoryName = new HashMap<>();

        binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        IMoviesViewModel viewModel = new ViewModelProvider(this).get(IMoviesViewModel.class);

        viewModel.getCategories().observe(this, categoriesFetched -> {
            for(Category category : categoriesFetched)
                categoryIdToCategoryName.put(category.category_id, category.category_name);
        });

        viewModel.getMovies().observe(this, moviesFetched -> {
            categoryIdToMovieList = getCategorizedHashmap(moviesFetched);
            binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.mainRecyclerView.setAdapter(new CategoriesAdapter(MainActivity.this, categoryIdToCategoryName, categoryIdToMovieList));
            binding.progressBar.setVisibility(View.INVISIBLE);
        });
    }


    public Map<String,List<Movie>> getCategorizedHashmap(List<Movie> unfilteredList){
        return unfilteredList.stream().collect(Collectors.groupingBy(movie -> movie.category_id));
    }
}