package services

import android.graphics.Color
import config.PropertiesConfig.Companion.ECOLE_PRIMAIRE_LOW_COUNT
import config.PropertiesConfig.Companion.ECOLE_PRIMAIRE_MEDIUM_COUNT_HIGH_SLICE
import config.PropertiesConfig.Companion.ECOLE_PRIMAIRE_MEDIUM_COUNT_LOW_SLICE


/**
 * Created by Pol on 09/02/2018.
 */

class GetColorService {
    // Méthode permettant de gérer les couleurs pour le background des items et Bitmap
    companion object {
        fun getColor(int: Int?): Int {
            if (int!! < ECOLE_PRIMAIRE_LOW_COUNT)
                return Color.GREEN
            else if (int in ECOLE_PRIMAIRE_MEDIUM_COUNT_LOW_SLICE..ECOLE_PRIMAIRE_MEDIUM_COUNT_HIGH_SLICE)
                return Color.YELLOW
            else
                return Color.RED
        }

        fun getMarkerIcon(color: Int): Float {
            val hsv = FloatArray(3)
            Color.colorToHSV(color, hsv)
            return hsv[0]
        }
    }
}
