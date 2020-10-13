package com.cristianerm.bestflight

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
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
                button_favorite.setImageResource(R.drawable.ic_favorite)
            })

            button_notifications.setOnClickListener(View.OnClickListener {
                button_notifications.setImageResource(R.drawable.ic_notifications_colored)
            })

            itemView.setOnClickListener { view ->
                //val item_text = destination.text
                //val position = itemView.verticalScrollbarPosition //Doesn't work, just 0
                Log.v("AdapterRecyclerView", "item clicked")
                /*val intent = Intent(activity, AddDestinationActivity::class.java)
                startActivity(intent)*/

                val intent = Intent(view.getContext(), DestinationActivity::class.java)
                view.getContext().startActivity(intent)

            }

        }

    }
}