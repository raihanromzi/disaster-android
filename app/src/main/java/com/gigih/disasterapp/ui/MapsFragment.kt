package com.gigih.disasterapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gigih.disasterapp.R
import com.gigih.disasterapp.data.remote.response.GeometriesItem
import com.gigih.disasterapp.ui.viewmodel.MainViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val boundsBuilder = LatLngBounds.Builder()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


    private fun addDisasterMarker(map: GoogleMap, disasters: List<GeometriesItem>) {
        map.clear()

        disasters.forEach { disaster ->
            val lat = disaster.coordinates[1].toString().toDouble()
            val lon = disaster.coordinates[0].toString().toDouble()

            val latLng = LatLng(lat, lon)
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(disaster.properties.title)
                    .snippet(disaster.properties.createdAt)
            )
            boundsBuilder.include(latLng)
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        mainViewModel.disasters.observe(this) { disasters ->
            addDisasterMarker(googleMap, disasters)
        }
    }


}