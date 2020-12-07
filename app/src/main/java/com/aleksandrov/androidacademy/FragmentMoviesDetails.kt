package com.aleksandrov.androidacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.androidacademy.data.Actor

class FragmentMoviesDetails : Fragment() {

    private val actors: List<Actor> = MutableList(20) {
        Actor(
            "https://st.kp.yandex.net/im/kadr/3/3/7/kinopoisk.ru-Keanu-Reeves-3379590.jpg",
            "Keanu Reeves"
        )
    }

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
        val recycler: RecyclerView = root.findViewById(R.id.actors_recycler)
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = ActorAdapter(actors)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesDetails()
    }

}