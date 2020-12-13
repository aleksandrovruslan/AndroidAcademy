package com.aleksandrov.androidacademy

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.androidacademy.data.loadMovies
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment() {

    private val mainScope = MainScope()
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            R.layout.fragment_movies_list, container, false
        )
        recycler = root.findViewById(R.id.movies_recycler)
        if (getActivity()?.getResources()
                ?.getConfiguration()?.orientation == Configuration.ORIENTATION_PORTRAIT
        ) {
            recycler.setLayoutManager(GridLayoutManager(context, 2))
        } else {
            recycler.setLayoutManager(GridLayoutManager(context, 3));
        }
        loadData()
        return root
    }

    private fun loadData() {
        mainScope.launch {
            context?.also { context ->
                loadMovies(context).also { movies ->
                    recycler.adapter = MovieAdapter(movies) { movie ->
                        activity?.also { activity ->
                            activity.supportFragmentManager.beginTransaction()
                                .addToBackStack(null)
                                .replace(
                                    R.id.movie_place,
                                    FragmentMoviesDetails.newInstance(movie)
                                )
                                .commit()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }

}