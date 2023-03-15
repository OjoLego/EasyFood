package com.example.easyfood.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.MealDetails

//Creating instance of the MealDatabase
@Database(entities = [MealDetails::class], version = 2)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase: RoomDatabase() {

    //Creating an instance of the MealDao Interface
    abstract fun mealDao(): MealDao

    //Creating an instance of the MealDatabase using the fun getInstance
    companion object{
        @Volatile
        var INSTANCE:MealDatabase? = null

        @Synchronized
        fun getInstance(context:Context):MealDatabase{
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "meal.db"
                    ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
            }
        }
    }