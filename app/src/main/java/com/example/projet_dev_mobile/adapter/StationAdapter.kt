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
import com.example.projet_dev_mobile.model.Station
import com.example.projet_dev_mobile.model.currentLocation
import com.example.projet_dev_mobile.model.stationSelected
import com.example.projet_dev_mobile.ui.station.StationMapsActivity

class StationAdapter(private val stations:List<Station>, private val context: Context ) : RecyclerView.Adapter<StationAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val name: TextView = itemView.findViewById(R.id.name)
        val status : ImageView = itemView.findViewById(R.id.status)
        val availability : TextView = itemView.findViewById(R.id.availability)
        val distance : TextView = itemView.findViewById((R.id.distance))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_station_item, parent,false)
        return ViewHolder(view)
    }

    //alimente la vue pour chaque view_item
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        holder.name.text = station.name

        if(currentLocation != null) {
            holder.distance.text = "${String.format("%.1f",currentLocation!!.distanceTo(station.toLocation())/1000)}km"
        }else{
            holder.distance.text = " - km"
        }

        holder.availability.text = "\uD83D\uDEB2${station.availableBikes} ❌${station.availableBikeStands} ✅${station.bikeStands}"
        if(0 == station.availableBikes.toInt()) {
            holder.name.setTextColor(context.getColor(R.color.red))
        } else {
            holder.name.setTextColor(context.getColor(R.color.black))
        }
        if("OPEN" == station.status) {
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_checked_green_24)
        } else {
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_checked_red_24)
        }

        holder.cardView.setOnClickListener {
            val intent = Intent(context, StationMapsActivity::class.java)

            stationSelected = station

            context.startActivity(intent)
        }
    }

    //retourne le nombre de stations à afficher
    override fun getItemCount(): Int {
        return stations.size
    }
}