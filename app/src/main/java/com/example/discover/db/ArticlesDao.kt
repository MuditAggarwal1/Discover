package com.example.discover.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.discover.models.Article

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getArticle() : LiveData<List<Article>>

    @Query("DELETE FROM articles WHERE id = :id")
    suspend fun deleteArticle(id: Long)
}