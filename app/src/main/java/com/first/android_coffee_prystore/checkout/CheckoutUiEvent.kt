package com.first.android_coffee_prystore.checkout

import com.first.android_coffee_prystore.UiEvent

sealed class CheckoutUiEvent(

): UiEvent {
    data object LoadScreenData: CheckoutUiEvent()
    data class SelectDeliveryAddress(val id: Long): CheckoutUiEvent()
    data class SelectPaymentMethod(val id: Long): CheckoutUiEvent()
}