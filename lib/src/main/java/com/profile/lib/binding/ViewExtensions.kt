package com.profile.lib.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun View.setVisibility(value: Boolean) {
    this.visibility = if (value) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("android:selected")
fun View.setSelection(value: Boolean) {
    this.isSelected = value
}