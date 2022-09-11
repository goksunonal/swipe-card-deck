package com.profile.lib

import android.location.Location

data class Card(
    val image: Int,
    val name: String,
    val status: String,
    val location: Location
)
