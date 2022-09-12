package com.profile.cardswipe

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class RickMortyApi {
    private val client = HttpClient(Android) {
        install(Logging) {
            this.level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getCharacters(page: Int): CharacterListModel =
        client.get("https://rickandmortyapi.com/api/character") {
            this.parameter("page", page)
        }.body()
}