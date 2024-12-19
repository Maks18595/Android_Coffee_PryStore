package com.first.android_coffee_prystore.pages.app.feed

import com.first.android_coffee_prystore.AppUiState

//import com.google.android.gms.analytics.ecommerce.Product

data class FeedUiState(
    val products: List<Any> = emptyList(),
    val searchCategories: List<Any> = emptyList(),
) : AppUiState