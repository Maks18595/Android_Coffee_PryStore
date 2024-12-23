package com.first.android_coffee_prystore.prototype

import com.first.android_coffee_prystore.UiEvent

sealed class ProductUiEvent: UiEvent {
 data class LoadData(val id: Long): ProductUiEvent()
    data object AddToCartOnClick: ProductUiEvent()
    data object UpdateFavouriteOnClick: ProductUiEvent()
}