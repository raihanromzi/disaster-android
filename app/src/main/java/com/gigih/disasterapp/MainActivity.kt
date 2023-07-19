package com.gigih.disasterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gigih.disasterapp.databinding.ActivityMainBinding
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sydney = LatLng(-33.852, 151.211)
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as SupportMapFragment

        // Google Maps
        mapFragment.getMapAsync { googleMap ->
            googleMap.addMarker {
                position(sydney)
                title("Marker in Sydney")
            }
        }
    }
}