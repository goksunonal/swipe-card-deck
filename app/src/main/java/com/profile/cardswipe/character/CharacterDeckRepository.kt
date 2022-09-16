package com.profile.cardswipe.character

import com.profile.cardswipe.base.Resource
import com.profile.cardswipe.character.model.PageConfig
import com.profile.lib.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDeckRepository : KoinComponent {
    private val api by inject<RickMortyApi>()
    val pageConfig = PageConfig()

    suspend fun getCharacter(pageNumber: Int): Flow<Resource<List<Card>>> {
        return flow {
            emit(Resource.Loading())
            val response = api.getCharacters(pageNumber)
            response.onSuccess { characterModel ->
                val characters = characterModel.results
                pageConfig.maxPage = characterModel.info.pages
                emit(Resource.Success(characters.map {
                    it.toCard()
                }))
            }.onFailure {
                emit(Resource.Error<List<Card>>(message = "Karakterleri alırken başarısız olundu!"))
            }
        }.flowOn(Dispatchers.IO)
    }
}