package com.example.foodiefood

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TrackfoodActivities : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trackfood_activities)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dateTimeText = findViewById<TextView>(R.id.currentDateTime)
        val btnChatWithDriver = findViewById<View>(R.id.btnChatWithDriver)
        val markAsReceivedLayout = findViewById<View>(R.id.markAsReceivedLayout)
        val cbMarkReceived = findViewById<CheckBox>(R.id.cbMarkReceived)
        val btnCall = findViewById<View>(R.id.btnCall)
        val btnEmail = findViewById<View>(R.id.btnEmail)
        val closeButton = findViewById<View>(R.id.closeTrackButton)

        // Set Current Date/Time
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
        dateTimeText.text = sdf.format(Date())

        btnChatWithDriver.setOnClickListener {
            val intent = Intent(this, LiveChatActivity::class.java)
            startActivity(intent)
        }

        markAsReceivedLayout.setOnClickListener {
            showReceivedDialog()
        }

        cbMarkReceived.setOnClickListener {
            showReceivedDialog()
        }

        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:9600764319"))
            startActivity(intent)
        }

        btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:support@foodiefood.com"))
            startActivity(intent)
        }

        closeButton.setOnClickListener { finish() }
    }

    private fun showReceivedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Order Received")
            .setMessage("Have you received your order?")
            .setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this, "Order completed!", Toast.LENGTH_SHORT).show()
                findViewById<CheckBox>(R.id.cbMarkReceived).isChecked = true
                val intent = Intent(this, ReviewActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                findViewById<CheckBox>(R.id.cbMarkReceived).isChecked = false
                dialog.dismiss()
            }
            .show()
    }
}
