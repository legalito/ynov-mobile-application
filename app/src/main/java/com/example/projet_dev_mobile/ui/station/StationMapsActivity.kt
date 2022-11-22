package com.example.projet_dev_mobile.ui.station

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projet_dev_mobile.R
import com.example.projet_dev_mobile.databinding.ActivityStationMapsBinding
import com.example.projet_dev_mobile.model.allStations
import com.example.projet_dev_mobile.model.stationSelected
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class StationMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityStationMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStationMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        stationSelected?.let { station ->
            allStations?.let {
                for (st in it) {
                    val stationLatLng = LatLng(st.latitude, st.longitude)
                    mMap.addMarker(MarkerOptions().position(stationLatLng).title(st.name))
                }
            }
            val stationSelected = LatLng(station.latitude, station.longitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(stationSelected, 18f))
        }
    }
}