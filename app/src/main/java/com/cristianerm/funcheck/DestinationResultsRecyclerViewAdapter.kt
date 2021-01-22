package com.cristianerm.funcheck

import android.content.ContentValues
import android.content.ContentValues.TAG
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_destination.*
import kotlinx.android.synthetic.main.activity_destination.view.*
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

            val originSelected = destinationResult.origin
            val destinationSelected = destinationResult.destination
            val dateGoSelected = destinationResult.dateGo
            val dateBackSelected = destinationResult.dateBack

            attraction_name.setText(destinationResult.attraction_name)

            val destinationListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val destinationInformation = ds.getValue(DestinationInformation::class.java)

                        var origin = destinationInformation!!.origin
                        var destination = destinationInformation!!.destination
                        var dateGo = destinationInformation!!.dateGo
                        var dateBack = destinationInformation!!.dateBack

                        if (origin == originSelected && destination == destinationSelected && dateGo == dateGoSelected && dateBack == dateBackSelected) {
                            val key: String? = ds.key
                            var attractionSelected = attraction_name.text.toString()

                            if (key != null) {
                                var myRefAttractionChecked = firebaseDatabase.getReference().child(uid).child("Destinations")
                                        .child(key).child("attraction")

                                val attractionCheckedListener = object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        for (dsAttraction in dataSnapshot.children) {
                                            val attractionInformation =
                                                dsAttraction.getValue(AttractionInformation::class.java)

                                            var attraction_database = attractionInformation!!.attraction
                                            if (attraction_database == attractionSelected) {
                                                attraction_check_box.setOnCheckedChangeListener(null)
                                                attraction_check_box.isChecked = true
                                            }
                                        }

                                    }

                                    override fun onCancelled(databaseError: DatabaseError) {
                                        // Getting Post failed, log a message
                                        Log.w(
                                            ContentValues.TAG,
                                            "loadPost:onCancelled",
                                            databaseError.toException()
                                        )
                                        // ...
                                    }
                                }

                                myRefAttractionChecked.addListenerForSingleValueEvent(attractionCheckedListener)
                            }

                        } else {

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

            attraction_check_box.setOnCheckedChangeListener { buttonView, isChecked ->
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
                                var attractionSelected = attraction_name.text.toString()

                                if (key != null){
                                    var myRefAttraction = firebaseDatabase.getReference().child(uid).child("Destinations").child(key).child("attraction")

                                    val attractionListener = object : ValueEventListener {
                                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                                            var deleteAttraction = false
                                            for (dsAttraction in dataSnapshot.children) {
                                                val attractionInformation = dsAttraction.getValue(AttractionInformation::class.java)

                                                var attraction_database = attractionInformation!!.attraction
                                                if(attraction_database == attractionSelected){
                                                    val keyOldAttraction: String? = dsAttraction.key
                                                    if (keyOldAttraction != null){
                                                        Log.v(TAG, "DELETE ATTRACTION: " + keyOldAttraction)
                                                        val refDeleteAttraction = myRefAttraction.child(keyOldAttraction)
                                                        refDeleteAttraction.removeValue()
                                                        deleteAttraction = true
                                                    }
                                                }
                                            }

                                            if (deleteAttraction == false){
                                                Log.v(TAG, "CREATE ATTRACTION")
                                                val keyNewAttraction = myRefAttraction.push().key
                                                if (keyNewAttraction != null){
                                                    myRefAttraction.child(keyNewAttraction).child("attraction").setValue(attractionSelected)
                                                }
                                            }
                                        }

                                        override fun onCancelled(databaseError: DatabaseError) {
                                            // Getting Post failed, log a message
                                            Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                                            // ...
                                        }
                                    }

                                    myRefAttraction.addListenerForSingleValueEvent(attractionListener)
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
            }

        }

    }
}
