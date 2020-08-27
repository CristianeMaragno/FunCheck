package com.cristianerm.bestflight

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class SectionsPageAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                HomeFragmentDestinations()
            }
            else -> {
                return HomeFragmentFavorites()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Home Fragment Destinations"
            else -> return "Home Fragment Favorites"
        }
    }


}

