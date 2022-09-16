package com.profile.cardswipe.character

import org.koin.dsl.module

val characterDeckModule = module {
    single {
        CharacterDeckViewModel()
    }
    single {
        CharacterDeckRepository()
    }
    single { RickMortyApi() }
}