package com.example.projet_dev_mobile.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_dev_mobile.R
import com.example.projet_dev_mobile.model.Park
import com.example.projet_dev_mobile.model.currentLocation
import com.example.projet_dev_mobile.model.parkSelected
import com.example.projet_dev_mobile.ui.park.ParkMapsActivity

class ParkAdapter(private val parks:List<Park>, private val context: Context) : RecyclerView.Adapter<ParkAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val name: TextView = itemView.findViewById(R.id.name)
        val disponibilite: TextView = itemView.findViewById(R.id.disponibilite)
        val distance : TextView = itemView.findViewById((R.id.distance))
        val parkStatus : ImageView = itemView.findViewById((R.id.parkStatus))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_parking_item, parent,false)
        return ViewHolder(view)
    }

    //alimente la vue pour chaque view_item
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val park = parks[position]
        holder.name.text = park.grpNom
        holder.disponibilite.text = park.grpDisponible.toString()
        if(currentLocation != null) {
            holder.distance.text = "${String.format("%.1f", currentLocation!!.distanceTo(park.toLocation())/1000)}km"
        }else{
            holder.distance.text = " - km"
        }
        holder.cardView.setOnClickListener {
            val intent = Intent(context, ParkMapsActivity::class.java)

            parkSelected = park

            context.startActivity(intent)
        }
        if(park.grpDisponible == 0) {
            holder.parkStatus.setImageResource(R.drawable.ic_baseline_local_parking_red_24)
        } else {
            holder.parkStatus.setImageResource(R.drawable.ic_baseline_local_parking_blue_24)
        }
    }

    //retourne le nombre de stations à afficher
    override fun getItemCount(): Int {
        return parks.size
    }
}