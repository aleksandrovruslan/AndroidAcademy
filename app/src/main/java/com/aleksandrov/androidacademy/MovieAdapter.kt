package com.aleksandrov.androidacademy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.androidacademy.data.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private val listener: ClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    private var _movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(_movies[position], listener)
    }

    override fun getItemCount(): Int {
        return _movies.size
    }

    fun bindMovies(movies: List<Movie>) {
        _movies = movies
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val movieNameTv: TextView? = itemView.findViewById(R.id.movie_name)
    private val pgTv: TextView? = itemView.findViewById(R.id.pg)
    private val followBtn: ToggleButton? = itemView.findViewById(R.id.btn_follow)
    private val backgroundImg: ImageView? = itemView.findViewById(R.id.img_background)
    private val tagsTv: TextView? = itemView.findViewById(R.id.movie_tags)
    private val ratingBar: RatingBar? = itemView.findViewById(R.id.rating_bar)
    private val reviewsTv: TextView? = itemView.findViewById(R.id.reviews)
    private val durationTv: TextView? = itemView.findViewById(R.id.movie_duration)

    fun onBind(movie: Movie, listener: ClickListener) {
        movieNameTv?.text = movie.name
        pgTv?.text = movie.pg
        followBtn?.isChecked = movie.isFollow
        backgroundImg?.let {
            Glide.with(itemView)
                .load(movie.imageUrl)
                .into(it)
        }
        tagsTv?.text = movie.tags.joinToString(separator = ", ")
        ratingBar?.rating = movie.rating
        reviewsTv?.text = context.getString(
            R.string.movie_reviews,
            movie.reviewsCount
        )
        durationTv?.text = context.getString(
            R.string.movie_duration,
            movie.duration
        )
        itemView.setOnClickListener { listener.onClick(movie) }
    }

}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface ClickListener {
    fun onClick(movie: Movie)
}