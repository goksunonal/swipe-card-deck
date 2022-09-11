package com.profile.lib.util

import android.content.res.Resources

val Int.dp: Int
    get() {
        val displayMetrics = Resources.getSystem().displayMetrics.density
        return (this * displayMetrics).toInt()
    }