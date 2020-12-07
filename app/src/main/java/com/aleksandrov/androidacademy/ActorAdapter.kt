package com.aleksandrov.androidacademy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.androidacademy.data.Actor
import com.bumptech.glide.Glide

class ActorAdapter(private val actors: List<Actor>) : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }

}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val actorImg: ImageView? = itemView.findViewById(R.id.actor)
    private val actorTitle: TextView? = itemView.findViewById(R.id.actor_title)

    fun onBind(actor: Actor) {
        actorImg?.let {
            Glide.with(itemView)
                .load(actor.imageUrl)
                .into(it)
        }
        actorTitle?.text = actor.title
    }

}