package com.example.easyfood.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.model.data.MealDetailsId
import com.example.easyfood.model.data.RandomMeal

@Dao
interface MealDao {

    //This is the function to insert/update single new mealDetails Into the MealDatabase entity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(mealDetails: MealDetails)

    //This is the function to delete single new mealDetails from the MealDatabase entity
    @Delete
    suspend fun delete(meal: MealDetails)

    //This is the function to observe MealDatabase entity and get all info
    @Query("SELECT * FROM mealInformation")
    fun getAllMeals(): LiveData<List<MealDetails>>

}