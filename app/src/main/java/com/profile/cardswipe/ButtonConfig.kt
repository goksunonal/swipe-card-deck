package com.profile.cardswipe

import android.graphics.drawable.Drawable

data class ButtonConfig(
    val text: String,
    var listener: () -> Unit
)

data class DialogConfig(
    val title: String,
    val titleIcon: Drawable?,
    val icon: Drawable?,
    val message: String,
    val button: ButtonConfig,
    val isCancellable: Boolean = false,
    val dismissOnButtonAction: Boolean = true,
)