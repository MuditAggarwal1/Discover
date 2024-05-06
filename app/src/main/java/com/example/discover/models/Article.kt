package com.example.discover.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)