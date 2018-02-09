package fr.garrycity.pol.kotlintp2.views

import adapters.EcolePrimaireAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import config.PropertiesConfig.Companion.API_URL
import fr.garrycity.pol.kotlintp2.R
import models.EcolePrimaire
import providers.ParseJSONProvider
import services.*
import java.util.logging.Level

class EmptyActivity : AppCompatActivity(), IGetRequestService {
    lateinit var ecolePrimaireListView : ListView
    lateinit var ecolePrimaireAdapter : EcolePrimaireAdapter

    override fun onTaskResult(response: String?) {
        // On parse les données récupérées de la requête
        ParseJSONProvider.EcolePrimaireParse(response, EcolePrimaireService.listEcolePrimaire)
        // On met à jour la liste view
        this.ecolePrimaireAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        // On lance la requête vers l'API
        GetRequestService(this@EmptyActivity).execute(API_URL)

        // On récupère la listView
        this.ecolePrimaireListView = findViewById(R.id.ecolePrimaireList) as ListView

        // On envoie la liste à l'Adapter et on l'associe à la listView
        this.ecolePrimaireAdapter = EcolePrimaireAdapter(this@EmptyActivity, EcolePrimaireService.listEcolePrimaire)
        this.ecolePrimaireListView.adapter = this.ecolePrimaireAdapter;

        // On ajoute une listerner pour chaque item de la liste
        this.ecolePrimaireListView.setOnItemClickListener { parent, view, position, id -> switchActivity(MapsActivity::class.java, this.ecolePrimaireListView.getItemAtPosition(position) as EcolePrimaire) }
    }


    // Méthode permettant de changer de vue
    private fun switchActivity(classToGo: java.lang.Class<*>, ecoleData : EcolePrimaire?) {
        Loggable.LOG.log(Level.INFO, "OnItemClickListener : switchActivity with ecoleToFocus")

        val intent = Intent(this, classToGo)
        intent.putExtra("ecoleToFocus", ecoleData)
        startActivity(intent)
    }
}
