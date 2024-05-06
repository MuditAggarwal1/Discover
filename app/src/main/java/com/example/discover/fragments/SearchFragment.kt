package com.example.discover.fragments

import android.app.DownloadManager.Query
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.discover.R
import com.example.discover.activities.DetailedActivity
import com.example.discover.activities.SearchedDataActivity
import com.example.discover.adapters.DashboardPagerAdapter
import com.example.discover.databinding.FragmentSearchBinding
import com.example.discover.utils.Constants.Companion.QUERY
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val tabTitles = arrayListOf<String>("All", "Politics", "Sports", "Education", "Technology", "Bollywood", "Games", "Entertainment")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setUpTabLayoutWithViewPager()
        searchNews()

        return binding.root
    }

    private fun searchNews() {
        binding.ivSearch.setOnClickListener {
            val intent = Intent(activity?.applicationContext, SearchedDataActivity::class.java)
            intent.putExtra(QUERY, binding.searchBar.text)
            binding.searchBar.text.clear()
            startActivity(intent)
        }
    }

    private fun setUpTabLayoutWithViewPager() {
        binding.viewPager.adapter = DashboardPagerAdapter(this, tabTitles.size)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for (i in 0..tabTitles.size) {
            val textView = LayoutInflater.from(activity?.applicationContext).inflate(R.layout.tab_title, null) as TextView
            binding.tabLayout.getTabAt(i)?.customView = textView
        }
    }
}