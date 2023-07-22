package com.gigih.disasterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gigih.disasterapp.databinding.ActivityMainBinding
import com.gigih.disasterapp.fragments.MapsFragment
import com.gigih.disasterapp.fragments.TestNewFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add maps fragment, and bottom sheet fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.map_fragment, MapsFragment())
            commit()
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.new_fragment, TestNewFragment())
            commit()
        }
    }
}