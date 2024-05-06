package com.example.discover.db

import androidx.room.TypeConverter
import com.example.discover.models.Source

class Convertors {

    @TypeConverter
    fun fromSourceToString(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun fromStringToSource(name: String) : Source {
        return Source(name, name)
    }
}