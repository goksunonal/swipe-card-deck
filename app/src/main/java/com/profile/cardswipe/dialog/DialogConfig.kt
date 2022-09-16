package com.profile.cardswipe.dialog

import android.graphics.drawable.Drawable

data class DialogConfig(
    val title: String,
    val titleIcon: Drawable?,
    val icon: Drawable?,
    val message: String,
    val button: ButtonConfig,
    val isCancellable: Boolean = false,
    val dismissOnButtonAction: Boolean = true,
)