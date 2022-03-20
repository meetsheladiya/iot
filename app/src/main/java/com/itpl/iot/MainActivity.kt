package com.itpl.iot


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appbutton = findViewById<Button>(R.id.button)
        val readdatabutton = findViewById<Button>(R.id.button2)
        readdatabutton.setOnClickListener {
            val intent = Intent(this, ReadDataActivity::class.java)
            startActivity(intent)
        }

    }
}