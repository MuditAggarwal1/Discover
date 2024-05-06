package com.example.discover.db

import android.content.Context
import androidx.room.*
import com.example.discover.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticlesDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null
        fun getDatabase(context: Context) : ArticleDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                        ArticleDatabase::class.java,
                        "articleDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}