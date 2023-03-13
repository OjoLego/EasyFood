package com.example.easyfood.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.easyfood.database.MealDatabase
import com.example.easyfood.model.data.MealByCategory
import com.example.easyfood.model.datasource.EasyFoodRetrofit
import com.example.easyfood.model.repository.EasyFoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "CategoryMealViewModel"
class CategoryMealViewModel(application: Application): AndroidViewModel(application) {

    private val mealDao = MealDatabase.getInstance(application).mealDao()
    private val retrofitService = EasyFoodRetrofit.easyFoodRetrofit
    private val easyFoodRepository = EasyFoodRepository(mealDao,retrofitService)

    private var _getMealByCategory = MutableLiveData<List<MealByCategory>>()
    val getMealByCategory: LiveData<List<MealByCategory>> = _getMealByCategory

    fun getMealByCategory(categoryName:String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res = easyFoodRepository.getMealByCategory(categoryName)
                Log.d(TAG,res.meals.toString())
                _getMealByCategory.postValue(res.meals)
            }
            catch(e:Exception){
                e.printStackTrace()
            }
        }
    }

}