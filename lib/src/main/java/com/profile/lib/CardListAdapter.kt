package com.profile.lib

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.profile.lib.databinding.CardTemplateBinding

class CardListAdapter : PagingDataAdapter<Card, CardViewHolder>(CardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            CardTemplateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class CardDiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.name == newItem.name && oldItem.image == newItem.image
                && oldItem.status == newItem.status && oldItem.location == newItem.location
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}

class CardViewHolder(private val cardView: CardTemplateBinding) :
    RecyclerView.ViewHolder(cardView.root) {
    fun bind(item: Card) {
        cardView.card = item
    }
}