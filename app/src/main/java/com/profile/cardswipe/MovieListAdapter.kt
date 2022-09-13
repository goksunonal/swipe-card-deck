package com.profile.cardswipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.profile.lib.Card
import com.profile.lib.databinding.CardTemplateBinding

class MovieListAdapter() : PagingDataAdapter<Card, MoviePosterViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        return MoviePosterViewHolder(
            CardTemplateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return false
    }
}

class MoviePosterViewHolder(private val cardView: CardTemplateBinding) :
    RecyclerView.ViewHolder(cardView.root) {
    fun bind(item: Card) {
        cardView.card = item
    }
}