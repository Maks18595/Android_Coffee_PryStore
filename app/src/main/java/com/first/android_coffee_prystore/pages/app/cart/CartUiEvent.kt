package com.first.android_coffee_prystore.pages.app.cart

import com.first.android_coffee_prystore.UiEvent

sealed class CartUiEvent: UiEvent {
    data object LoadScreenData: CartUiEvent()
    data class OnChangeQuantity(val id: Long, val quantity: Int): CartUiEvent()
    class OnDecreaseQuantity {

    }

    class OnIncreaseQuantity {

    }
}