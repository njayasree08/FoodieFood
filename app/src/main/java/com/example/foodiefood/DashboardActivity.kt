package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val restaurants = listOf(
            Restaurant("Burger King", "Fast Food • Burgers", "4.5"),
            Restaurant("Pizza Hut", "Italian • Pizza", "4.2"),
            Restaurant("Subway", "Healthy • Sandwiches", "4.3"),
            Restaurant("KFC", "Fast Food • Fried Chicken", "4.1"),
            Restaurant("Starbucks", "Beverages • Coffee", "4.7"),
            Restaurant("Domino's", "Italian • Pizza", "4.4"),
            Restaurant("McDonald's", "Fast Food • Burgers", "4.0")
        )

        val rvRestaurants = findViewById<RecyclerView>(R.id.rv_restaurants)
        rvRestaurants.layoutManager = LinearLayoutManager(this)
        rvRestaurants.adapter = RestaurantAdapter(restaurants) { restaurant ->
            val intent = Intent(this, RestaurantMenuActivity::class.java)
            intent.putExtra("RESTAURANT_NAME", restaurant.name)
            intent.putExtra("RESTAURANT_INFO", "${restaurant.type} • ★ ${restaurant.rating}")
            startActivity(intent)
        }

        findViewById<TextView>(R.id.btn_view_cart_top).setOnClickListener {
            val intent = Intent(this, CartActivities::class.java)
            startActivity(intent)
        }
    }
}