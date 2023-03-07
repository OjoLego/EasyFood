package com.example.easyfood.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.model.data.MealDetailsId
import com.example.easyfood.model.data.RandomMeal

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(mealDetails: MealDetails)

    @Delete
    suspend fun delete(meal: MealDetails)

    @Query("SELECT * FROM mealInformation")
    fun getAllMeals(): LiveData<List<MealDetails>>

}