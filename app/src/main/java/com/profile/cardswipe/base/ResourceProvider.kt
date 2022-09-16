package com.profile.cardswipe.base

import android.content.Context
import androidx.annotation.StringRes
import org.koin.core.component.KoinComponent

class ResourceProvider(private val context: Context) : KoinComponent {

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return context.getString(resId, *formatArgs)
    }
}