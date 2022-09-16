package com.profile.cardswipe.base

import org.koin.core.component.KoinComponent
import org.koin.core.parameter.ParametersDefinition


inline fun <reified T> KoinComponent.injectOrNull(noinline parameters: ParametersDefinition? = null): Lazy<T?> {
    return getKoin().injectOrNull(parameters = parameters)
}