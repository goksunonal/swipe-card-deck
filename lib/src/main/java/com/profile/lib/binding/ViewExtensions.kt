package com.profile.lib.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun View.setVisibility(isVisible: Boolean): Int {
    return if (isVisible) View.VISIBLE else View.GONE
}