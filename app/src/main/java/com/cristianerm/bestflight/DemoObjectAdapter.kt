package com.cristianerm.bestflight

import android.R

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class DemoObjectAdapter(exampleList: ArrayList<HomeItem>) :
    RecyclerView.Adapter<DemoObjectAdapter.DemoObjectAdapterViewHolder>() {
    private val mHomeList: ArrayList<HomeItem>

    class DemoObjectAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView_destinations: TextView
        var textView_date_go: TextView
        var textView_date_back: TextView

        init {
            textView_destinations = itemView.findViewById(R.id.text_destination_home)
            textView_date_go = itemView.findViewById(R.id.text_date_go_home)
            textView_date_back = itemView.findViewById(R.id.text_date_back_home)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DemoObjectAdapterViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return DemoObjectAdapterViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: DemoObjectAdapterViewHolder,
        position: Int
    ) {
        val currentItem: HomeItem = mHomeList[position]
        holder.textView_destinations.setText(currentItem.destination)
        holder.textView_date_go.setText(currentItem.date_go)
        holder.textView_date_back.setText(currentItem.date_back)
    }

    override fun getItemCount(): Int {
        //return mHomeList.size()
    }

    init {
        mHomeList = exampleList
    }
}