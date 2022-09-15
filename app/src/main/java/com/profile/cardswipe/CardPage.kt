package com.profile.cardswipe

import com.profile.lib.Card

data class CardPage(
    val pageConfig: PageConfig,
    val cardList: List<Card>
)
