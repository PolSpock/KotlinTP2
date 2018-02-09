package fr.garrycity.pol.kotlintp2.views

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import config.PropertiesConfig.Companion.DEFAULT_MAP_LATLNG
import config.PropertiesConfig.Companion.DEFAULT_MAP_ZOOM
import fr.garrycity.pol.kotlintp2.R
import models.EcolePrimaire
import services.EcolePrimaireService
import services.GetColorService

class MapsActivity : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    // Méthode permettant de cibler la map en fonction d'une latitude et d'une longitude
    private fun focusToMap(latitude: Double, longitude: Double) {
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(latitude, longitude)))
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (ecole: EcolePrimaire in EcolePrimaireService.listEcolePrimaire) {
            mMap!!.addMarker(MarkerOptions().position(LatLng(ecole.latitude!!, ecole.longitude!!)).title(ecole.nom).snippet(ecole.addresse).icon(BitmapDescriptorFactory.defaultMarker(GetColorService.getMarkerIcon(GetColorService.getColor(ecole.nbEleve)))))
        }

        // Permet d'effectuer un ciblage sur le marker au clic
        // C'est normalement réalisé par défaut mais la spec propose de le faire
        mMap!!.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                mMap!!.moveCamera(CameraUpdateFactory.newLatLng(marker.position))
                return false
            }
        })

        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(DEFAULT_MAP_LATLNG))
        mMap!!.moveCamera(CameraUpdateFactory.zoomTo(DEFAULT_MAP_ZOOM))

        if (intent.hasExtra("ecoleToFocus")) {
            val ecoleToFocus: EcolePrimaire = intent.getSerializableExtra("ecoleToFocus") as EcolePrimaire
            focusToMap(ecoleToFocus.latitude!!, ecoleToFocus.longitude!!)
        }
    }
}
