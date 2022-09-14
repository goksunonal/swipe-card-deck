package com.profile.cardswipe

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}

class MainRepositoryImpl() : MainRepository {
    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = { RickAndMortyService(RickMortyApi()) }
        ).flow
    }
}