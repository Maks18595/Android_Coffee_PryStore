package com.first.android_coffee_prystore

data class PaymentMethod(
    val id: Long,
    val name: String,
    val number: String,
    val isSelected: Boolean
)