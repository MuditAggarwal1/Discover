package com.example.discover.utils

import com.example.discover.models.Article

interface OnNewsClickListener {
    fun onNewsItemClickListener(position: Int, article: Article)
}