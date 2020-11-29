package com.aleksandrov.androidacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            R.layout.fragment_movies_details, container, false
        )
        val backTv: TextView = root.findViewById(R.id.back_arrow_btn)
        backTv.setOnClickListener {
            activity?.let {
                it.supportFragmentManager.popBackStack()
            }
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesDetails()
    }

}