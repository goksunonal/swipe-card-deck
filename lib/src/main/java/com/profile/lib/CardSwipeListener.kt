package com.profile.lib

interface CardSwipeListener {
    fun onLeftOutside()
    fun onRightOutside()
    fun onNoItemLeft() {}
}