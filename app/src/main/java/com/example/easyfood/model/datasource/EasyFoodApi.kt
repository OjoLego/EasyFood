package com.example.easyfood.model.datasource

import com.example.easyfood.model.data.*
import retrofit2.http.GET
import retrofit2.http.Query

interface EasyFoodApi {

    //get a randomMeal from Api call
    @GET("random.php")
    suspend fun getRandomMeal():RandomMeal

    //to get a mealDetails with id from Api call
    @GET("lookup.php")
    suspend fun getMealDetailId(@Query("i") id: String):MealDetailsId

    //get a list of popularMeals from Api call
    @GET("filter.php")
    suspend fun getMostPopularMeals(@Query("c") categoryName: String): MostPopularMeals

    //get list of Categories of meals from Api call
    @GET("categories.php")
    suspend fun getCategoriesMeal(): CategoryMealList

    //get list of meals in a category from Api call
    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") categoryName:String):MealByCategoryList

    //get list of meals with query from Api call
    @GET("search.php")
    suspend fun searchMeals(@Query("s") searchQuery: String): MealDetailsId
//    @GET("search.php")
//    suspend fun searchMeals(@Query("s") searchQuery: String): SearchMealList
}