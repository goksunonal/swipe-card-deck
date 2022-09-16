package com.profile.cardswipe.character.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListModel(
    val info: Info,
    val results: List<Character>
)