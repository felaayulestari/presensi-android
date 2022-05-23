package com.example.rekapresensionline.views.Presensi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rekapresensionline.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PresensiFragment : Fragment(), OnMapReadyCallback {

    private var mapPresensi: SupportMapFragment? = null
    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_presensi, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMaps()
    }

    private fun setupMaps() {
        mapPresensi = childFragmentManager.findFragmentById(R.id.map_presensi) as SupportMapFragment
        mapPresensi?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        //Coordinate Bisa di ganti sesuai tempat
        val indo = LatLng(-7.94767195143, 112.617200129)
        map?.addMarker(
            MarkerOptions()
                .position(indo)
                .title("Marker in Malang")
        )
        map?.moveCamera(CameraUpdateFactory.newLatLng(indo))
        map?.animateCamera(CameraUpdateFactory.zoomTo(20f))
    }
}