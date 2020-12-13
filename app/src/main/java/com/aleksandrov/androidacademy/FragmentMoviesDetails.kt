package com.aleksandrov.androidacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.androidacademy.data.Movie
import com.bumptech.glide.Glide

private const val ARG_MOVIE = "ARG_MOVIE"

class FragmentMoviesDetails : Fragment() {

    private lateinit var recycler: RecyclerView
    private var _movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.also {
            _movie = it.getParcelable(ARG_MOVIE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            R.layout.fragment_movies_details, container, false
        )
        initView(root)
        return root
    }

    private fun initView(root: View) {
        val backTv: TextView = root.findViewById(R.id.back_arrow_btn)
        backTv.setOnClickListener {
            activity?.let {
                it.supportFragmentManager.popBackStack()
            }
        }
        val backgroundImg: ImageView = root.findViewById(R.id.background_origin)
        _movie?.backdrop.also {
            Glide.with(root)
                .load(it)
                .into(backgroundImg)
        }
        val pgTv: TextView = root.findViewById(R.id.pg)
        _movie?.minimumAge.also {
            pgTv.text = "$it+"
        }
        val movieNameTv: TextView = root.findViewById(R.id.movie_name)
        _movie?.title.also {
            movieNameTv.text = it
        }
        val taglineTv: TextView = root.findViewById(R.id.tagline)
        _movie?.genres?.map { it.name }?.joinToString(separator = ", ").also {
            taglineTv.text = it
        }
        val ratingBar: RatingBar = root.findViewById(R.id.rating_bar)
        _movie?.ratings?.also {
            ratingBar.rating = it / 2
        }
        val reviewsTv: TextView = root.findViewById(R.id.reviews)
        _movie?.numberOfRatings?.also {
            reviewsTv.text = context?.getString(R.string.movie_reviews, it)
        }
        val storylineContentTV: TextView = root.findViewById(R.id.storyline_content)
        _movie?.overview?.also {
            storylineContentTV.text = it
        }
        recycler = root.findViewById(R.id.actors_recycler)
        recycler.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        recycler.adapter = ActorAdapter(_movie?.actors ?: ArrayList())
    }

    companion object {
        @JvmStatic
        fun newInstance(movie: Movie) = FragmentMoviesDetails().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_MOVIE, movie)
            }
        }
    }

}