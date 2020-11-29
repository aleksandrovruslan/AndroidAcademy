package com.aleksandrov.androidacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {

    private var _listener: View.OnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            R.layout.fragment_movies_list, container, false
        )
        root.setOnClickListener(_listener)
        return root
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        _listener = listener
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }

}