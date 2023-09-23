package com.example.discover.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

object ViewPagerTransition {
        fun transitions(viewPager: ViewPager2) {
            viewPager.clipToPadding = false
            viewPager.clipChildren = false
            viewPager.offscreenPageLimit = 3
            viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.90f + r * 0.10f
            })
            viewPager.setPageTransformer(compositePageTransformer)
        }
}