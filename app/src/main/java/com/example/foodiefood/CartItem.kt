package com.example.foodiefood

data class CartItem(
    val name: String,
    val restaurantName: String,
    val price: String,
    var quantity: Int
)