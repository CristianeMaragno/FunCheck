package com.cristianerm.funcheck

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_monitored_destinations.*

const val ARG_OBJECT = "object"

class HomeFragment : Fragment() {

    private lateinit var destinationsTabsPagerAdapter: DestinationsTabsPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monitored_destinations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        destinationsTabsPagerAdapter = DestinationsTabsPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager_home)
        viewPager.adapter = destinationsTabsPagerAdapter

        tab_layout_home.setupWithViewPager(viewPager)

        //You tab icons
        val icons = intArrayOf(
            R.drawable.ic_all,
            R.drawable.ic_favorite
        )

        for (i in 0 until 2) {
            tab_layout_home.getTabAt(i)?.setIcon(icons[i])
        }

        floating_action_button_add_destination.setOnClickListener {
            val intent = Intent(activity, AddDestinationActivity::class.java)
            startActivity(intent)
        }

    }

}