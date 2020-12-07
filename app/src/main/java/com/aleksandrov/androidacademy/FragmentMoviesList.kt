package com.aleksandrov.androidacademy

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.androidacademy.data.Movie

class FragmentMoviesList : Fragment() {

    private val listener: ClickListener = object : ClickListener {
        override fun onClick(movie: Movie) {
            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.movie_place, FragmentMoviesDetails.newInstance())
                    .commit()
            }
        }
    }

    private val movies: List<Movie> = MutableList(20) { index ->
        Movie(
            "Avengers: End Game",
            "13+",
            index % 2 == 0,
            "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_UX182_CR0,0,182,268_AL_.jpg",
            listOf("Action", "Adventure", "Fantasy"),
            5.0f,
            125,
            137
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            R.layout.fragment_movies_list, container, false
        )
        val recycler: RecyclerView = root.findViewById(R.id.movies_recycler)
        if(getActivity()?.getResources()?.getConfiguration()?.orientation == Configuration.ORIENTATION_PORTRAIT){
            recycler.setLayoutManager(GridLayoutManager(context, 2))
        }
        else{
            recycler.setLayoutManager(GridLayoutManager(context, 3));
        }
        val adapter = MovieAdapter(listener)
        recycler.adapter = adapter
        adapter.bindMovies(movies)
        adapter.notifyDataSetChanged()
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }

}