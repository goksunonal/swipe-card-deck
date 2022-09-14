package com.profile.cardswipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.profile.lib.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel(private val repository: MainRepository = MainRepositoryImpl()) : ViewModel() {
    fun getCharacters(): Flow<PagingData<Card>> {
        return repository.getCharacters()
            .map { character ->
                character.map {
                    Card(
                        it.image,
                        it.name,
                        it.status,
                        it.location.name
                    )
                }
            }
            .cachedIn(viewModelScope)
    }
}