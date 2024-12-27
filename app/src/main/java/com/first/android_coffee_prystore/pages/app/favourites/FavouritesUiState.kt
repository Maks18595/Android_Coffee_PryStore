package com.first.android_coffee_prystore.pages.app.favourites

import com.first.android_coffee_prystore.AppUiState

data class FavouritesUiState(
    val items: List<Any> = emptyList()
): AppUiState