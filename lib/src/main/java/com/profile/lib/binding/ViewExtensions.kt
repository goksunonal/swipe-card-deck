package com.profile.lib.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun View.setVisibility(value: Boolean) {
    this.visibility = if (value) {
        View.VISIBLE
    } else {
        View.GONE
    }
}