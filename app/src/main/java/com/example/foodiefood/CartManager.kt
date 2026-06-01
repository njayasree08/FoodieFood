package com.example.foodiefood

object CartManager {
    private val items = mutableListOf<CartItem>()
    const val DELIVERY_FEE = 25.0

    fun getItems(): List<CartItem> = items

    fun addItem(item: CartItem) {
        val existingItem = items.find { it.name == item.name && it.restaurantName == item.restaurantName }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            items.add(item)
        }
    }

    fun removeItem(name: String, restaurantName: String) {
        items.removeIf { it.name == name && it.restaurantName == restaurantName }
    }

    fun clearCart() {
        items.clear()
    }

    fun calculateSubtotal(): Double {
        return items.sumOf { 
            val priceValue = it.price.replace("$", "").toDoubleOrNull() ?: 0.0
            priceValue * it.quantity
        }
    }

    fun calculateTotal(): Double {
        val subtotal = calculateSubtotal()
        return if (subtotal > 0) subtotal + DELIVERY_FEE else 0.0
    }
}