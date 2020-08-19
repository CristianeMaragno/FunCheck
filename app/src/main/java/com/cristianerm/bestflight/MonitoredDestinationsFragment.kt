package com.cristianerm.bestflight

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.fragment_monitored_destinations.view.*
import kotlinx.android.synthetic.main.fragment_monitored_destinations.*

/**
 * A simple [Fragment] subclass.
 */
class MonitoredDestinationsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_monitored_destinations, container, false)

        // Set up the toolbar.
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar_monitored_destinations)

        view.app_bar_monitored_destinations.setNavigationOnClickListener {
            Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();
        }

        view.app_bar_monitored_destinations.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_icon_more -> {
                    Toast.makeText(context, "Test item clicked", Toast.LENGTH_LONG).show();
                    true
                }
                else -> false
            }
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

}
