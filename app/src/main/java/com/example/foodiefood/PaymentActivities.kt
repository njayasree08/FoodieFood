package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class PaymentActivities : AppCompatActivity() {

    private lateinit var processingSection: LinearLayout
    private lateinit var successSection: LinearLayout

    private val pinEntryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            CartManager.clearCart()
            processingSection.visibility = View.GONE
            successSection.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_activities)
        
        processingSection = findViewById(R.id.paymentProcessingSection)
        successSection = findViewById(R.id.successSection)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val amount = intent.getDoubleExtra("TOTAL_AMOUNT", 0.0)
        val method = intent.getStringExtra("PAYMENT_METHOD") ?: "GPay"

        val amountValue = findViewById<TextView>(R.id.paymentAmountValue)
        val methodLabel = findViewById<TextView>(R.id.paymentMethodLabel)
        val scanOption = findViewById<View>(R.id.scanPaymentOption)
        val trackOrderButton = findViewById<Button>(R.id.trackOrderButton)

        amountValue.text = String.format(Locale.getDefault(), "$%.2f", amount)
        methodLabel.text = "Paying via: $method"

        scanOption.setOnClickListener {
            val intent = Intent(this, PinEntryActivity::class.java)
            intent.putExtra("AMOUNT", amount)
            pinEntryLauncher.launch(intent)
        }

        trackOrderButton.setOnClickListener {
            val intent = Intent(this, TrackfoodActivities::class.java)
            // Remove CLEAR_TOP if you want to keep stack, but for "Track Order" usually we want a fresh start
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}