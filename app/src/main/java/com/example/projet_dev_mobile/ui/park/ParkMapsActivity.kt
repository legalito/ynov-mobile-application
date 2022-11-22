package com.example.projet_dev_mobile.ui.park

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projet_dev_mobile.R
import com.example.projet_dev_mobile.databinding.ActivityParkMapsBinding
import com.example.projet_dev_mobile.model.allParks
import com.example.projet_dev_mobile.model.parkSelected
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ParkMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityParkMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParkMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.parkMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        parkSelected?.let { park ->
            allParks?.let {
                for (p in it) {
                    val parkLatLng = LatLng(p.latitude, p.longitude)
                    mMap.addMarker(MarkerOptions().position(parkLatLng).title(p.grpNom))
                }
            }
            val parkSelected = LatLng(park.latitude, park.longitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(parkSelected, 18f))
        }
    }
}