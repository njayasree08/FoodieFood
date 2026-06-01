package com.example.foodiefood

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class LiveChatActivity : AppCompatActivity() {

    private lateinit var containerMessages: LinearLayout
    private lateinit var etMessage: EditText
    private lateinit var btnSend: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_chat)

        containerMessages = findViewById(R.id.container_messages)
        etMessage = findViewById(R.id.et_message)
        btnSend = findViewById(R.id.btn_send)

        // Initial message
        addMessage("Support: Hello! How can we help you today?", false)

        btnSend.setOnClickListener {
            val msg = etMessage.text.toString().trim()
            if (msg.isNotEmpty()) {
                addMessage("You: $msg", true)
                etMessage.text.clear()
                handleResponse(msg)
            }
        }
    }

    private fun addMessage(text: String, isUser: Boolean) {
        val tv = TextView(this)
        tv.text = text
        tv.setPadding(24, 16, 24, 16)
        tv.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 8, 0, 8)
            gravity = if (isUser) Gravity.END else Gravity.START
        }
        tv.setBackgroundResource(if (isUser) R.drawable.bubble_user else R.drawable.bubble_support)
        tv.setTextColor(if (isUser) getColor(R.color.white) else getColor(R.color.black))
        containerMessages.addView(tv)
        
        // Scroll to bottom
        findViewById<android.view.View>(R.id.sv_messages).post {
            findViewById<androidx.core.widget.NestedScrollView>(R.id.sv_messages).fullScroll(android.view.View.FOCUS_DOWN)
        }
    }

    private fun handleResponse(userMsg: String) {
        val lowerMsg = userMsg.lowercase()
        val response = when {
            lowerMsg.contains("order") -> "I'm checking your order status. It should be with you shortly!"
            lowerMsg.contains("location") || lowerMsg.contains("where") -> "The rider is currently near your area."
            lowerMsg.contains("hello") || lowerMsg.contains("hi") -> "Hello! I am your support assistant."
            lowerMsg.contains("thanks") || lowerMsg.contains("thank you") -> "You're welcome! Happy to help."
            else -> "I understand. Is there anything else I can assist you with?"
        }

        Handler(Looper.getMainLooper()).postDelayed({
            addMessage("Support: $response", false)
        }, 1500)
    }
}
