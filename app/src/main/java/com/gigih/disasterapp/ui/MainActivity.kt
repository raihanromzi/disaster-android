package com.gigih.disasterapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
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
    private lateinit var activityMainBinding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.maps_container, MapsFragment())
            commit()
        }

        with(activityMainBinding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                searchView.hide()
                Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                mainViewModel.setSearchQuery(searchView.text.toString())
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

        mainViewModel.refreshDisasterData()

        mainViewModel.disasters.observe(this) { disaster ->
            setDisasterData(disaster)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        with(activityMainBinding) {
            btnBanjir.setOnClickListener {
                mainViewModel.onButtonDisasterTypeClicked("flood")
                mainViewModel.refreshDisasterData()
            }
            btnAnginKencang.setOnClickListener {
                mainViewModel.onButtonDisasterTypeClicked("wind")
                mainViewModel.refreshDisasterData()
            }
            btnGempaBumi.setOnClickListener {
                mainViewModel.onButtonDisasterTypeClicked("earthquake")
                mainViewModel.refreshDisasterData()
            }
            btnGunungApi.setOnClickListener {
                mainViewModel.onButtonDisasterTypeClicked("volcano")
                mainViewModel.refreshDisasterData()
            }
            btnKabutAsap.setOnClickListener {
                mainViewModel.onButtonDisasterTypeClicked("haze")
                mainViewModel.refreshDisasterData()
            }
            btnKebakaranHutan.setOnClickListener {
                mainViewModel.onButtonDisasterTypeClicked("fire")
                mainViewModel.refreshDisasterData()
            }
        }
    }

    private fun setDisasterData(disasterData: List<GeometriesItem>) {
        val adapter = DisasterAdapter()
        adapter.submitList(disasterData)
        activityMainBinding.disasterListRv.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        activityMainBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}