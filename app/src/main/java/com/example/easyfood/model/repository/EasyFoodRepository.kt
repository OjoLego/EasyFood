package com.example.easyfood.model.repository

import com.example.easyfood.model.data.CategoryMealList
import com.example.easyfood.model.data.MealDetailsId
import com.example.easyfood.model.data.MostPopularMeals
import com.example.easyfood.model.data.RandomMeal
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

}