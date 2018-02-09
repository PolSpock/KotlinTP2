package services

import java.util.logging.Logger

/**
 * Created by Pol on 08/02/2018.
 */

// Class permettant de gérer les logs
class Loggable {
    companion object {
        val LOG = Logger.getLogger(Loggable::class.java.name)
    }
}