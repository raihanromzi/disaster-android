package com.gigih.disasterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gigih.disasterapp.databinding.ActivityMainBinding
import com.gigih.disasterapp.fragments.MapsFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.maps_container, MapsFragment())
            commit()
        }


    }
}