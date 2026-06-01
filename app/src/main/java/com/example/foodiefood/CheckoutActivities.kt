package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CheckoutActivities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout_activities)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<View>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val nameEdit = findViewById<EditText>(R.id.nameEditText)
        val phoneEdit = findViewById<EditText>(R.id.phoneEditText)
        val ageEdit = findViewById<EditText>(R.id.ageEditText)
        val addressEdit = findViewById<EditText>(R.id.addressEditText)
        val pincodeEdit = findViewById<EditText>(R.id.pincodeEditText)
        
        val genderSpinner = findViewById<Spinner>(R.id.genderSpinner)
        val stateSpinner = findViewById<Spinner>(R.id.stateSpinner)
        val citySpinner = findViewById<Spinner>(R.id.citySpinner)

        // Setup Spinners
        val genders = arrayOf("Male", "Female")
        val states = arrayOf("Tamil Nadu", "Kerala", "Karnataka", "Andhra Pradesh")
        val cities = arrayOf("Chennai", "Coimbatore", "Madurai", "Salem", "Trichy")

        genderSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genders)
        stateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, states)
        citySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities)

        val proceedButton = findViewById<Button>(R.id.proceedToPaymentButton)
        val totalAmount = CartManager.calculateTotal()

        proceedButton.setOnClickListener {
            if (validateForm(nameEdit, phoneEdit, ageEdit, addressEdit, pincodeEdit)) {
                val intent = Intent(this, PaymentActivities::class.java)
                intent.putExtra("TOTAL_AMOUNT", totalAmount)
                intent.putExtra("PAYMENT_METHOD", "GPay")
                startActivity(intent)
            }
        }
    }

    private fun validateForm(vararg fields: EditText): Boolean {
        var isValid = true
        for (field in fields) {
            if (field.text.toString().trim().isEmpty()) {
                field.error = "Field is required"
                isValid = false
            }
        }
        return isValid
    }
}
