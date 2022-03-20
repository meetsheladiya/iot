package com.itpl.iot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.itpl.iot.R
import com.itpl.iot.ReadD
import com.itpl.iot.ReadDataAdapter
import org.json.JSONObject

class ReadDataActivity : AppCompatActivity() {
    private lateinit var mAdapter: ReadDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data)
        var recView = findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(this)
        fetchData()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mAdapter = ReadDataAdapter(this)
        recView.adapter = mAdapter
    }
    //https://api.thingspeak.com/channels/1673574/feeds.json?api_key=HWLKWQCW9YDEFDPD
        private fun fetchData() {
            val url = "https://api.thingspeak.com/channels/1673574/feeds.json"
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                {

                    val dataJsonArray = it.getJSONArray("feeds")
                    val dataArray = ArrayList<ReadD>()
                    for(i in 0 until dataJsonArray.length()) {
                        val dataJsonObject = dataJsonArray.getJSONObject(i)
                        val data = ReadD(
                            dataJsonObject.getString("created_at"),
                            dataJsonObject.getString("field1"),
                            dataJsonObject.getString("field2"),
                        )
                        dataArray.add(data)
                    }

                    mAdapter.updateData(dataArray)
                },
                {

                }
            )
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }

    }

