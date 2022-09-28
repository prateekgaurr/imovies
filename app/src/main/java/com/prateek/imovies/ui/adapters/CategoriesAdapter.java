package com.prateek.imovies.ui.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prateek.imovies.R;
import com.prateek.imovies.databinding.SingleCategoryBinding;

import com.prateek.imovies.models.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private final HashMap<String, String> categoryIdToCategoryName;
    private final Context context;
    private final Map<String, List<Movie>> categoryIdToMoviesList;

    public CategoriesAdapter(Context context, HashMap<String, String> categoryMap, Map<String, List<Movie>> movies) {
        this.categoryIdToCategoryName = categoryMap;
        this.context = context;
        this.categoryIdToMoviesList = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//            int index = (position > categoryIdToCategoryName.keySet().size()) ? position-1 : position;
//            String category = (String)categoryIdToCategoryName.keySet().toArray()[index];
//            holder.binding.movieCategory.setText(categoryIdToCategoryName.get(category));
//            if(categoryIdToMoviesList.get(category) != null && categoryIdToMoviesList.get(category).size()>1){
//                holder.binding.recyclerViewForMovies.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//                holder.binding.recyclerViewForMovies.setAdapter(new MoviesAdapter(context, categoryIdToMoviesList.get(category)));
//            }
//            else{
//                holder.binding.noMoviesFound.setVisibility(View.VISIBLE);
//                holder.binding.recyclerViewForMovies.setVisibility(GONE);
//                holder.binding.loadMoreText.setVisibility(GONE);
//            }

        int index = (position > categoryIdToMoviesList.keySet().size()) ? position-1 : position;
        String category = (String) categoryIdToMoviesList.keySet().toArray()[index];
        holder.binding.movieCategory.setText(categoryIdToCategoryName.get(category));
        holder.binding.recyclerViewForMovies.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.binding.recyclerViewForMovies.setAdapter(new MoviesAdapter(context, categoryIdToMoviesList.get(category)));

    }

    @Override
    public int getItemCount() {
//        return categoryIdToCategoryName.keySet().size();
        return categoryIdToMoviesList.keySet().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SingleCategoryBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleCategoryBinding.bind(itemView);
        }
    }
}
