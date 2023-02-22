package com.example.easyfood.model.repository

import com.example.easyfood.model.data.MealDetailsId
import com.example.easyfood.model.data.RandomMeal
import com.example.easyfood.model.datasource.EasyFoodApi

class EasyFoodRepository(private val  easyFoodApi: EasyFoodApi) {

    suspend fun getRandomMeal():RandomMeal{
        return easyFoodApi.getRandomMeal()
    }

    suspend fun getMealDetailsId(id:String):MealDetailsId{
        return easyFoodApi.getMealDetailId(id)
    }

}