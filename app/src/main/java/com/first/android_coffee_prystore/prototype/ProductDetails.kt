package com.first.android_coffee_prystore.prototype

data class ProductDetails
    (
  val id: Long = -1,
            val title: String = "",
            val description: String = "",
            val category: String = "",
            val price: Double = 0.0,
            val imageUrls: List<String> = emptyList(),
            val isFavourite: Boolean = false
    )
