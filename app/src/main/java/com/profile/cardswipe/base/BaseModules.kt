package com.profile.cardswipe.base

import com.profile.cardswipe.client.HttpClientProvider
import org.koin.dsl.module

val resourceProviderModule = module {
    single {
        ResourceProvider(get())
    }
}

val httpClientModule = module {
    single {
        HttpClientProvider()
    }
}