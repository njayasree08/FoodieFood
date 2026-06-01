package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OtpActivity : AppCompatActivity() {
    
    private var isResent = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val etOtp = findViewById<EditText>(R.id.et_otp)
        val btnVerify = findViewById<Button>(R.id.btn_verify)
        val tvResend = findViewById<TextView>(R.id.tv_resend)
        val tvResentMsg = findViewById<TextView>(R.id.tv_resent_msg)

        tvResend.setOnClickListener {
            isResent = true
            tvResentMsg.visibility = View.VISIBLE
            tvResentMsg.text = "OTP resent use :1357"
            Toast.makeText(this, "New OTP Sent", Toast.LENGTH_SHORT).show()
        }

        btnVerify.setOnClickListener {
            val enteredOtp = etOtp.text.toString()
            val validOtps = mutableListOf("2468", "2026")
            if (isResent) {
                validOtps.add("1357")
            }

            if (validOtps.contains(enteredOtp)) {
                Toast.makeText(this, "Verification Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val errorMsg = if (isResent) "Invalid OTP. Use 1357" else "Invalid OTP. Use 2468"
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}