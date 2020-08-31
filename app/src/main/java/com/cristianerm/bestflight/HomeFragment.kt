package com.cristianerm.bestflight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_home.*

const val ARG_OBJECT = "object"

class HomeFragment : Fragment() {

    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager_home)
        viewPager.adapter = demoCollectionPagerAdapter

        tab_layout_home.setupWithViewPager(viewPager)

        //You tab icons

        //You tab icons
        val icons = intArrayOf(
            R.drawable.ic_airplane,
            R.drawable.ic_favorite
        )

        for (i in 0 until 2) {
            tab_layout_home.getTabAt(i)?.setIcon(icons[i])
        }
    }

}