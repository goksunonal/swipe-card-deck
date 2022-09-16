package com.profile.cardswipe.base.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <T> Fragment.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, block)
}