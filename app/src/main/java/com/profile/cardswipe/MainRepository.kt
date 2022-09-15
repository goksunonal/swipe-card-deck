package com.profile.cardswipe

import com.profile.lib.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private val api: RickMortyApi = RickMortyApi()) {

    suspend fun getCharacter(pageNumber: Int): Flow<Resource<Pair<Info, List<Card>>>> {
        return flow {
            emit(Resource.Loading())
            val response = api.getCharacters(pageNumber)
            response.onSuccess { characterModel ->
                val characters = characterModel.results
                emit(Resource.Success(Pair(characterModel.info, characters.map {
                    it.toCard()
                })))
            }.onFailure {
                emit(Resource.Error<Pair<Info, List<Card>>>(message = "Karakterleri alırken başarısız olundu!"))
            }
        }.flowOn(Dispatchers.IO)
    }
}