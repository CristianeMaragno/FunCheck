package com.cristianerm.bestflight

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_sign_up.view.*

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        view.sign_up_cadastrate_button.setOnClickListener({
            val intent = Intent(activity, MonitoredDestinationsActivity::class.java)
            startActivity(intent)
        })

        return view
    }

}
