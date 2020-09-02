package com.cristianerm.bestflight

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.destinations_item.view.*

class DestinationsTabsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<DestinationsInformation> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DestinationsHomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.destinations_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is DestinationsHomeViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(destinationsList: List<DestinationsInformation>){
        items = destinationsList
    }

    class DestinationsHomeViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val destination = itemView.text_destination_home
        val date_go = itemView.text_date_go_home
        val date_back = itemView.text_date_back_home

        val button_favorite = itemView.favorite_button_home
        val button_notifications = itemView.notifications_button_home

        fun bind(destinationsHome: DestinationsInformation){

            destination.setText(destinationsHome.destination)
            date_go.setText(destinationsHome.date_go)
            date_back.setText(destinationsHome.date_back)

            button_favorite.setOnClickListener(View.OnClickListener {
                Log.d("DestinationsAdapter", "button_favorite clicked at position " + adapterPosition)
            })

            button_notifications.setOnClickListener(View.OnClickListener {
                Log.d("DestinationsAdapter", "button_notifications clicked at position " + adapterPosition)
            })

        }

    }

}