package com.first.android_coffee_prystore.checkout

import com.first.android_coffee_prystore.AppUiState
import com.first.android_coffee_prystore.DeliveryAddress
import com.first.android_coffee_prystore.PaymentMethod
import com.first.android_coffee_prystore.ProductCart


data class CheckoutUiState(
    val address: List<DeliveryAddress>,
    val paymentMethod: List<PaymentMethod>,
 val products: List<ProductCart>,
    val total: Double
    ): AppUiState