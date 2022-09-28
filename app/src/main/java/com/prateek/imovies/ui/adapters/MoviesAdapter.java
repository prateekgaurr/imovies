package com.prateek.imovies.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prateek.imovies.R;
import com.prateek.imovies.databinding.SingleMovieBinding;
import com.prateek.imovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private final List<Movie> movies;
    private final Context context;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.single_movie, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movieBinding.movieName.setText(movie.title);
        holder.movieBinding.movieYear.setText("("+movie.year+")");
        if(!TextUtils.isEmpty(movie.stream_icon)){
            Picasso.get()
                    .load(movie.stream_icon)
                    .fit()
//                    .resize(holder.movieBinding.movieImage.getMeasuredWidth(), holder.movieBinding.movieImage.getMeasuredHeight())
                    .error(R.drawable.movie)
                    .into(holder.movieBinding.movieImage);
        }
    }

    @Override
    public int getItemCount() {
        if(movies == null) return 0;
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SingleMovieBinding movieBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieBinding = SingleMovieBinding.bind(itemView);
        }
    }
}
