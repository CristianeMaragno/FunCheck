package com.cristianerm.bestflight

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_monitored_destinations.*

class MonitoredDestinationsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitored_destinations)

        setSupportActionBar(app_bar_monitored_destinations)

        app_bar_monitored_destinations.setNavigationOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        /*app_bar_monitored_destinations.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_icon_more -> {
                    Toast.makeText(this, "Test item clicked", Toast.LENGTH_LONG).show();
                    true
                }
                else -> false
            }
        }*/

        val navigationView: NavigationView = nav_view
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.main_content_frame,
                HomeFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_home_fragment)
        }

    }

    /*override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }*/

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home_fragment -> supportFragmentManager.beginTransaction().replace(
                R.id.main_content_frame,
                HomeFragment()
            ).commit()

            R.id.nav_notifications_fragment -> supportFragmentManager.beginTransaction().replace(
                R.id.main_content_frame,
                NotificationsFragment()
            ).commit()

            R.id.nav_log_out_fragment -> Toast.makeText(this, "Test nav_third_fragment clicked", Toast.LENGTH_LONG).show()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}