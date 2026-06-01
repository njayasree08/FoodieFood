package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etName = findViewById<EditText>(R.id.et_name)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPhone = findViewById<EditText>(R.id.et_phone)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val btnRegister = findViewById<Button>(R.id.btn_register)
        val tvLoginLink = findViewById<TextView>(R.id.tv_login_link)

        // Set default values for convenience (as requested)
        etEmail.setText("njayasree033@gmail.com")
        etPhone.setText("9600764319")
        etPassword.setText("080806")

        btnRegister.setOnClickListener {
            if (etEmail.text.toString() == "njayasree033@gmail.com" && 
                etPhone.text.toString() == "9600764319" && 
                etPassword.text.toString() == "080806") {
                val intent = Intent(this, OtpActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please use the provided credentials", Toast.LENGTH_SHORT).show()
            }
        }

        tvLoginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}