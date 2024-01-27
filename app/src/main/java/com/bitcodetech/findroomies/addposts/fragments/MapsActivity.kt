package com.bitcodetech.findroomies.addposts.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bitcodetech.findroomies.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maps_activity)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val initialLocation = LatLng(18.5018, 73.8636) // Replace with your desired initial location's latitude and longitude
        val zoomLevel = 15f // Adjust the zoom level as needed
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, zoomLevel))

        // Enable zoom controls
        googleMap.uiSettings.isZoomControlsEnabled = true

        googleMap.setOnMapClickListener { latLng ->
            // Handle the map click event
            // Add a marker at the clicked position
            googleMap.clear() // Clear previous markers
            googleMap.addMarker(MarkerOptions().position(latLng))
            // Return the selected latitude and longitude to the calling fragment
            val resultIntent = Intent()
            resultIntent.putExtra("latitude", latLng.latitude)
            resultIntent.putExtra("longitude", latLng.longitude)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}