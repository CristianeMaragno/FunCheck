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
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


class NotificationsFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radio_group_notifications.setOnCheckedChangeListener( RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val selectedRadioButtonId: Int = radio_group_notifications.checkedRadioButtonId
            var radioButtonId = resources.getResourceEntryName(selectedRadioButtonId)
            Log.v(ContentValues.TAG, radioButtonId)

            writeNotificationStatusOnDatabase()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        return view
    }


    private fun writeNotificationStatusOnDatabase(){
        
    }

}