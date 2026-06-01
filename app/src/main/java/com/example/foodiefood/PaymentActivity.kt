package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val rgPayment = findViewById<RadioGroup>(R.id.rg_payment_methods)
        val btnPayNow = findViewById<Button>(R.id.btn_pay_now)

        btnPayNow.setOnClickListener {
            val selectedId = rgPayment.checkedRadioButtonId
            if (selectedId != -1) {
                val intent = Intent(this, PinEntryActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            }
        }
    }
}