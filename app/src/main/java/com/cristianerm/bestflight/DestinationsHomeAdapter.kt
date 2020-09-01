package com.cristianerm.bestflight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_item.view.*

class DestinationsHomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"

    private var items: List<DestinationsHome> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DestinationsHomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
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

    fun submitList(blogList: List<DestinationsHome>){
        items = blogList
    }

    class DestinationsHomeViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val destination = itemView.text_destination_home
        val date_go = itemView.text_date_go_home
        val date_back = itemView.text_date_back_home

        fun bind(destinationsHome: DestinationsHome){

            destination.setText(destinationsHome.destination)
            date_go.setText(destinationsHome.date_go)
            date_back.setText(destinationsHome.date_back)

        }

    }

}