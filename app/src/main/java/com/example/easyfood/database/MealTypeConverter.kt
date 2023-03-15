package com.example.easyfood.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters

//creating a class to convert data for database
@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnyToString(attribute:Any?): String{
        if (attribute == null)
            return ""
        return attribute.toString()
    }

    @TypeConverter
    fun fromStringToAny(attribute:String?): Any{
        if (attribute == null)
            return ""
        return attribute
    }
}