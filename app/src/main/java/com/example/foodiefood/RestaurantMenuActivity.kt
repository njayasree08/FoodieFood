package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class RestaurantMenuActivity : AppCompatActivity() {

    private lateinit var restaurantName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_menu)

        restaurantName = intent.getStringExtra("RESTAURANT_NAME") ?: "Restaurant"
        val restaurantInfo = intent.getStringExtra("RESTAURANT_INFO") ?: ""

        findViewById<TextView>(R.id.tv_restaurant_name).text = restaurantName
        findViewById<TextView>(R.id.tv_restaurant_info).text = restaurantInfo
        findViewById<TextView>(R.id.tv_toolbar_title).text = restaurantName

        val foodItems = List(13) { i ->
            FoodItem(
                name = "$restaurantName Special ${i + 1}",
                description = "Experience our signature dish prepared with the finest ingredients and authentic recipes.",
                price = 10.0 + i * 2.5
            )
        }

        val rvMenu = findViewById<RecyclerView>(R.id.rv_menu)
        rvMenu.layoutManager = LinearLayoutManager(this)
        rvMenu.adapter = FoodMenuAdapter(foodItems) { food, quantity ->
            CartManager.addItem(CartItem(food.name, restaurantName, "$${food.price}", quantity))
            updateCartStrip()
        }

        findViewById<MaterialCardView>(R.id.cv_view_cart).setOnClickListener {
            startActivity(Intent(this, CartActivities::class.java))
        }

        findViewById<View>(R.id.toolbar).setOnClickListener { finish() }

        updateCartStrip()
    }

    override fun onResume() {
        super.onResume()
        updateCartStrip()
    }

    private fun updateCartStrip() {
        val cartItems = CartManager.getItems()
        val cartStrip = findViewById<MaterialCardView>(R.id.cv_view_cart)
        
        if (cartItems.isNotEmpty()) {
            cartStrip.visibility = View.VISIBLE
            findViewById<TextView>(R.id.tv_cart_count).text = "${cartItems.sumOf { it.quantity }} Items"
            findViewById<TextView>(R.id.tv_cart_total).text = "$${String.format("%.2f", CartManager.calculateTotal())}"
        } else {
            cartStrip.visibility = View.GONE
        }
    }
}