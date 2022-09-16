package com.profile.cardswipe

import com.profile.cardswipe.base.AbstractContentProvider
import com.profile.cardswipe.base.httpClientModule
import com.profile.cardswipe.base.resourceProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class Initializer : AbstractContentProvider(), KoinComponent {

    override fun onCreate(): Boolean {
        startKoin {
            androidContext(checkNotNull(context))
            modules(resourceProviderModule, httpClientModule)
        }

        return true
    }
}