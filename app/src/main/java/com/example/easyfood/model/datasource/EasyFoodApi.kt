package com.example.easyfood.model.datasource

import com.example.easyfood.model.data.MealDetailsId
import com.example.easyfood.model.data.RandomMeal
import retrofit2.http.GET
import retrofit2.http.Query

interface EasyFoodApi {

    @GET("random.php")
    suspend fun getRandomMeal():RandomMeal

    @GET("lookup.php?")
    suspend fun getMealDetailId(@Query("i") id: String):MealDetailsId
}