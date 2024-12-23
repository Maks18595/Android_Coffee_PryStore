package com.first.android_coffee_prystore.pages.app.favourites

import com.first.android_coffee_prystore.AppUiState
import com.first.android_coffee_prystore.prototype.ProductDetails

data class FavouritesUiState(
    val items: List<ProductDetails> = emptyList()
): AppUiState