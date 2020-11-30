package com.aleksandrov.androidacademy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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
    }

    private fun initFragmentMoviesList() {
        fragmentMoviesList = FragmentMoviesList.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.movie_place, fragmentMoviesList, MOVIES_LIST_FRAGMENT)
            .commit()
    }

}