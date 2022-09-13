package com.profile.cardswipe

import android.graphics.Rect
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import com.profile.lib.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel(private val repository: MainRepository = MainRepositoryImpl()) : ViewModel() {
    val currentPage = MutableLiveData(0)
    val count = MutableLiveData(1)

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