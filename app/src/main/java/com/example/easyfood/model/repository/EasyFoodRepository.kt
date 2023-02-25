package com.example.easyfood.model.repository

import com.example.easyfood.model.data.*
import com.example.easyfood.model.datasource.EasyFoodApi

class EasyFoodRepository(private val  easyFoodApi: EasyFoodApi) {

    suspend fun getRandomMeal():RandomMeal{
        return easyFoodApi.getRandomMeal()
    }

    suspend fun getMealDetailsId(id:String):MealDetailsId{
        return easyFoodApi.getMealDetailId(id)
    }

    suspend fun getMostPopularMeals(categoryName:String): MostPopularMeals{
        return easyFoodApi.getMostPopularMeals(categoryName)
    }

    suspend fun getCategoriesMeal():CategoryMealList{
        return easyFoodApi.getCategoriesMeal()
    }

    suspend fun getMealByCategory(categoryName:String):MealByCategoryList{
        return easyFoodApi.getMealByCategory(categoryName)
    }

}