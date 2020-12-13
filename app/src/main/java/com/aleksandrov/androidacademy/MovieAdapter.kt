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

class MovieAdapter(
    private val movies: List<Movie>,
    private val listener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position], listener)
    }

    override fun getItemCount(): Int {
        return movies.size
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

    fun onBind(movie: Movie, listener: (Movie) -> Unit) {
        movieNameTv?.text = movie.title
        pgTv?.text = "${movie.minimumAge}+"
        backgroundImg?.let {
            Glide.with(itemView)
                .load(movie.poster)
                .into(it)
        }
        tagsTv?.text = movie.genres.map { it.name }.joinToString(separator = ", ")
        ratingBar?.rating = movie.ratings / 2
        reviewsTv?.text = context.getString(
            R.string.movie_reviews,
            movie.numberOfRatings
        )
        durationTv?.text = context.getString(
            R.string.movie_duration,
            movie.runtime
        )
        itemView.setOnClickListener { listener(movie) }
    }

}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context