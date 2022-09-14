package com.profile.cardswipe

import androidx.paging.PagingSource
import androidx.paging.PagingState

class RickAndMortyService(
    private val service: RickMortyApi
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex = params.key
        return try {
            pageIndex?.let {
                val response = service.getCharacters(
                    page = pageIndex
                )
                val characters = response.results
                val nextKey = if (pageIndex == 2) {
                    null
                } else {
                    pageIndex + 1
                }
                LoadResult.Page(
                    data = characters,
                    prevKey = null,
                    nextKey = nextKey
                )
            } ?: kotlin.run {
                return LoadResult.Invalid()
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}