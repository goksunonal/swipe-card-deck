package com.profile.cardswipe

import com.profile.lib.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private val api: RickMortyApi = RickMortyApi()) {
    suspend fun getCharacter(currentPage: Int): Flow<Resource<List<Card>>> {
        return flow {
            emit(Resource.Loading())
            val response = api.getCharacters(currentPage)
            val characters = response.results
            emit(Resource.Success(characters.map {
                Card(
                    it.image,
                    it.name,
                    it.status,
                    it.location.name
                )
            }))
        }.flowOn(Dispatchers.IO)
    }
}