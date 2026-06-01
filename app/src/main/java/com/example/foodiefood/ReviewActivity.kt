package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val etReview = findViewById<EditText>(R.id.et_review_comment)
        val btnSubmit = findViewById<Button>(R.id.btn_submit_review)

        btnSubmit.setOnClickListener {
            val reviewText = etReview.text.toString().trim()
            
            if (reviewText.isEmpty()) {
                etReview.error = "Please write your review before submitting"
                Toast.makeText(this, "Review cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Thank you for your valuable feedback!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, DashboardActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }
}