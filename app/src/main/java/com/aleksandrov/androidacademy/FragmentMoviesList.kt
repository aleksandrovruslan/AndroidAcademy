package com.aleksandrov.androidacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            R.layout.fragment_movies_list, container, false
        )
        val movieCard: View = root.findViewById(R.id.movie_card)
        movieCard.setOnClickListener {
            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.movie_place, FragmentMoviesDetails.newInstance())
                    .commit()
            }
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }

}