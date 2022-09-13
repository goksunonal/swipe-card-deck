package com.profile.cardswipe

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}

class MainRepositoryImpl() : MainRepository {
    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50
            ),
            pagingSourceFactory = { RickAndMortyService(RickMortyApi()) }
        ).flow
    }
}