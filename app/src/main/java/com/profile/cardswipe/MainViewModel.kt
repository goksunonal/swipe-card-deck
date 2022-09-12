package com.profile.cardswipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profile.lib.Card
import kotlinx.coroutines.launch

class MainViewModel(private val api: RickMortyApi) : ViewModel() {
    private val currentPage = MutableLiveData(0)
    var maxPageNumber = 10

    val liveData = MutableLiveData<MutableList<Card>>()

    fun getCharactersModel(isNextPage: Boolean) {
        val cardList = mutableListOf<Card>()
        val page = if (isNextPage) currentPage.value?.plus(1) else currentPage.value?.minus(1)
        page?.let { pageNumber ->
            currentPage.value = pageNumber
            if (maxPageNumber > pageNumber) {
                viewModelScope.launch {
                    val characters = api.getCharacters(pageNumber)
                    characters.results.forEach {
                        cardList.add(Card(it.image, it.name, it.status, it.location.name))
                    }
                    liveData.value = cardList
                }
            }
        }
    }
}
