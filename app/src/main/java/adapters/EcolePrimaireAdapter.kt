package adapters

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import fr.garrycity.pol.kotlintp2.R
import models.EcolePrimaire
import services.GetColorService

/**
 * Created by Pol on 02/02/2018.
 */

class EcolePrimaireAdapter(var activity: Activity, listEcolePrimaire: ArrayList<EcolePrimaire>) : BaseAdapter() {
    private val context: Context
    private val listEcolePrimaire : List<EcolePrimaire>

    init {
        this.context = activity
        this.listEcolePrimaire = listEcolePrimaire
    }

    override fun getItem(position: Int): Any {
        return listEcolePrimaire.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listEcolePrimaire.size
    }

    private class ViewHolder(row: View?) {
        var layout: LinearLayout? = null
        var txtNom: TextView? = null
        var txtAddresse: TextView? = null
        var txtNbEleve: TextView? = null

        init {
            this.layout = row?.findViewById(R.id.ecolePrimaireLayout) as LinearLayout
            this.txtNom = row?.findViewById(R.id.ecolePrimaireNom) as TextView
            this.txtAddresse = row?.findViewById(R.id.ecolePrimaireAddresse) as TextView
            this.txtNbEleve = row?.findViewById(R.id.ecolePrimaireNbEleve) as TextView
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_ecole_primaire, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val ecolePrimaire = listEcolePrimaire[position]

        viewHolder.txtNom?.text = ecolePrimaire.nom
        viewHolder.txtAddresse?.text = ecolePrimaire.addresse
        viewHolder.txtNbEleve?.text = ecolePrimaire.nbEleve.toString()

        viewHolder.layout?.setBackgroundColor(GetColorService.getColor(ecolePrimaire.nbEleve).toInt())

        return view as View
    }

}
