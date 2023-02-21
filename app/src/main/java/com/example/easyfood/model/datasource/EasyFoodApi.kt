package com.example.easyfood.model.datasource

import com.example.easyfood.model.data.RandomMeal
import retrofit2.http.GET

interface EasyFoodApi {

    @GET("random.php")
    suspend fun getRandomMeal():RandomMeal.Meal
}