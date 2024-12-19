package com.first.android_coffee_prystore.pages.app.feed

import com.first.android_coffee_prystore.UiEvent


sealed class FeedUiEvent: UiEvent {

    data object LoadScreenData: FeedUiEvent()

    data class OnProductClick(val productId: Long): FeedUiEvent()

    data class OnFavouriteClick(val productId: Long): FeedUiEvent()

}