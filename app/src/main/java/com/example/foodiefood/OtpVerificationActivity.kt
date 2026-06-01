package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.telephony.SmsManager
import android.os.Build

class OtpVerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        val phoneNumber = intent.getStringExtra("PHONE_NUMBER") ?: ""
        val flow = intent.getStringExtra("FLOW") ?: ""
        val instructionText = findViewById<TextView>(R.id.otpInstructionText)
        
        if (phoneNumber.isNotEmpty()) {
            instructionText.text = "Please enter the code to continue"
            
            if (phoneNumber == "9600764319") {
                try {
                    val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        this.getSystemService(SmsManager::class.java)!!
                    } else {
                        @Suppress("DEPRECATION")
                        SmsManager.getDefault()
                    }
                    smsManager.sendTextMessage(phoneNumber, null, "Your TASTY SPOT OTP is: 1234", null, null)
                    Toast.makeText(this, "OTP sent to your phone!", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Failed to send SMS. Ensure permission is granted.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val digit1 = findViewById<EditText>(R.id.otpDigit1)
        val digit2 = findViewById<EditText>(R.id.otpDigit2)
        val digit3 = findViewById<EditText>(R.id.otpDigit3)
        val digit4 = findViewById<EditText>(R.id.otpDigit4)

        findViewById<Button>(R.id.verifyOtpButton).setOnClickListener {
            val enteredOtp = "${digit1.text}${digit2.text}${digit3.text}${digit4.text}"
            
            val isVerified = if (phoneNumber == "9600764319") {
                enteredOtp == "1234"
            } else {
                enteredOtp.length == 4
            }

            if (isVerified) {
                Toast.makeText(this, "OTP Verified Successfully!", Toast.LENGTH_SHORT).show()
                if (flow == "REGISTER") {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else if (flow == "FORGOT_PASSWORD") {
                    val intent = Intent(this, ResetPasswordActivity::class.java)
                    intent.putExtra("PHONE_NUMBER", phoneNumber)
                    startActivity(intent)
                } else {
                    startActivity(Intent(this, DashboardActivity::class.java))
                }
                finish()
            } else {
                Toast.makeText(this, "INVALID OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
