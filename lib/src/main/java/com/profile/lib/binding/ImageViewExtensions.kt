package com.profile.lib.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("android:src", "default", requireAll = false)
fun ImageView.loadImage(image: Drawable?, default: Drawable?) {
    this.load(image) {
        placeholder(default)
    }
}

@BindingAdapter("android:src", "default", requireAll = false)
fun ImageView.loadImage(image: String?, default: Drawable?) {
    this.load(image) {
        placeholder(default)
    }
}

@BindingAdapter("android:src", "default", requireAll = false)
fun ImageView.loadImage(@DrawableRes image: Int?, default: Drawable?) {
    image?.let {
        this.setImageResource(image)
    } ?: run {
        this.setImageDrawable(default)
    }
}
