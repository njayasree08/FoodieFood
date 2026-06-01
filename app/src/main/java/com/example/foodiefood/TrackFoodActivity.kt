package com.example.foodiefood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TrackFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_food)

        findViewById<Button>(R.id.btn_live_chat).setOnClickListener {
            val intent = Intent(this, LiveChatActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_finish_order).setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }
    }
}