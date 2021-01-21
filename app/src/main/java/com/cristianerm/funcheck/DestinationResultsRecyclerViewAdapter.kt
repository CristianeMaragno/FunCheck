package com.cristianerm.funcheck

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.destination_item.view.*

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

        val attraction_check_box = itemView.check_box_destination_item
        val attraction_name = itemView.text_view_destination_item

        fun bind(destinationResult: DestinationResultInformation){
            var auth= FirebaseAuth.getInstance()
            var firebaseDatabase= FirebaseDatabase.getInstance()
            var firebaseUser = auth.currentUser!!

            val uid = firebaseUser.uid
            var myRef = firebaseDatabase.getReference().child(uid).child("Destinations")

            attraction_name.setText(destinationResult.attraction_name)

            attraction_check_box.setOnCheckedChangeListener { buttonView, isChecked ->
                var attractionSelected = attraction_name.text.toString()
                if(isChecked){
                    //myRef.child(attractionId).child("attraction").setValue(attractionSelected)
                    Log.v("ResultsRecyclerView", attractionSelected + " was selected")
                }else{
                    Log.v("ResultsRecyclerView", attractionSelected + " was deselected")
                }
            }

        }

    }
}
