package com.cristianerm.bestflight

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GravityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main_functionalities.*

class MainFunctionalitiesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_functionalities)

        setSupportActionBar(app_bar_monitored_destinations)

        app_bar_monitored_destinations.setNavigationOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        app_bar_monitored_destinations.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_icon_more -> {
                    Toast.makeText(this, "Test item clicked", Toast.LENGTH_LONG).show()
                    val menuItemView: View = findViewById(R.id.menu_icon_more)
                    showPopup(menuItemView)
                    true
                }
                else -> false
            }
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
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

    private fun showPopup(view: View) {
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.pop_up_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.pop_up_menu_change_password -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.main_content_frame,
                        ChangePasswordFragment()
                    ).commit()
                }

                R.id.pop_up_menu_delete_account -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle(resources.getString(R.string.confirmation))
                        .setMessage(resources.getString(R.string.supporting_text_delete_account))
                        .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                            // Respond to negative button press
                        }
                        .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                            // Respond to positive button press
                        }
                        .show()
                }
            }

            true
        })

        popup.show()
    }

}
