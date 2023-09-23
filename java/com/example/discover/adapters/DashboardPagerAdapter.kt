package com.example.discover.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.discover.fragments.CategoryFragment

class DashboardPagerAdapter(fragment: Fragment, private val listSize: Int) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return listSize
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CategoryFragment("india")
            1 -> CategoryFragment("politic")
            2 -> CategoryFragment("sport")
            3 -> CategoryFragment("education")
            4 -> CategoryFragment("technology")
            5 -> CategoryFragment("bollywood")
            6 -> CategoryFragment("games")
            7 -> CategoryFragment("entertainment")
            else -> CategoryFragment("all")
        }
    }
}