package com.itpl.iot

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.HttpURLConnection
import java.net.URL


class Controls : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controls)
        val on = findViewById<Button>(R.id.on)
        val off = findViewById<Button>(R.id.off)
        on.setOnClickListener {
// missing 'http://' will cause crashed
            val url = URL("https://api.thingspeak.com/update?api_key=1ZZRQ3GEWA04ZTEG&field2=1")
            val con = url.openConnection() as HttpURLConnection
            con.requestMethod = "GET"
            val toast = Toast.makeText(
                applicationContext,
                "Device ON",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }


        off.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://api.thingspeak.com/update?api_key=1ZZRQ3GEWA04ZTEG&field2=0") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)


            val toast = Toast.makeText(
                applicationContext,
                "Device OFF",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }
}