package com.gigih.disasterapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gigih.disasterapp.R
import com.gigih.disasterapp.data.remote.response.GeometriesItem
import com.gigih.disasterapp.databinding.ActivityMainBinding
import com.gigih.disasterapp.ui.adapter.DisasterAdapter
import com.gigih.disasterapp.ui.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
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

        mainViewModel.disasters.observe(this) { disaster ->
            setDisasterData(disaster)
        }

    }

    private fun setDisasterData(disasterData: List<GeometriesItem>) {
        val adapter = DisasterAdapter()
        adapter.submitList(disasterData)
        binding.disasterListRv.adapter = adapter
    }


}