package com.profile.cardswipe.character.model


const val DEFAULT_PAGE_NUMBER = 1

data class PageConfig(
    var currentPage: Int = 0,
    var maxPage: Int = currentPage + 1,
    val pageOffset: Int = 1
) {
    fun nextPage() {
        currentPage += pageOffset
    }

    fun previousPage() {
        currentPage -= pageOffset
    }

    fun nextPageAvailable(): Boolean {
        return currentPage < maxPage
    }

    fun previousPageAvailable(): Boolean {
        return currentPage > DEFAULT_PAGE_NUMBER
    }
}
