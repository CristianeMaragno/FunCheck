package com.cristianerm.bestflight

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_destinations.*
import kotlinx.android.synthetic.main.fragment_add_destinations.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class AddDestinationFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_destinations, container, false)

        view.close_add_destination.setOnClickListener({
            val intent = Intent(activity, MainFunctionalitiesActivity::class.java)
            startActivity(intent)
        })
        return view
    }
}