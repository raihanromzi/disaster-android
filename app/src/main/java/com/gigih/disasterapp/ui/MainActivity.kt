package com.gigih.disasterapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gigih.disasterapp.R
import com.gigih.disasterapp.data.remote.response.GeometriesItem
import com.gigih.disasterapp.data.remote.response.Response
import com.gigih.disasterapp.data.remote.retrofit.ApiConfig
import com.gigih.disasterapp.databinding.ActivityMainBinding
import com.gigih.disasterapp.ui.adapter.DisasterAdapter
import retrofit2.Call


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
        private const val START = "2017-12-04T00%3A00%3A00%2B0700"
        private const val END = "2017-12-06T05%3A00%3A00%2B0700"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.maps_container, MapsFragment())
            commit()
        }


        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                searchView.hide()
                Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                false
            }
            searchBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.setting_button -> {
                        //intent to move to setting activity
                        val move = Intent(this@MainActivity, SettingActivity::class.java)
                        startActivity(move)
                        true
                    }

                    else -> false
                }
            }

        }

        supportActionBar?.hide()

        // set recyclerview
        val layoutManager = LinearLayoutManager(this@MainActivity)
        binding.disasterListRv.layoutManager = layoutManager

        // add divider
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.disasterListRv.addItemDecoration(itemDecoration)

        getDisasterData()

    }

    private fun getDisasterData() {
        val client = ApiConfig.getApiService().getDisaster(START, END)

        client.enqueue(object : retrofit2.Callback<Response> {
            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                if (response.isSuccessful) {
                    val responseAPI = response.body()
                    if (responseAPI != null) {
                        Log.d(TAG, "onResponse: $responseAPI")
                        setDisasterData(responseAPI.result?.objects?.output?.geometries)
                        setMarkerOnMap(33.8688, 151.2093, "Hello")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    private fun setDisasterData(disasterData: List<GeometriesItem?>?) {
        val adapter = DisasterAdapter()
        adapter.submitList(disasterData)
        binding.disasterListRv.adapter = adapter
    }

    // set market on map fragment
    private fun setMarkerOnMap(latitude: Double, longitude: Double, title: String) {
        val mapsFragment =
            supportFragmentManager.findFragmentById(R.id.maps_container) as MapsFragment?
        mapsFragment?.updateMapWithLocation(latitude, longitude, title)
    }


}