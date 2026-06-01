package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class PaymentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val amount = intent.getDoubleExtra("TOTAL_AMOUNT", 0.0)
        val method = intent.getStringExtra("PAYMENT_METHOD") ?: "Card"
        
        findViewById<TextView>(R.id.paymentAmountTextView).text = 
            String.format(Locale.getDefault(), "Amount to Pay: $%.2f via %s", amount, method)

        findViewById<Button>(R.id.confirmPaymentButton).setOnClickListener {
            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, getString(R.string.order_success), Toast.LENGTH_LONG).show()
            
            CartManager.clearCart()

            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
