package com.itpl.iot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import org.json.JSONException
import com.android.volley.VolleyError
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var listView: ListView? = null
    var heroList: MutableList<Hero>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById<View>(R.id.listView) as ListView
        heroList = ArrayList()
        loadHeroList()
    }

    private fun loadHeroList() {
        val progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar.visibility = View.VISIBLE
        val stringRequest = StringRequest(
            Request.Method.GET, JSON_URL,
            { response: String? ->
                progressBar.visibility = View.INVISIBLE
                try {
                    val obj = JSONObject(response)
                    val heroArray = obj.getJSONArray("feeds")
                    for (i in 0 until heroArray.length()) {
                        val heroObject = heroArray.getJSONObject(i)
                        val hero = Hero(
                            heroObject.getString("created_at"),
                            heroObject.getString("field1"),
                            heroObject.getString("field2")
                        )
                        heroList!!.add(hero)
                    }
                    val adapter = heroList?.let { ListViewAdapter(it, applicationContext) }
                    listView!!.adapter = adapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error: VolleyError ->
            Toast.makeText(
                applicationContext,
                error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    companion object {
        private const val JSON_URL =
            "https://api.thingspeak.com/channels/1673574/feeds.json?results"
    }
}