package com.first.android_coffee_prystore.pages.app.cart

import com.first.android_coffee_prystore.AppUiState

data class CartUiState(
    val products: Unit = emptyList(),
    val subTotal: Double = 0.0,
    val shipping: Double = 0.0,
    val discount: Double = 0.0,
    val total: Double = 0.0,
    val subtotal: Any
): AppUiState