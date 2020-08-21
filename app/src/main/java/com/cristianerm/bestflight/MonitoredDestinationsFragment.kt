package com.cristianerm.bestflight

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_monitored_destinations.*
import kotlinx.android.synthetic.main.fragment_monitored_destinations.view.*


/**
 * A simple [Fragment] subclass.
 */
class MonitoredDestinationsFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_monitored_destinations, container, false)

        (activity as AppCompatActivity).setSupportActionBar(app_bar_monitored_destinations)

        view.app_bar_monitored_destinations.setNavigationOnClickListener {
            Toast.makeText(context, "Test", Toast.LENGTH_LONG).show()
            drawer_layout.openDrawer(GravityCompat.START)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
