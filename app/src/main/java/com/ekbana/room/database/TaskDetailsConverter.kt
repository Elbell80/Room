package com.ekbana.room.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TaskDetailsConverter {

    @TypeConverter
    fun fromListToString(listValues: List<TaskDetails>?): String? {
        if (listValues == null) {
            return null
        }

        val gson = Gson()
        val type = object : TypeToken<List<TaskDetails>>() {

        }.type
        return gson.toJson(listValues, type)
    }

    @TypeConverter // note this annotation
    fun toStringFromList(optionValuesString: String?): List<TaskDetails>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<TaskDetails>>() {

        }.type
        return gson.fromJson<List<TaskDetails>>(optionValuesString, type)
    }

}