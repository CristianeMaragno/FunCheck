package com.cristianerm.funcheck

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object DatabaseDestinationKey {

    fun getDatabaseResult(originSelected: String, destinationSelected: String, dateGoSelected: String, dateBackSelected: String): String {

        var auth = FirebaseAuth.getInstance()
        var firebaseDatabase = FirebaseDatabase.getInstance()
        var firebaseUser = auth.currentUser!!

        val uid = firebaseUser.uid
        var myRef = firebaseDatabase.getReference().child(uid).child("Destinations")

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

                        if (key != null) {
                            Log.v("DatabaseDestinationKey", "RESULT: " + key)
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

        return "Ok getDatabaseResult function"

    }
}