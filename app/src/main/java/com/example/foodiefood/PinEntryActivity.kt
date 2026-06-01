package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PinEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_entry)

        val etPin = findViewById<EditText>(R.id.et_pin)
        val btnConfirm = findViewById<Button>(R.id.btn_confirm_payment)

        btnConfirm.setOnClickListener {
            if (etPin.text.toString() == "2206") {
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_SHORT).show()
            }
        }
    }
}