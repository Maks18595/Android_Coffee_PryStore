package com.first.android_coffee_prystore.pages.app.favourites

import com.first.android_coffee_prystore.UiEvent

sealed class FavouritesUiEvent: UiEvent {
    data object Initiate: FavouritesUiEvent()
    data class OnItemDeleted(val id: Long): FavouritesUiEvent()
}