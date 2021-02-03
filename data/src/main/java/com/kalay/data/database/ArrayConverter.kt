package com.kalay.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kalay.data.database.model.MovieCardDbDTO

class ArrayConverter {

    @TypeConverter
    fun fromContent(movieCardDbDTO: MovieCardDbDTO?): String? {
        if (movieCardDbDTO == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<MovieCardDbDTO?>() {}.type
        return gson.toJson(movieCardDbDTO, type)
    }

}