package com.example.foodiefood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class FoodMenuAdapter(
    private val foodItems: List<FoodItem>,
    private val onAddToCart: (FoodItem, Int) -> Unit
) : RecyclerView.Adapter<FoodMenuAdapter.ViewHolder>() {

    private val quantities = MutableList(foodItems.size) { 1 }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_food_name)
        val description: TextView = view.findViewById(R.id.tv_food_description)
        val price: TextView = view.findViewById(R.id.tv_food_price)
        val quantity: TextView = view.findViewById(R.id.tv_quantity)
        val btnPlus: ImageView = view.findViewById(R.id.btn_plus)
        val btnMinus: ImageView = view.findViewById(R.id.btn_minus)
        val btnAdd: MaterialButton = view.findViewById(R.id.btn_add_to_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foodItems[position]
        holder.name.text = food.name
        holder.description.text = food.description
        holder.price.text = "$${String.format("%.2f", food.price)}"
        holder.quantity.text = quantities[position].toString()

        holder.btnPlus.setOnClickListener {
            quantities[position]++
            holder.quantity.text = quantities[position].toString()
        }

        holder.btnMinus.setOnClickListener {
            if (quantities[position] > 1) {
                quantities[position]--
                holder.quantity.text = quantities[position].toString()
            }
        }

        holder.btnAdd.setOnClickListener {
            onAddToCart(food, quantities[position])
            // Optional: reset quantity after adding
            // quantities[position] = 1
            // holder.quantity.text = "1"
        }
    }

    override fun getItemCount() = foodItems.size
}