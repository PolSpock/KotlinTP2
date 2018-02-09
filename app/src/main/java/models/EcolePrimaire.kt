package models

import java.io.Serializable

/**
 * Created by Pol on 02/02/2018.
 */

class EcolePrimaire(nom: String?, addresse: String?, nbEleve: Int?, status: String?, latitute: Double?, longitude: Double?) : Serializable {

    var nom: String? = null
    var addresse: String? = null
    var nbEleve: Int? = null
    var status: String? = null
    var latitude: Double? = null
    var longitude: Double? = null

    init {
        this.nom = nom;
        this.addresse = addresse;
        this.nbEleve = nbEleve;
        this.status = status;
        this.latitude = latitute;
        this.longitude = longitude;
    }

}
