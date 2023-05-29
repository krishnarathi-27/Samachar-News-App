package com.example.samachar.db

import androidx.room.TypeConverter
import com.example.samachar.models.Source

class Convertors {

    @TypeConverter   //to make room know it is a type convertor
    fun fromSource(source : Source) : String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String) : Source {
        return Source(name, name)
    }
}