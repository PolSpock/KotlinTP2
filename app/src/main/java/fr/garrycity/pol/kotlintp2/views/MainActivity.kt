package fr.garrycity.pol.kotlintp2.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import fr.garrycity.pol.kotlintp2.R
import services.Loggable
import java.util.logging.Level

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // On récupère les éléments de l'activité
        val buttonToEmpty: Button = findViewById(R.id.buttonToEmpty) as Button
        val buttonToGoogleMaps: Button = findViewById(R.id.buttonToGoogleMap) as Button

        // On créée des listeners pour ces boutons {
        buttonToEmpty.setOnClickListener{switchActivity(EmptyActivity::class.java)}
        buttonToGoogleMaps.setOnClickListener{switchActivity(MapsActivity::class.java)}
    }

    // Méthode permettant de changer de vue
    private fun switchActivity(classToGo: java.lang.Class<*>) {
        Loggable.LOG.log(Level.INFO, "OnClickListener : switchActivity to " + classToGo.toString())

        val intent = Intent(this, classToGo)
        startActivity(intent)
    }
}
