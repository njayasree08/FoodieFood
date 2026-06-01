package com.example.foodiefood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(
    private val restaurants: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_restaurant_name)
        val type: TextView = view.findViewById(R.id.tv_restaurant_type)
        val rating: TextView = view.findViewById(R.id.tv_restaurant_rating)
        val image: ImageView = view.findViewById(R.id.iv_restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.name.text = restaurant.name
        holder.type.text = restaurant.type
        holder.rating.text = restaurant.rating
        // holder.image.setImageResource(restaurant.imageRes) // Add logic if you have real images

        holder.itemView.setOnClickListener { onItemClick(restaurant) }
    }

    override fun getItemCount() = restaurants.size
}