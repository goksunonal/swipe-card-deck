package com.profile.cardswipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profile.lib.Card
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository = MainRepository()) : ViewModel() {

    val characterCards = MutableLiveData<Resource<List<Card>>>()
    val pageConfig = PageConfig(1, 2)

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            repository.getCharacter(pageConfig.currentPage).collect {
                pageConfig.currentPage++
                characterCards.value = it
            }
        }
    }
}