package com.cristianerm.bestflight

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.destination_item.view.*
import kotlinx.android.synthetic.main.destinations_item.view.*

class DestinationResultsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<DestinationResultInformation> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DestinationResultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.destination_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is DestinationResultViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(destinationResultList: ArrayList<DestinationResultInformation>){
        items = destinationResultList
    }

    class DestinationResultViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val hour_go = itemView.hour_departure_and_arrival_go
        val hour_back = itemView.hour_departure_and_arrival_back
        val price = itemView.price_destination_result

        fun bind(destinationResult: DestinationResultInformation){

            hour_go.setText(destinationResult.hour_departure_and_arrival_go)
            hour_back.setText(destinationResult.hour_departure_and_arrival_back)
            price.setText(destinationResult.price)

        }

    }
}