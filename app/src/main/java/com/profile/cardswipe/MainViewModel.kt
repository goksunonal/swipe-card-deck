package com.profile.cardswipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profile.lib.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository = MainRepository()) : ViewModel() {

    val characterCards = MutableStateFlow<Resource<List<Card>?>>(Resource.Success(null))
    val pageConfig = PageConfig()
    val pageState = MutableLiveData(pageConfig)

    init {
        getNextCharacters()
    }

    fun getPreviousCharacters() {
        viewModelScope.launch {
            repository.getCharacter((pageState.value?.currentPage?.minus(1)) ?: 1).collect {
                if (it is Resource.Success) {
                    pageState.postValue(pageConfig.apply {
                        previousPage()
                    })
                    characterCards.emit(Resource.Success(it.data?.second))
                }
            }
        }
    }

    fun getNextCharacters() {
        viewModelScope.launch {
            repository.getCharacter((pageState.value?.currentPage?.plus(1)) ?: 1).collect {
                if (it is Resource.Success) {
                    pageState.postValue(pageConfig.apply {
                        nextPage()
                    })
                    characterCards.emit(Resource.Success(it.data?.second))
                }
            }
        }
    }
}