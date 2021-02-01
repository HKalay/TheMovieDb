package com.kalay.themoviedb.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kalay.data.response.dataclasses.Results

class ArrayConverter {

    @TypeConverter
    fun fromResults(results: Results?): String? {
        if (results == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<Results?>() {}.type
        return gson.toJson(results, type)
    }
}