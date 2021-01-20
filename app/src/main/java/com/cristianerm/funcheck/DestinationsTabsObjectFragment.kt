package com.cristianerm.funcheck

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_destinations.*
import java.util.*
import kotlin.collections.ArrayList

class DestinationsTabsObjectFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var destinationsTabsRecyclerViewAdapter: DestinationsTabsRecyclerViewAdapter
    var list = ArrayList<DestinationsInformation>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseUser = this.auth.currentUser!!

        return inflater.inflate(R.layout.fragment_destinations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {

            var tab_page = getInt(ARG_OBJECT).toString()

            if (tab_page == "1"){

                initRecyclerView()
                addDataSet()

            }else{
                initRecyclerView()
                addDataSetFavorited()
            }


        }
    }

    private fun addDataSet(){
        val uid = firebaseUser.uid
        myRef = firebaseDatabase.getReference().child(uid).child("Destinations")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list.clear()

                for (ds in dataSnapshot.children) {
                    val destinationInformation = ds.getValue(DestinationInformation::class.java)

                    var origin = destinationInformation!!.origin
                    var destination = destinationInformation!!.destination
                    var dateGo = destinationInformation!!.dateGo
                    var dateBack = destinationInformation!!.dateBack
                    var favorited = destinationInformation!!.favorited

                    Log.v(ContentValues.TAG, "DESTINATION: " + origin)
                    Log.v(ContentValues.TAG, "DESTINATION: " + destination)
                    Log.v(ContentValues.TAG, "DESTINATION: " + dateGo)
                    Log.v(ContentValues.TAG, "DESTINATION: " + dateBack)

                    if (favorited == "0"){
                        list.add(DestinationsInformation(origin, destination, dateGo, dateBack))
                    }
                }
                list.reverse()
                destinationsTabsRecyclerViewAdapter.notifyDataSetChanged()
                destinationsTabsRecyclerViewAdapter.submitList(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        myRef.addValueEventListener(postListener)

    }

    private fun addDataSetFavorited(){
        val uid = firebaseUser.uid
        myRef = firebaseDatabase.getReference().child(uid).child("Destinations")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list.clear()

                for (ds in dataSnapshot.children) {
                    val destinationInformation = ds.getValue(DestinationInformation::class.java)

                    var origin = destinationInformation!!.origin
                    var destination = destinationInformation!!.destination
                    var dateGo = destinationInformation!!.dateGo
                    var dateBack = destinationInformation!!.dateBack
                    var favorited = destinationInformation!!.favorited

                    Log.v(ContentValues.TAG, "DESTINATION: " + origin)
                    Log.v(ContentValues.TAG, "DESTINATION: " + destination)
                    Log.v(ContentValues.TAG, "DESTINATION: " + dateGo)
                    Log.v(ContentValues.TAG, "DESTINATION: " + dateBack)

                    if (favorited == "1"){
                        list.add(DestinationsInformation(origin, destination, dateGo, dateBack))
                    }
                }
                list.reverse()
                destinationsTabsRecyclerViewAdapter.notifyDataSetChanged()
                destinationsTabsRecyclerViewAdapter.submitList(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        myRef.addValueEventListener(postListener)

    }

    private fun initRecyclerView(){

        recyclerView_home.apply {
            layoutManager = LinearLayoutManager(context)
            destinationsTabsRecyclerViewAdapter = DestinationsTabsRecyclerViewAdapter()
            adapter = destinationsTabsRecyclerViewAdapter
        }
    }
}