package com.profile.lib.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun ImageView.loadImage(image: String?) {
    Picasso.get()
        .load(image)
        .into(this)
}

@BindingAdapter("android:src", "default", requireAll = false)
fun ImageView.loadImage(@DrawableRes image: Int?, default: Drawable?) {
    image?.let {
        this.setImageResource(image)
    } ?: run {
        this.setImageDrawable(default)
    }
}
