package com.example.easyfood.model.datasource

import com.example.easyfood.model.data.*
import retrofit2.http.GET
import retrofit2.http.Query

interface EasyFoodApi {

    @GET("random.php")
    suspend fun getRandomMeal():RandomMeal

    @GET("lookup.php?")
    suspend fun getMealDetailId(@Query("i") id: String):MealDetailsId

    @GET("filter.php?")
    suspend fun getMostPopularMeals(@Query("c") categoryName: String): MostPopularMeals

    @GET("categories.php")
    suspend fun getCategoriesMeal(): CategoryMealList

    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") categoryName:String):MealByCategoryList

    @GET("search.php")
    suspend fun searchMeals(@Query("s") searchQuery: String): MealDetailsId
//    @GET("search.php")
//    suspend fun searchMeals(@Query("s") searchQuery: String): SearchMealList
}