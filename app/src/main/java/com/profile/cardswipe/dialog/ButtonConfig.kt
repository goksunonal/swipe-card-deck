package com.profile.cardswipe.dialog

data class ButtonConfig(
    val text: String,
    var listener: () -> Unit
)