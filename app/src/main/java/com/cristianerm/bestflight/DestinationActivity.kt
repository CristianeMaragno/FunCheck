package com.cristianerm.bestflight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

class DestinationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)

        val destination = intent.getStringExtra("destination")
        val date_go = intent.getStringExtra("date go")
        val date_back = intent.getStringExtra("date back")
        Toast.makeText(this, "Value of destination: " + destination, Toast.LENGTH_LONG).show()

    }
}
