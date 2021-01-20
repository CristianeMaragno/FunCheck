package com.cristianerm.funcheck

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


class NotificationsFragment : Fragment() {

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference()

    private lateinit var auth: FirebaseAuth

    var notificationStatus = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        auth = FirebaseAuth.getInstance()

        view.save_button_notifications.setOnClickListener({
            writeNotificationStatusOnDatabase()
        })

        return view
    }


    private fun writeNotificationStatusOnDatabase(){
        val selectedRadioButtonId: Int = radio_group_notifications.checkedRadioButtonId
        var radioButtonId = resources.getResourceEntryName(selectedRadioButtonId)

        if (radioButtonId == "enable_all_radio_button_notifications"){
            notificationStatus = "0"
        }else if(radioButtonId == "enable_favorite_radio_button_notifications"){
            notificationStatus = "1"
        }else if(radioButtonId == "disable_notifications_radio_button_notifications"){
            notificationStatus = "2"
        }

        val firebaseUser = this.auth.currentUser!!
        val uid = firebaseUser.uid

        myRef.child(uid).child("Notification").child("notification").setValue(notificationStatus)
    }

}