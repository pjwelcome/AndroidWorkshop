package com.multimeleon.androidworkshop


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.animal_list_item.view.*


class AnimalAdapter(val items: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.animal_list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvAnimalType.text = items.get(position)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.tv_animal_type
}

//class DashboardAdapter(private val plants: List<Plant>, private val clickListener: (Plant) -> Unit) : RecyclerView.Adapter<PlantDashboardViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantDashboardViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.dashboard_plant_item, parent, false)
//        return PlantDashboardViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: PlantDashboardViewHolder, position: Int) {
//        holder.bind(plants[position], clickListener)
//    }
//
//    override fun getItemCount(): Int = plants.size
//}
//
//class PlantDashboardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//
//    fun bind(data: Plant, clickListener: (Plant) -> Unit) {
//
//        view.plantNameTextView.text = data.name
//        view.setOnClickListener { clickListener(data) }
//    }
//}