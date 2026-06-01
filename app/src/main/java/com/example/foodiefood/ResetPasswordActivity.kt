package com.example.foodiefood

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val newPass = findViewById<EditText>(R.id.newPassword)
        val confirmPass = findViewById<EditText>(R.id.confirmPassword)
        val resetBtn = findViewById<Button>(R.id.resetButton)

        resetBtn.setOnClickListener {
            val pass1 = newPass.text.toString().trim()
            val pass2 = confirmPass.text.toString().trim()

            if (pass1.isEmpty() || pass2.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (pass1 != pass2) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("password", pass1)
                    apply()
                }
                Toast.makeText(this, "Password reset successful! Please login.", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
        }
    }
}
