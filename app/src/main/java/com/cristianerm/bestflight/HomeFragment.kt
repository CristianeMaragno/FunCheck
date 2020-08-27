package com.cristianerm.bestflight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        
        var fragmentAdapter = SectionsPageAdapter(childFragmentManager)
        view_pager_home.adapter = fragmentAdapter

        main_tabs.setupWithViewPager(view_pager_home)

        return view
    }



}