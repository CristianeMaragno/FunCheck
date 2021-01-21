package com.cristianerm.funcheck

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
        val origin = itemView.text_origin_home
        val destination = itemView.text_destination_home
        val date_go = itemView.text_date_go_home
        val date_back = itemView.text_date_back_home

        val button_favorite = itemView.favorite_button_home

        fun bind(destinationsHome: DestinationsInformation){
            var auth= FirebaseAuth.getInstance()
            var firebaseDatabase= FirebaseDatabase.getInstance()
            var firebaseUser = auth.currentUser!!

            val uid = firebaseUser.uid
            var myRef = firebaseDatabase.getReference().child(uid).child("Destinations")

            origin.setText(destinationsHome.origin)
            destination.setText(destinationsHome.destination)
            date_go.setText(destinationsHome.date_go)
            date_back.setText(destinationsHome.date_back)

            var originSelected = origin.text.toString()
            var destinationSelected = destination.text.toString()
            var dateGoSelected = date_go.text.toString()
            var dateBackSelected = date_back.text.toString()

            val destinationListenerFavorited = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val destinationInformationFavorited = ds.getValue(DestinationInformation::class.java)
                        var favoritedBefore = destinationInformationFavorited!!.favorited
                        Log.v("AdapterRecyclerView", "FAVORITED BEFORE: " + favoritedBefore)

                        var origin = destinationInformationFavorited!!.origin
                        var destination = destinationInformationFavorited!!.destination
                        var dateGo = destinationInformationFavorited!!.dateGo
                        var dateBack = destinationInformationFavorited!!.dateBack

                        if (origin == originSelected && destination == destinationSelected && dateGo == dateGoSelected && dateBack == dateBackSelected){
                            if(favoritedBefore == "1"){
                                button_favorite.setImageResource(R.drawable.ic_favorite)
                            }else{
                                button_favorite.setImageResource(R.drawable.ic_favorite_none)
                            }
                        }

                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                    // ...
                }
            }

            myRef.addListenerForSingleValueEvent(destinationListenerFavorited)

            button_favorite.setOnClickListener(View.OnClickListener {
                val destinationListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (ds in dataSnapshot.children) {
                            val destinationInformation = ds.getValue(DestinationInformation::class.java)

                            var origin = destinationInformation!!.origin
                            var destination = destinationInformation!!.destination
                            var dateGo = destinationInformation!!.dateGo
                            var dateBack = destinationInformation!!.dateBack

                            if (origin == originSelected && destination == destinationSelected && dateGo == dateGoSelected && dateBack == dateBackSelected){

                                val key: String? = ds.key
                                var favorited = destinationInformation!!.favorited
                                Log.v("AdapterRecyclerView", "FAVORITED: " + favorited)
                                if(favorited.equals("0")){
                                    if (key != null) {
                                        myRef.child(key).child("favorited").setValue("1")
                                    }
                                }else{
                                    if(key != null) {
                                        myRef.child(key).child("favorited").setValue("0")
                                    }
                                }
                            }else{

                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                        // ...
                    }
                }

                myRef.addListenerForSingleValueEvent(destinationListener)
            })


            itemView.setOnClickListener { view ->
                val origin_text = origin.text
                val destination_text = destination.text
                val date_go_text = date_go.text
                val date_back_text = date_back.text

                Log.v("AdapterRecyclerView", "item clicked")

                val intent = Intent(view.getContext(), DestinationActivity::class.java)
                intent.putExtra("origin", origin_text)
                intent.putExtra("destination", destination_text)
                intent.putExtra("date go", date_go_text)
                intent.putExtra("date back", date_back_text)
                view.getContext().startActivity(intent)
            }

        }

    }
}