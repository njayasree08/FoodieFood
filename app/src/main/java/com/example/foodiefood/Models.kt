package com.example.foodiefood

data class Restaurant(
    val name: String,
    val type: String,
    val rating: String,
    val imageRes: Int = 0
)

data class FoodItem(
    val name: String,
    val description: String,
    val price: Double,
    val imageRes: Int = 0
)