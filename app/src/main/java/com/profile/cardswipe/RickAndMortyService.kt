package com.profile.cardswipe

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlin.system.exitProcess

const val NETWORK_PAGE_SIZE = 500

class RickAndMortyService(
    private val service: RickMortyApi
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex = params.key ?: 1
        return try {
            val response = service.getCharacters(
                page = pageIndex
            )
            val characters = response.results
            val nextKey = if (response.info.next.isNullOrBlank()) {
                null
            } else {
                pageIndex + 1
            }
            LoadResult.Page(
                data = characters,
                prevKey = if (pageIndex != 1) pageIndex - 1 else null,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            exitProcess(0)
            return LoadResult.Error(exception)
        }
    }

}