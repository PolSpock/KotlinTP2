package providers

import android.content.Context
import android.content.ContextWrapper
import models.EcolePrimaire
import org.json.JSONArray
import org.json.JSONException
import services.Loggable
import java.util.*
import java.util.logging.Level

/**
 * Created by Pol on 11/04/2017.
 */

class ParseJSONProvider {

    // Méthode static permettant de parser les données de l'API
    companion object {
        fun EcolePrimaireParse(stringJSON: String?, listEcolePrimaire: ArrayList<EcolePrimaire>) {
            listEcolePrimaire.clear()
            try {
                val mainObject = JSONArray(stringJSON)
                for (i in 0 until mainObject.length()) {
                    try {
                        val ecoleObject = mainObject.getJSONObject(i)

                        val itemNom = ecoleObject.getString("nom")
                        val itemAddresse = ecoleObject.getString("addresse")
                        val itemNbEleve = ecoleObject.getInt("nbEleve")
                        val itemStatus = ecoleObject.getString("status")
                        val itemLatitude = ecoleObject.getDouble("latitude")
                        val itemLongitude = ecoleObject.getDouble("longitude")

                        // On ajout les informations dans une object EcolePrimaire qui est directement ajouté dans la liste
                        listEcolePrimaire.add(EcolePrimaire(itemNom, itemAddresse, itemNbEleve, itemStatus, itemLatitude, itemLongitude))

                    } catch (e: JSONException) {
                        Loggable.LOG.log(Level.SEVERE, "ParseJSONProvider : " + e.message)
                    }
                }
            } catch (e: JSONException) {
                Loggable.LOG.log(Level.SEVERE, "ParseJSONProvider : " + e.message)
            }

        }
    }
}
