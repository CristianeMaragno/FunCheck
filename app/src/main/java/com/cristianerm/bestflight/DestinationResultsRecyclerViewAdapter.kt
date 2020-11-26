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

        val attraction_name = itemView.text_view_attraction_name

        fun bind(destinationResult: DestinationResultInformation){

            attraction_name.setText(destinationResult.attraction_name)

        }

    }
}