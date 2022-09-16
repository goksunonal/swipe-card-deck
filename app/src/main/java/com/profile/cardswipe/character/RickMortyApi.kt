package com.profile.cardswipe.character

import com.profile.cardswipe.character.model.CharacterListModel
import com.profile.cardswipe.client.HttpClientProvider
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RickMortyApi : KoinComponent {
    private val clientProvider by inject<HttpClientProvider>()

    suspend fun getCharacters(page: Int): Result<CharacterListModel> = kotlin.runCatching {
        clientProvider.client.get("https://rickandmortyapi.com/api/character") {
            this.parameter("page", page)
        }.body()
    }
}