package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class CartActivities : AppCompatActivity() {

    private lateinit var subtotalTextView: TextView
    private lateinit var deliveryFeeTextView: TextView
    private lateinit var grandTotalTextView: TextView
    private lateinit var cartRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart_activities)

        // Find views
        subtotalTextView = findViewById(R.id.subtotalTextView)
        deliveryFeeTextView = findViewById(R.id.deliveryFeeTextView)
        grandTotalTextView = findViewById(R.id.grandTotalTextView)
        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        val checkoutButton = findViewById<Button>(R.id.checkoutButton)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        
        setupCart()

        checkoutButton.setOnClickListener {
            if (CartManager.getItems().isNotEmpty()) {
                val intent = Intent(this, CheckoutActivities::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please add items to cart first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupCart() {
        val cartItems = CartManager.getItems()
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show()
        }
        cartRecyclerView.adapter = CartAdapter(cartItems)
        updateTotals()
    }

    private fun updateTotals() {
        val subtotal = CartManager.calculateSubtotal()
        val deliveryFee = if (subtotal > 0) CartManager.DELIVERY_FEE else 0.0
        val grandTotal = CartManager.calculateTotal()
        
        subtotalTextView.text = String.format(Locale.getDefault(), "$%.2f", subtotal)
        deliveryFeeTextView.text = String.format(Locale.getDefault(), "$%.2f", deliveryFee)
        grandTotalTextView.text = String.format(Locale.getDefault(), "$%.2f", grandTotal)
    }

    class CartAdapter(private val cartItems: List<CartItem>) :
        RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

        class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nameTextView: TextView = view.findViewById(R.id.itemName)
            val restaurantTextView: TextView = view.findViewById(R.id.itemRestaurant)
            val priceTextView: TextView = view.findViewById(R.id.itemPrice)
            val quantityTextView: TextView = view.findViewById(R.id.itemQuantity)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cart, parent, false)
            return CartViewHolder(view)
        }

        override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
            val item = cartItems[position]
            holder.nameTextView.text = item.name
            holder.restaurantTextView.text = item.restaurantName
            
            val unitPrice = item.price.replace("$", "").toDoubleOrNull() ?: 0.0
            val totalPrice = unitPrice * item.quantity
            
            holder.priceTextView.text = String.format(Locale.getDefault(), "$%.2f", totalPrice)
            holder.quantityTextView.text = "x${item.quantity}"
        }

        override fun getItemCount() = cartItems.size
    }
}