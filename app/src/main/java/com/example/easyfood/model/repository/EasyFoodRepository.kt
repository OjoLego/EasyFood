package com.example.easyfood.model.repository

import androidx.lifecycle.LiveData
import com.example.easyfood.database.MealDao
import com.example.easyfood.model.data.*
import com.example.easyfood.model.datasource.EasyFoodApi
import retrofit2.http.Query

class EasyFoodRepository(
    private val mealDao: MealDao,
    private val  easyFoodApi: EasyFoodApi
    ) {

    val readAllMeals: LiveData<List<MealDetails>> = mealDao.getAllMeals()

    suspend fun deleteRandomMeal(mealDetails: MealDetails){
        mealDao.delete(mealDetails)
    }

    suspend fun saveRandomMeal(mealDetails:MealDetails){
       mealDao.upsert(mealDetails)
    }

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

    suspend fun searchMeals(searchQuery: String): MealDetailsId{
        return easyFoodApi.searchMeals(searchQuery)
    }

//    suspend fun searchMeals(searchQuery: String): SearchMealList{
//        return easyFoodApi.searchMeals(searchQuery)
//    }

}