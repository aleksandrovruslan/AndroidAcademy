package com.aleksandrov.androidacademy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val MOVIES_LIST_FRAGMENT = "MOVIES_LIST_FRAGMENT"
    private lateinit var fragmentMoviesList: FragmentMoviesList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            initFragmentMoviesList()
        } else {
            fragmentMoviesList =
                supportFragmentManager.findFragmentByTag(MOVIES_LIST_FRAGMENT) as FragmentMoviesList
            if (fragmentMoviesList == null) {
                initFragmentMoviesList()
            }
        }
        fragmentMoviesList.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.movie_details_place, FragmentMoviesDetails.newInstance())
            .commit()
    }

    private fun initFragmentMoviesList() {
        fragmentMoviesList = FragmentMoviesList.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.movie_place, fragmentMoviesList, MOVIES_LIST_FRAGMENT)
            .commit()
    }

}