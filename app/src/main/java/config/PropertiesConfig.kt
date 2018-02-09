package config

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Pol on 09/02/2018.
 */

class PropertiesConfig {

    // Liste des propriétés
    companion object {
        val ECOLE_PRIMAIRE_LOW_COUNT: Int = 150
        val ECOLE_PRIMAIRE_MEDIUM_COUNT_LOW_SLICE: Int = 151
        val ECOLE_PRIMAIRE_MEDIUM_COUNT_HIGH_SLICE: Int = 299
        val API_URL : String = "http://10.69.0.191:8080/getEcolePrimaire"

        val DEFAULT_MAP_LATLNG : LatLng = LatLng(45.74102884691749,4.83206175500527)
        val DEFAULT_MAP_ZOOM : Float = 12f

    }


}
