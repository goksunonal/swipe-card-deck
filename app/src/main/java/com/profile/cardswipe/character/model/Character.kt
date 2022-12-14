package com.profile.cardswipe.character.model

import com.profile.lib.model.Card
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: NameUrlVal,
    val image: String,
    val location: NameUrlVal,
    val episode: List<String>,
    val url: String,
    val created: String
) {
    fun toCard(): Card {
        return Card(
            image,
            name,
            status,
            location.name
        )
    }
}

@Serializable
data class NameUrlVal(
    val name: String,
    val url: String
)