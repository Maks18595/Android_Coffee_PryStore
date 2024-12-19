package com.first.android_coffee_prystore

data class Product(
 val id: Long = -1,
    val title: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val isFavourite: Boolean = false
)
