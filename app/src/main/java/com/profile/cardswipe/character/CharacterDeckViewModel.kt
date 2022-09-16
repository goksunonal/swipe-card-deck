package com.profile.cardswipe.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profile.cardswipe.R
import com.profile.cardswipe.base.Resource
import com.profile.cardswipe.base.ResourceProvider
import com.profile.cardswipe.base.injectOrNull
import com.profile.cardswipe.character.model.DEFAULT_PAGE_NUMBER
import com.profile.lib.model.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

private const val PAGE_OFFSET = 1

class CharacterDeckViewModel : ViewModel(), KoinComponent {
    private val resourceProvider by injectOrNull<ResourceProvider>()
    private val repository by injectOrNull<CharacterDeckRepository>()

    val characterCards = MutableStateFlow<List<Card>>(emptyList())
    val pageState = MutableLiveData(repository?.pageConfig)
    val isLoading = MutableLiveData(false)
    val hasError = MutableLiveData<String>()

    init {
        getNextCharacters()
    }

    fun getPreviousCharacters() {
        viewModelScope.launch {
            repository?.getCharacter(
                (pageState.value?.currentPage?.minus(PAGE_OFFSET)) ?: DEFAULT_PAGE_NUMBER
            )?.collectLatest {
                handleCharacterResult(it, false)
            }
        }
    }

    fun getNextCharacters() {
        viewModelScope.launch {
            repository?.getCharacter(
                (pageState.value?.currentPage?.plus(PAGE_OFFSET)) ?: DEFAULT_PAGE_NUMBER
            )?.collectLatest {
                handleCharacterResult(it, true)
            }
        }
    }

    private suspend fun handleCharacterResult(resource: Resource<List<Card>>, isNext: Boolean) {
        when (resource) {
            is Resource.Success -> {
                isLoading.value = false
                resource.data?.let { cardList ->
                    pageState.value = pageState.value?.apply {
                        if (isNext) nextPage() else previousPage()
                    }
                    characterCards.emit(cardList)
                } ?: kotlin.run {
                    hasError.value = resourceProvider?.getString(R.string.error_null_character_list)
                }
            }
            is Resource.Loading -> isLoading.value = true
            else -> {
                isLoading.value = false
                resource.message?.let {
                    hasError.value = it
                }
            }
        }
    }
}